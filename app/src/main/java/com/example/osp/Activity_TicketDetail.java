package com.example.osp;

import android.os.Bundle;

import com.example.osp.data.pojo.Geraet;
import com.example.osp.data.pojo.Ticket;
import com.example.osp.mailing.MailingTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    protected void onCreate(final Bundle savedInstanceState) {
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
                SaveTicket();
            }
        });

        try {
            //Laden
            mRoomNumber = getIntent().getSerializableExtra("room_number").toString();
            mPcNumber = getIntent().getSerializableExtra("pc_number").toString();
            mIsNew = getIntent().getSerializableExtra("is_new").toString();
        } catch (Exception ex) {
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

            //Zweite Titelzeile setzten
            mDate = new Date();
            DateFormat nFormat = new SimpleDateFormat("dd.MM.yyyy");
            TextView nTitleView = findViewById(R.id.textVvew_ticketdata);
            nTitleView.setText("Datum: " + nFormat.format(mDate) + " | Raum: " + mRoomNumber + " | " + mPcNumber);
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

        nStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                //Farben für die Status anzeigen
                ImageView nImageView = findViewById(R.id.imageView_statuscolor);

                switch (position)
                {
                    //Offen
                    case 0:
                        nImageView.setImageResource( R.color.status_offen);
                        break;
                    case 1:
                        nImageView.setImageResource(R.color.status_geschlossen);
                        break;
                    case 2:
                        nImageView.setImageResource(R.color.status_erledigt);
                        break;
                    case 3:
                        nImageView.setImageResource(R.color.status_inBearbeitung);
                        break;
                    case 4:
                        nImageView.setImageResource(R.color.status_inBearbeitung);
                        break;
                    case 5:
                        nImageView.setImageResource(R.color.status_geschlossen);
                        break;

                    default:
                        nImageView.setImageResource(R.color.status_geschlossen);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        //spinner_prio
        //Prioritäten in das Steuerelement laden
        Spinner nPrioSpinner = findViewById(R.id.spinner_prio);

        //Prios aus dem Ressourcenarray laden
        ArrayAdapter<CharSequence> nPrioAdapter = ArrayAdapter.createFromResource(this,
                R.array.Prio, android.R.layout.simple_spinner_item);
        nPrioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nPrioSpinner.setAdapter(nPrioAdapter);

        nPrioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Farben für die Prio anzeigen
                ImageView nImageView = findViewById(R.id.imageView_priocolor);

                switch (position)
                {
                    //Niedrig
                    case 0:
                        nImageView.setImageResource(R.color.status_erledigt);
                        break;
                    //Normal
                    case 1:
                        nImageView.setImageResource(R.color.status_inBearbeitung);
                        break;
                    //Hock
                    case 2:
                        nImageView.setImageResource(R.color.status_offen);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        //Standardfälle
        Spinner nDefaultCaseSpinner = findViewById(R.id.spinner_defaultcases);

        //todo
        //ArrayAdapter<String> nDefaultCaseAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,new ArrayList( {"Hallo","Schön"}));
        //nDefaultCaseSpinner.setAdapter(nDefaultCaseAdapter);
    }

    private void SaveTicket()
    {

        //Steuerelemente laden
        AutoCompleteTextView nDescriptionView = findViewById(R.id. multiAutoCompleteTextView_ErrorDescription);
        Spinner nStatusSpinner = findViewById(R.id.spinner_status);

        //Variablen setzen
        String nError = nDescriptionView.getText().toString();
        String nStatus =nStatusSpinner.getSelectedItem().toString();

        //Todo Laden
        Geraet nPc = new Geraet(100,1,1,mRoomNumber,mPcNumber);

        //Ticket anlegen
        Ticket nTicketToSave = new Ticket("100",nStatus,nPc.id,nError);
    }
}
