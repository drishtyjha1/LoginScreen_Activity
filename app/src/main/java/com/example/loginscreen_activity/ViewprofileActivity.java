package com.example.loginscreen_activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class ViewprofileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewprofile_activity);
        EditText etprofilername =findViewById(R.id.editText2);
        Button btnlogout = findViewById(R.id.btn_logout);
        Button btnedit =findViewById(R.id.button2);
        Button btnsnakbar =findViewById(R.id.btn_snakbar);

        SharedPreferences sharedpref=getApplicationContext().getSharedPreferences("SHARED", MODE_PRIVATE );
        final SharedPreferences.Editor editor = sharedpref.edit();

        String username =sharedpref.getString("USERNAME", "");
        etprofilername.setText(username);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("USERNAME","");
                editor.putString("PASSWORD","");
                editor.putBoolean("ISREMEMBER",false);
                editor.apply();

                Toast.makeText(ViewprofileActivity.this,"you have successfully logout",Toast.LENGTH_LONG).show();
                startActivity(new Intent(ViewprofileActivity.this,LoginScreen_Activity.class));
                finish();
            }
        });
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ViewprofileActivity.this).setTitle("Alert")
                        .setMessage("Are you sure want to logout")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Cencle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });
        btnsnakbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"showing Snackbar", Snackbar.LENGTH_LONG).setAction("REDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        });
    }
}
