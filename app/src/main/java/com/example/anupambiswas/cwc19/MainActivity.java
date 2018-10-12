package com.example.anupambiswas.cwc19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnPlayers,btnSchedule,btnStandings;
    TextView welcomeView,adminLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        btnPlayers=(Button)findViewById(R.id.btn_Play);
        btnSchedule = (Button) findViewById(R.id.btn_Schedule);
        btnStandings = (Button) findViewById(R.id.btn_Standings);
        adminLogin = (TextView) findViewById(R.id.adminlog);
        viewPlayers();
        logAdmin();
        btnSchedule.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Under Maintenance",Toast.LENGTH_LONG).show();
            }
        });
        btnStandings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Under Maintenance",Toast.LENGTH_LONG).show();
            }
        });
    }

        public void viewPlayers()
        {
            btnPlayers.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, PlayerInfoActivity.class));
                }
            });
        }
        public void logAdmin()
        {
            adminLogin.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            });
        }
    /*public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);//arg is context
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/
}
