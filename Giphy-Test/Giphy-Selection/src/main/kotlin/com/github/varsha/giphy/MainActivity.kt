package com.github.varsha.giphy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.github.varsha.giphy.trends.TrendingFragment

/**
 * Main Activity for the app.
 */
class MainActivity : AppCompatActivity() {

    private val toolbar by lazy{
        findViewById<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, TrendingFragment.newInstance())
                    .commit()           // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,TrendingFragment()).commit();



        }

    }
}
