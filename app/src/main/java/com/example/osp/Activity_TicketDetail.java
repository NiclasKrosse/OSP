package com.example.osp;

import android.os.Bundle;

import com.example.osp.mailing.MailingTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class Activity_TicketDetail extends AppCompatActivity {

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
    }
}
