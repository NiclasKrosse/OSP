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
    private Geraet[] mGeratete;

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
        ListView nView = findViewById(R.id.listview_pcs);
        RaumbetreuerDataSource nRbDatasource = new RaumbetreuerDataSource(this);

        mGeratete = nRbDatasource.selectGeraete(mRoomNumber);

        List<String> nDeviceList = new ArrayList<String>();
        if (mGeratete != null)
        {

            for(int i = 0; i < mGeratete.length; i++)
            {
                nDeviceList.add(mGeratete[i].geraeteName);

            }
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
                intent.putExtra("pc_id", mGeratete[position].id);
                intent.putExtra("room_number",mRoomNumber);
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }
}
