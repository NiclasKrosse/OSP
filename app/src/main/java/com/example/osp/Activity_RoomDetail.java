package com.example.osp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_RoomDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__room_detail);

        ListView nView = findViewById(R.id.listview_pcs);
        String[] nDataset = {"PC 01","PC 02","PC 03","PC 04","PC 05","Beamer","L. PC"};//new String[5];

        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset));
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
/*        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();*/
    }

}
