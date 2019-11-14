package com.example.osp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Activity_RoomDetail extends AppCompatActivity {

    private String mRoomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__room_detail);

        mRoomNumber = getIntent().getSerializableExtra("room_number").toString();
        setTitle(mRoomNumber);

        ListView nView = findViewById(R.id.listview_pcs);
        String[] nDataset = {"PC 01","PC 02","PC 03","PC 04","PC 05","Beamer","L. PC"};//new String[5];

        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset));

        Button nButton  = findViewById(R.id. button_switchtogrid);

        nButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Zur Rasteransicht wechseln
                Intent intent = new Intent(Activity_RoomDetail. this,Activity_RoomDetail_Grid.class);

                intent.putExtra("room_number",mRoomNumber);
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }

}
