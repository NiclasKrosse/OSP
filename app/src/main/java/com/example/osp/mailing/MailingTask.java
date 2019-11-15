package com.example.osp.mailing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

public class MailingTask extends AsyncTask {

    private ProgressDialog statusDialog;
    private Activity sendMailActivity;

    public MailingTask(Activity activity) {
        sendMailActivity = activity;
    }

    protected void onPreExecute() {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Getting ready...");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }


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

    @Override
    public void onProgressUpdate(Object... values) {
        statusDialog.setMessage(values[0].toString());

    }

    @Override
    public void onPostExecute(Object result) {
        statusDialog.dismiss();
    }
}
