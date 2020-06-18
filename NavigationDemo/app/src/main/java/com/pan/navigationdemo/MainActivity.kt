package com.pan.navigationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        bottomNav.setOnNavigationItemSelectedListener{item->
//            when(item.itemId){
//                R.id.homeFragment->{
//                    findNavController(R.id.navHostFragment).navigate(R.id.homeFragment)
//                }
//                R.id.listFragment->{
//                    findNavController(R.id.navHostFragment).navigate(R.id.listFragment)
//                }
//                R.id.mineFragment->{
//                    findNavController(R.id.navHostFragment).navigate(R.id.mineFragment)
//                }
//            }
//            true
//        }
        val navController = findNavController(R.id.navHostFragment)
        bottomNav.setupWithNavController(navController)
//        hideActionBar()
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.e("MainActivity","destination.id:${destination.id}")
            if(destination.id == R.id.listFragment){
                hideActionBar()
            }else{
                title = if(destination.id == R.id.listFragment){
                    "Super_Pan"
                } else {
                    destination.label
                }
                showActionBar()
            }
        }
//        setupActionBarWithNavController(navController) 设置之后切换BottomNavigationView actionBar 标签会跟随变化

        val uri = intent.data
        if(uri!=null){// 唤起链接
            val sb = StringBuilder()
            sb.append("string : ").append(intent.dataString).append("\n")
            sb.append("scheme : ").append(uri.scheme).append("\n")
            sb.append("host : ").append(uri.host).append("\n")
            sb.append("port : ").append(uri.port).append("\n")
            sb.append("path : ").append(uri.path).append("\n")
            sb.append("queryParameterNames : ").append(uri.getQueryParameters("param"))
            Log.e("MainActivity","$sb")
        }
    }


    fun showActionBar() {
        supportActionBar?.show()
    }

    fun hideActionBar() {
        supportActionBar?.show()
    }

    fun showBottomNavigation(){
        bottomNav.visibility=View.VISIBLE
    }

    fun hideBottomNavigation(){
        bottomNav.visibility=View.GONE
    }


}