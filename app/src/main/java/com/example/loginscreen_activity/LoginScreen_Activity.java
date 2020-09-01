package com.example.loginscreen_activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen_Activity extends AppCompatActivity {


 private EditText etenterEmail;
 private SharedPreferences.Editor editor;
 private EditText mEtPassword;
 private CheckBox mChRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

             mEtPassword = findViewById(R.id.etPassword);
             etenterEmail=findViewById(R.id.editTextTextEmailAddress);
             mChRememberMe=findViewById(R.id.chk_remember);
             Button btnlogin = findViewById(R.id.button);

        SharedPreferences prefManager = getApplicationContext().getSharedPreferences( "SHARED",MODE_PRIVATE);
        editor = prefManager.edit();

                btnlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String username = etenterEmail.getText().toString();
                        String password = mEtPassword.getText().toString();

                        boolean isRemember=mChRememberMe.isChecked();
                        if (isRemember){
                            editor.putString("USERNAME",username);
                            editor.putString("PASSWORD",password);
                            editor.putBoolean("ISREMEMBER",isRemember);

                        //    editor.commit();
                            editor.apply();
                        }
                    moveToViewprofileActivity();
                    }
                });
                boolean isAlreadyLogin=prefManager.getBoolean("ISREMEMBER",false);
                if(isAlreadyLogin) {
                    moveToViewprofileActivity();
                }
    }

    private void moveToViewprofileActivity(){
        startActivity(new Intent(LoginScreen_Activity.this,ViewprofileActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences prefManager = getApplicationContext().getSharedPreferences( "SHARED",MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = prefManager.edit();

        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                String editedUsername = data.getExtras().getString("EDITED_USERNAME");
                etenterEmail.setText(editedUsername);
            } else {
                Toast.makeText(LoginScreen_Activity.this, "User Cancelled operation", Toast.LENGTH_LONG).show();
            }
        }
    }
}

