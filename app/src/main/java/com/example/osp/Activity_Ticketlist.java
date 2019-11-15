package com.example.osp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class Activity_Ticketlist extends AppCompatActivity
{
    private String mPcNumber;
    private String mRoomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ticketlist);

        Boolean nSucess = true;

        try
        {
            //Überschrift auf die Nummer des gewählten PCs setzen und Raumnummer laden
            mPcNumber = getIntent().getSerializableExtra("pc_number").toString();
            mRoomNumber= getIntent().getSerializableExtra("room_number").toString();
            setTitle("Tickets zu " + mPcNumber);
        }
        //Todo Beim verlassen eines Tickets
        catch(Exception ex)
        {
            setTitle("Tickets");
            Toast.makeText(getApplicationContext(), "Liste konnte nicht initialisiert werden", Toast.LENGTH_LONG).show();
            nSucess = false;
        }


        //Liste
        InitList();

        //Neues Ticket erstellen
        InitCreateTicketButton(nSucess);

    }

    private void  InitList()
    {
        //Liste mit einfachen Dummy-Daten befüllen
        ListView nView = findViewById(R.id.listview_tickets);
        String [] nDataset = {"#123","#456","#789"};
        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset));
    }

    private void InitCreateTicketButton(Boolean sucess)
    {

        Button nButton = findViewById(R.id.button_createticket);

        if (!sucess)
        {
            nButton.setEnabled(false);
            return;
        }

        nButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Activity_Ticketlist.this,Activity_TicketDetail.class);

                //Dem neuen Ticket PC und Raumnummer geben
                intent.putExtra("pc_number",mPcNumber);
                intent.putExtra("room_number",mRoomNumber);
                intent.putExtra("is_new","true");

                startActivity(intent);
            }
        });
    }
}
