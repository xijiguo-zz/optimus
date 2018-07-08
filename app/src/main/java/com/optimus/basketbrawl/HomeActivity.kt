package com.optimus.basketbrawl

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import android.support.v4.view.ViewPager
import android.view.MenuItem


class HomeActivity : AppCompatActivity() {

    private var prevMenuItem: MenuItem? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_courts -> {
//                message.setText(R.string.title_home)
                pager.currentItem = 0
                changeTitle(pager.adapter!!.getPageTitle(0))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_games -> {
//                message.setText(R.string.title_dashboard)
                pager.currentItem = 1
                changeTitle(pager.adapter!!.getPageTitle(1))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
//                message.setText(R.string.title_notifications)
                pager.currentItem = 2
                changeTitle(pager.adapter!!.getPageTitle(2))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val courtContainerFragment = CourtContainerFragment()
        val gameFragment = GameFragment()
        val profileFragment = ProfileFragment()
        adapter.addFragment(courtContainerFragment, "Court List")
        adapter.addFragment(gameFragment, "My Games")
        adapter.addFragment(profileFragment, "Profile")
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3
    }

    private fun changeTitle(titleName: CharSequence?) {
        title = titleName

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setupViewPager(pager)

        supportActionBar!!.hide()

        title = pager.adapter!!.getPageTitle(0)

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    navigation.menu.getItem(0).isChecked = false
                }
                navigation.menu.getItem(position).isChecked = true
                changeTitle(pager.adapter!!.getPageTitle(position))
                prevMenuItem = navigation.menu.getItem(position)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


}
