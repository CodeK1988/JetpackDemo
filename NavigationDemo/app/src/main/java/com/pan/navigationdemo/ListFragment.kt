package com.pan.navigationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.list_frag.*

class ListFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return container?.inflate(R.layout.list_frag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doAction.setOnClickListener {
            // Fragment 目的地共享元素过渡
            // https://developer.android.com/guide/navigation/navigation-animate-transitions#fragment_destination_shared_element_transitions
//            val extras = FragmentNavigatorExtras(doAction to "textView")
//            findNavController().navigate(R.id.list2Fragment,
//            null,       // Bundle of args
//            null,  // NavOptions
//                extras)
            //转场动画
//            findNavController().navigate(R.id.action_listFragment_to_list2Fragment)

            //==================== 传参 方式一
            //ListFragmentDirections 编译生成的 不行的话 退出重新打开Android Studio或者 clear rebuild一下试试
            //如果还不行
            // apply plugin: 'com.android.application'
            //apply plugin: 'kotlin-android'
            //apply plugin: 'kotlin-android-extensions'
            //apply plugin: 'kotlin-kapt'
            //apply plugin: 'androidx.navigation.safeargs.kotlin' 顺序放在最下面

//            val action =
//                ListFragmentDirections.actionListFragmentToList2Fragment("方式一")
//            findNavController().navigate(action)

            //==================== 传参 方式二 Bundle
            val bundleOf = bundleOf("testArgs" to "方式二", "xx" to "xxx")
            findNavController().navigate(R.id.action_listFragment_to_list2Fragment,bundleOf)
        }
    }
}