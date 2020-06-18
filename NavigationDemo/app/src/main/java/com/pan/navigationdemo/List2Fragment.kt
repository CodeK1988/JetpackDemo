package com.pan.navigationdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.list2_frag.*

class List2Fragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.list2_frag)
    }

    private val args:List2FragmentArgs by navArgs<List2FragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //方式一 Bundle 直接拿
        textView.text = arguments?.getString("testArgs")
        //方式二  List2FragmentArgs 不知道为啥生成不了 遂放弃 仿佛听到了鄙视的声音??!!
//         textView.text = args.testArgs

        //为目标创建深层链接
        val channel_name = getString(R.string.app_name)
        deepLink.setOnClickListener {
            val notificationId = System.currentTimeMillis().toInt()
//            val intent = Intent()
//            val mainIntent = Intent()
//            intent.setClass(requireContext(), AlertDetailsActivity::class.java)
//            intent.putExtra("textView","我曾经听人讲过 当你不可以再拥有的时候 你唯一可以做的 就是令自己莫忘记")
//            mainIntent.setClass(requireContext(), MainActivity::class.java)
//            mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK  or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//            val pendingIntent = PendingIntent.getActivities(
//                requireContext(),
//                0,
//                arrayOf(mainIntent, intent),
//                PendingIntent.FLAG_UPDATE_CURRENT
//            )

            val bundle = Bundle().apply {
                putString("textView","我曾经听人讲过 当你不可以再拥有的时候 你唯一可以做的 就是令自己莫忘记")
            }
//            val pendingIntent = NavDeepLinkBuilder(requireContext())
//                .setComponentName(AlertDetailsActivity::class.java)
//                .setGraph(R.navigation.alertdetails_nav_graph)
//                .setDestination(R.id.alertDetailsActivity)
//                .setArguments(bundle)
//                .createPendingIntent()

            val pendingIntent = findNavController()
                .createDeepLink()
                .setComponentName(AlertDetailsActivity::class.java)
                .setGraph(R.navigation.alertdetails_nav_graph)
                .setDestination(R.id.alertDetailsActivity)
                .setArguments(bundle)
                .createTaskStackBuilder()
                .addParentStack(MainActivity::class.java)
                .getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT)

            var builder: NotificationCompat.Builder
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel =
                    NotificationChannel(notificationId.toString(), channel_name, importance).apply {
                        description = "我曾经听人讲过 当你不可以再拥有的时候 你唯一可以做的 就是令自己莫忘记"
                    }

                val notificationManager: NotificationManager =
                    requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)

                builder = NotificationCompat.Builder(requireContext(), notificationId.toString())
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background))
                    .setContentTitle("My notification")
                    .setContentText("Hello World!")
                    .setStyle(NotificationCompat.BigTextStyle().bigText("我曾经听人讲过 当你不可以再拥有的时候 你唯一可以做的 就是令自己莫忘记"))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)  //点击自动取消 不设置需要滑动删除
            } else {
                builder = NotificationCompat.Builder(requireContext(), channel_name)
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background))
                    .setContentTitle("My notification")
                    .setContentText("Hello World!")
                    .setPriority(NotificationCompat.PRIORITY_MAX) //设置优先级
                    .setStyle(NotificationCompat.BigTextStyle().bigText("我曾经听人讲过 当你不可以再拥有的时候 你唯一可以做的 就是令自己莫忘记"))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)  //点击自动取消 不设置需要滑动删除
            }
            with(NotificationManagerCompat.from(requireContext())) {
                notify(notificationId, builder.build())
            }
        }
    }
}