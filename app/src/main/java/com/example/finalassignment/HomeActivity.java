package com.example.finalassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.btm_navigation);
        bottomNav.setOnItemSelectedListener(navlisterner);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commit();

    }

    private NavigationBarView.OnItemSelectedListener navlisterner = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            //switch statement to pass the item
            //getItemId, what item was clicked
            //selectedFragment that were created
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new FragmentHome();
                    break;
                case R.id.nav_browse:
                    selectedFragment = new FragmentBrowser();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new FragmentProfile();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}