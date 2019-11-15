package com.example.osp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Toast;

import com.example.osp.MainActivity;
import com.example.osp.R;


public class LoginActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Den Loginbutton initialisieren
        final Button loginButton = findViewById(R.id.login);
        loginButton.setEnabled(true);
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DoLogIn();
            }
        });
    }

    //Anmeldung ausführen
    private void DoLogIn()
    {
        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);

        boolean nSucess = true;
        int nMode = 0;

        //Name und Passwort abfregen
        switch (usernameEditText.getText().toString().toLowerCase())
        {
            case "gso-lehrer@schule.koeln" :
                nMode = 1;
                break;
            case "gso-raumbetreuer@schule.koeln" :
                nMode = 2;
                break;
            case "gso-werkstatt@schule.koeln" :
                nMode = 3;
                break;

            case "a":
                nMode = 99;
                break;
            default:
                nSucess = false;

        }

        String nPassword = passwordEditText.getText().toString();
        switch (nMode)
        {
            case 1:
                if (! nPassword.equals("weLoveLehrer9"))
                {
                    nSucess = false;
                }
                break;
            case 2:
                if (! nPassword.equals("weLoveRaumbetreuer3"))//
                {
                    nSucess = false;
                }
                break;

            case 3:
                if (! nPassword.equals("weLoveWerkstatt5"))
                {
                    nSucess = false;
                }
                break;
            case 99:
                if (! nPassword.equals("a"))
                {
                    nSucess = false;
                }
        }

        if (nSucess)
        {
            Intent nIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(nIntent);
        }
        else
        {
           Toast.makeText(getApplicationContext(),"Die Kombination aus Benutzername und Passwort ist ungültig!",Toast.LENGTH_LONG);


            usernameEditText.setError("Ungültiger Benutzername");
            passwordEditText.setError("Ungültiges Passwort");
        }

    }
}
