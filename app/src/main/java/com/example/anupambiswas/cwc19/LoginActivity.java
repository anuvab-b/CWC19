package com.example.anupambiswas.cwc19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.et_userName);
        password=(EditText)findViewById(R.id.et_password);
        login=(Button)findViewById(R.id.btn_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((username.getText().toString().equals("Admin"))&&(password.getText().toString().equals("123454")))
                {
                    Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                }
                else
                    Toast.makeText(LoginActivity.this,"Login Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });

    }
}
