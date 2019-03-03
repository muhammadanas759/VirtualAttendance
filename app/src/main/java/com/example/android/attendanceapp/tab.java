package com.example.android.attendanceapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class tab extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabAdapter(getSupportFragmentManager());

//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_menu_camera);
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_playlist_add_black_24dp);

        adapter.addFragment(new Tab1Fragment(), "take Manual");
        adapter.addFragment(new CameraFragment(), "take Video");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    
    public void onItemClick(String name) {
        Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
//        if(commitList.contains(name))
//            commitList.remove(name);
//        else
//            commitList.add(name);
    }
}

