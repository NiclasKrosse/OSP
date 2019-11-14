package com.example.osp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        //Set Title to Room Number
        mRoomNumber = getIntent().getSerializableExtra("room_number").toString();
        setTitle(mRoomNumber);

        //Init List
        AddListItemClick();

        //Add Click Event to Button
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

    private void AddListItemClick()
    {
        //Create and insert Dummy Data
        ListView nView = findViewById(R.id.listview_pcs);
        String[] nDataset = {"PC 11","PC 12","PC 15","PC 16","PC 21","PC 21","PC 22","PC 25","PC 26","PC 31","PC 32","PC 32","PC 35","PC 36","PC 51","PC 52","PC 55","PC 56","Beamer","L PC"};//new String[5];
        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset));


        //Click event
        nView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(Activity_RoomDetail.this,Activity_Ticketlist.class);

                //Pc übergeben
                intent.putExtra("pc_number",parent.getItemAtPosition(position).toString());
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }
}
