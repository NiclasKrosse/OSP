package com.example.osp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class Activity_Ticketlist extends AppCompatActivity
{
    private String mPcNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ticketlist);

        //pc_number
        mPcNumber = getIntent().getSerializableExtra("pc_number").toString();
        setTitle("Tickets zu " + mPcNumber);

        //Liste
        InitList();


    }

    private void  InitList()
    {
        ListView nView = findViewById(R.id.listview_tickets);
        String [] nDataset = {"#123","#456","#789"};
        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset));
    }

    private void InitCreateTicketButton()
    {
        Button nButton = findViewById(R.id.button_createticket);

        nButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
