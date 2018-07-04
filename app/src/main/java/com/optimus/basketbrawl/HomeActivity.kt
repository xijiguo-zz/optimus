package com.optimus.basketbrawl

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*


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
        val courtFragment = CourtFragment()
        val gameFragment = GameFragment()
        val profileFragment = ProfileFragment()
        adapter.addFragment(courtFragment, "Court List")
        adapter.addFragment(gameFragment, "My Games")
        adapter.addFragment(profileFragment, "Profile")
        viewPager.adapter = adapter
    }

    private fun changeTitle(titleName: CharSequence?) {
        title = titleName
    }

    public fun signoutFirebase(view: android.view.View) {
        logout_button.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        setupViewPager(pager)

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
