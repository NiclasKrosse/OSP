package com.example.osp.mailing;

import android.app.Activity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MailingActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public void sendMail() {

        String fromEmail = "";
        String fromPassword = "";
        String toEmails = "riemann-isabel@gmx.de";
        List toEmailList = Arrays.asList(toEmails
                .split("\\s*,\\s*"));
        String emailSubject = "TICKET FATALER FEHLER :o";
        String emailBody = "Automatisch generierte Email - pls dont reply - but error oO";
        new MailingTask().execute(fromEmail,
                fromPassword, toEmailList, emailSubject, emailBody);
    }
}
