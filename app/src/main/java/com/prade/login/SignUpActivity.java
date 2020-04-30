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
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText txtName,txtMail,txtPassword,txtConfmPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        txtName = findViewById(R.id.edName);
        txtMail = findViewById(R.id.edEmail);
        txtPassword = findViewById(R.id.edPassword);
        txtConfmPwd = findViewById(R.id.edConfirmPassword);

    }

    public void signup(View view) {
        if(TextUtils.isEmpty(txtName.getText()))
        {
            txtName.setError("Name is required");
        } else
            if(TextUtils.isEmpty(txtMail.getText()))
        {
            txtMail.setError("Email is required");
        } else if(TextUtils.isEmpty(txtPassword.getText()))
        {
            txtPassword.setError("Password is required");
        } else if(TextUtils.isEmpty(txtConfmPwd.getText())) {
                txtConfmPwd.setError("Confirm Password is required");
            }else if(!txtPassword.getText().toString().equals(txtConfmPwd.getText().toString())){
                Toast.makeText(SignUpActivity.this, " Passwords are not same!", Toast.LENGTH_LONG).show();
            }else {
                final ProgressDialog progress = new ProgressDialog(this);
                progress.setMessage("Loading ...");
                progress.show();

                ParseUser user = new ParseUser();
// Set the user's username and password, which can be obtained by a forms
                user.setUsername(txtName.getText().toString().trim());
                user.setEmail(txtMail.getText().toString().trim());
                user.setPassword(txtPassword.getText().toString().trim());
                user.put("name", txtName.getText().toString().trim());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        progress.dismiss();
                        if (e == null) {
                            //alertDisplayer("Sucessful Sign Up!","Welcome" + <Insert Username Here> + "!");
                            Toast.makeText(SignUpActivity.this, " Welcome !", Toast.LENGTH_LONG).show();
                            Intent intent =new Intent(SignUpActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            ParseUser.logOut();
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }


    }
}
