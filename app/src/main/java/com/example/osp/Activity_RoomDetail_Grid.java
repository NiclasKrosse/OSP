package com.example.osp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Activity_RoomDetail_Grid extends AppCompatActivity {

    private  String mRoomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__room_detail__grid);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set Title to Room Number
        mRoomNumber = getIntent().getSerializableExtra("room_number").toString();
        setTitle(mRoomNumber);

        //Set Dummy image
        ImageView nImgView = findViewById(R.id.imageView_grid);
        nImgView.setImageResource(R.drawable.room_grid);

        //Button for list view
        Button nButton = findViewById(R.id.button_switchtolist);
        nButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Activity_RoomDetail_Grid. this,Activity_RoomDetail.class);
                intent.putExtra("room_number",mRoomNumber);
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }

}
