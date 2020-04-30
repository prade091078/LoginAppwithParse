package com.prade.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
/*
* https://www.back4app.com/docs/android/login-android-tutorial
* https://www.back4app.com/docs/android/parse-android-sdk
* */
public class MainActivity extends AppCompatActivity {

    EditText txtMail,txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMail = findViewById(R.id.edEmail);
        txtPassword = findViewById(R.id.edPassword);

        if(ParseUser.getCurrentUser()!=null){
            Intent intent =new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view) {

        if(TextUtils.isEmpty(txtMail.getText()))
        {
            txtMail.setError("Email is required");
        } else if(TextUtils.isEmpty(txtPassword.getText()))
        {
            txtMail.setError("Password is required");
        } else
        {
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Loading ...");
            progress.show();

            ParseUser.logInInBackground(txtMail.getText().toString(), txtPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                progress.dismiss();
                if (parseUser != null) {
                    Toast.makeText(MainActivity.this, " Welcome back!", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    ParseUser.logOut();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        }


    }

    public void signup(View view) {
        Intent intent =new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
}
