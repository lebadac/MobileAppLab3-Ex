package com.dac.lab3_ex

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Main activity displaying a list of users with a custom toolbar.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var usersAdapter: UsersAdapter

    /**
     * Called when the activity is first created.
     * Sets up the toolbar, list view, and populates user data.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the custom Toolbar as ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configure ActionBar properties
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setLogo(R.drawable.ic_android_black_30dp)
            setDisplayUseLogoEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = "CustomAdapterDemo"
        }

        // Initialize ListView and adapter
        val listView = findViewById<ListView>(R.id.lvItems)

        val arrayOfUsers = arrayListOf(
            User("Tuyet Mai", "Ho Chi Minh"),
            User("Quynh Tien", "Ho Chi Minh"),
            User("Bao Chau", "Binh Dinh")
        )

        // Set up the adapter for ListView
        usersAdapter = UsersAdapter(this, arrayOfUsers)
        listView.adapter = usersAdapter

        // Add a new user dynamically
        val newUser = User("Gia Hung", "Ho Chi Minh")
        usersAdapter.add(newUser)
    }
}
