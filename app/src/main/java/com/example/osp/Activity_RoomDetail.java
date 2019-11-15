package com.example.osp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.osp.data.pojo.Geraet;

import java.util.ArrayList;
import java.util.List;

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
        /*
        String[] nDataset = {"PC 11","PC 12","PC 15","PC 16","PC 21","PC 21","PC 22","PC 25","PC 26","PC 31","PC 32","PC 32","PC 35","PC 36","PC 51","PC 52","PC 55","PC 56","Beamer","L PC"};//new String[5];
        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset)); */

        //todo Durch DB Daten ersetzten
        RaumbetreuerDataSource nRbDatasource = new RaumbetreuerDataSource(this);

        nRbDatasource.insertGeraet(new Geraet(100,1,1,mRoomNumber,"PC01"));
        nRbDatasource.insertGeraet(new Geraet(200,1,2,mRoomNumber,"PC02"));
        nRbDatasource.insertGeraet(new Geraet(300,1,3,mRoomNumber,"PC03"));
        nRbDatasource.insertGeraet(new Geraet(400,1,4,mRoomNumber,"PC04"));
        nRbDatasource.insertGeraet(new Geraet(500,1,5,mRoomNumber,"PC05"));



        List<Geraet> nDevices = new ArrayList<Geraet>();
          nDevices.add(new Geraet(100,1,1,mRoomNumber,"PC01"));

        List<String> nDeviceList = new ArrayList<String>();
        for (int i = 0; i<= nDevices.size() -1; i++)
        {
            nDeviceList.add(nDevices.get(i).geraeteName);
        }

        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDeviceList));

        //Click event
        nView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(Activity_RoomDetail.this,Activity_Ticketlist.class);

                //Pc und Raum übergeben
                intent.putExtra("pc_number",parent.getItemAtPosition(position).toString());
                intent.putExtra("room_number",mRoomNumber);
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }
}
