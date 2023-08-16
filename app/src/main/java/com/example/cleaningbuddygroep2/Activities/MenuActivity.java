package com.example.cleaningbuddygroep2.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import com.example.cleaningbuddygroep2.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void logoutToLogin(View view){
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void goToOpenstaandeTaken(View view){
        Intent intent = new Intent(this, OpenstaandeTakenActivity.class);
        startActivity(intent);
    }

    public void goToAlleTaken(View view){
        Intent intent = new Intent(this, AlleTakenActivity.class);
        startActivity(intent);
    }

    public void goToNieuweTaak(View view){
        Intent intent = new Intent(this, NieuweTaakActivity.class);
        startActivity(intent);
    }

    public void goToNieuweKamer(View view){
        Intent intent = new Intent(this, NieuweKamerActivity.class);
        startActivity(intent);
    }

    public void goToAlleKamers(View view){
        Intent intent = new Intent(this, AlleKamersActivity.class);
        startActivity(intent);

    }
}