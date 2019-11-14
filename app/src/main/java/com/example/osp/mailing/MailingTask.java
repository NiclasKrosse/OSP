package com.example.osp.mailing;

import android.os.AsyncTask;

import java.util.List;

public class MailingTask extends AsyncTask {


    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            publishProgress("Processing input....");
            publishProgress("Preparing mail message....");
            GMail androidEmail = new GMail(objects[0].toString(),
                    objects[1].toString(), (List) objects[2], objects[3].toString(),
                    objects[4].toString());
            androidEmail.createEmailMessage();
            publishProgress("Sending email....");
            androidEmail.sendEmail();
            publishProgress("Email Sent.");
        } catch (Exception e) {
            publishProgress(e.getMessage());

        }
        return null;
    }
}
