package com.shiftdev.freshstartec;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import static com.shiftdev.freshstartec.R.id;
import static com.shiftdev.freshstartec.R.layout;
import static com.shiftdev.freshstartec.R.string;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     private DrawerLayout drawer;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(layout.activity_main);
          Toolbar toolbar = findViewById(id.toolbar);
          setSupportActionBar(toolbar);
          drawer = findViewById(id.drawer_layout);
          NavigationView navigationView = findViewById(id.nav_view);
          navigationView.setNavigationItemSelectedListener(this);


          ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                  string.nav_app_bar_open_drawer_description, string.navigation_drawer_close);
          drawer.addDrawerListener(toggle);
          toggle.syncState();
          if (savedInstanceState == null) {
               getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ServicesFragment()).commit();
               navigationView.setCheckedItem(id.nav_services);
          }
     }

     @Override
     public void onBackPressed() {
          if (drawer.isDrawerOpen(GravityCompat.START)) {
               drawer.closeDrawer(GravityCompat.START);
          } else {
               super.onBackPressed();
          }
     }

     @Override
     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
               case id.nav_services:
                    getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ServicesFragment()).commit();
                    break;
               case id.nav_archive:
                    getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new ArchiveFragment()).commit();
                    break;
               case id.nav_about:
                    getSupportFragmentManager().beginTransaction().replace(id.fragment_container, new AboutFragment()).commit();
                    break;

               case id.nav_share:
                    Toast.makeText(this, "Share. TODO MAKE IT BRING UP SHARE DRAWER", Toast.LENGTH_SHORT).show();
                    break;
               case id.nav_contact:
                    Toast.makeText(this, "Contact. TODO SHOW CONTACT OPTIONS", Toast.LENGTH_SHORT).show();
                    break;
               case id.nav_give:
                    Toast.makeText(this, "Give. TODO EXPLICIT INTENT TO DONATION PAGE IN WEBSITE", Toast.LENGTH_SHORT).show();
                    break;
          }
          drawer.closeDrawer(GravityCompat.START);
          return true;
     }
}