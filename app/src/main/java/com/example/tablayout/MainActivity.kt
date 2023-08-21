package com.example.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.tablayout.databinding.ActivityMainBinding
import com.example.tablayout.databinding.TabItemBinding
import com.example.viewpagertablayout.adapter.MyPagerAdapter
import com.example.viewpagertablayout.models.User
import com.example.viewpagertablayout.utils.DepthPageTransformer
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var list = ArrayList<User>()
    private lateinit var myPagerAdapter: MyPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        myPagerAdapter = MyPagerAdapter(list)
        binding.myViewPager.adapter = myPagerAdapter
        binding.myViewPager.setPageTransformer(true, DepthPageTransformer())
        binding.myTablayout.setupWithViewPager(binding.myViewPager)

        val tabcount = binding.myTablayout.tabCount
        for (i in 0 until tabcount) {
            val tabView = TabItemBinding.inflate(layoutInflater)
            val tab = binding.myTablayout.getTabAt(i)
            tab?.customView = tabView.root

            tabView.txtTitle.text = list[i].text
            if (i == 0) {
                tabView.tabIndicator.visibility = View.VISIBLE
            } else {
                tabView.tabIndicator.visibility = View.VISIBLE
            }
        }
        binding.myTablayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabItemBinding = TabItemBinding.inflate(layoutInflater)
                tabItemBinding.txtTitle.text = list[tab?.position!!].text
                tabItemBinding.tabIndicator.visibility = View.VISIBLE
                tab.setCustomView(tabItemBinding.root)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabItemBinding = TabItemBinding.inflate(layoutInflater)
                tabItemBinding.txtTitle.text = list[tab?.position!!].text
                tabItemBinding.tabIndicator.visibility = View.GONE
                tab.setCustomView(tabItemBinding.root)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun loadData() {
        list = ArrayList()
        list.add(User(R.drawable.ic_launcher_foreground, "launcher"))
        list.add(User(R.drawable.ic_launcher_background, "salom qale"))
    }
}