package com.example.osp;

import android.os.Bundle;

import com.example.osp.mailing.MailingTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class Activity_TicketDetail extends AppCompatActivity {

    private String mRoomNumber;
    private String mPcNumber;
    private Date mDate;
    private String mIsNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ticket_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Button send = findViewById(R.id.button_saveticket);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromEmail = "gso.raumbetreuer.app@gmail.com";
                String fromPassword = "Raumbetreuer";
                String toEmails = "riemann-isabel@gmx.de";
                List toEmailList = Arrays.asList(toEmails
                        .split("\\s*,\\s*"));
                String emailSubject = "TICKET FATALER FEHLER :o";
                String emailBody = "Sorry, not sorry";
                new MailingTask(Activity_TicketDetail.this).execute(fromEmail,
                        fromPassword, toEmailList, emailSubject, emailBody);
            }
        });
        try
        {
            //Laden
            mRoomNumber = getIntent().getSerializableExtra("room_number").toString();
            mPcNumber = getIntent().getSerializableExtra("pc_number").toString();
            mIsNew =  getIntent().getSerializableExtra("is_new").toString();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Ticket konnte nicht initialisiert werden", Toast.LENGTH_LONG).show();
        }

        SetTitle();
        InitSpinners();
    }

    //Titelzeilen initialisieren
    private void SetTitle()
    {
        if (mIsNew.equals("true"))
        {
            //Titelleiste
            setTitle("Neues Ticket");

            //Raumnummer, Datum, ... setzen
            mDate = new Date();
            DateFormat nFormat = new SimpleDateFormat("dd.MM.yyyy");


            TextView nTitleView = findViewById(R.id.textVvew_ticketdata);
            nTitleView.setText("Datum: "+ nFormat.format( mDate )+" | Raum: "+ mRoomNumber +" | "+mPcNumber);

            //todo
            //textVvew_ticketdata
            //Datum: dd.mm.yyyy |Raum: CXXX | PC 01
        }
    }

    //Dropdownmenüs initialisieren
    private void InitSpinners()
    {
        //Status in das Steuerelement laden
        Spinner nStatusSpinner = findViewById(R.id.spinner_status);

        //Statuswerte aus dem Ressourcenarray laden
        ArrayAdapter<CharSequence> nStatusAdapter = ArrayAdapter.createFromResource(this,
                R.array.Status, android.R.layout.simple_spinner_item);
        nStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nStatusSpinner.setAdapter(nStatusAdapter);

        //spinner_prio
        //Prioritäten in das Steuerelement laden
        Spinner nPrioSpinner = findViewById(R.id.spinner_prio);

        //Prios aus dem Ressourcenarray laden
        ArrayAdapter<CharSequence> nPrioAdapter = ArrayAdapter.createFromResource(this,
                R.array.Prio, android.R.layout.simple_spinner_item);
        nPrioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nPrioSpinner.setAdapter(nPrioAdapter);

    }
}
