package com.sid1818713.plannerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.sid1818713.plannerapp.fragments.modules.Modules
import com.sid1818713.plannerapp.fragments.home.Home
import com.sid1818713.plannerapp.fragments.notes.Notes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_menu.setNavigationItemSelectedListener(this)

        setToolbarTitle("Home")
        changeFragment(Home())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)

        when(item.itemId) {
            R.id.home -> {
                setToolbarTitle("Home")
                changeFragment(Home())
            }

            R.id.modules -> {
                setToolbarTitle("Modules")
                changeFragment(Modules())
            }

            R.id.notes -> {
                setToolbarTitle("Notes")
                changeFragment(Notes())
            }
        }
        return true
    }

    fun setToolbarTitle(title:String) {
        supportActionBar?.title = title
    }

    fun changeFragment(frag : Fragment) {
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container, frag).commit()
    }
}