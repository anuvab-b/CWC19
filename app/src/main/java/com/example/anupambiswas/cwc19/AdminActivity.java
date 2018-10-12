package com.example.anupambiswas.cwc19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    EditText playerName,dob,country,matches,role,runs,wickets,rating,pid;
    Button adddata,updatedata,deletedata;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        myDb=new DatabaseHelper(this);

        playerName=findViewById(R.id.et_playerName);
        dob=findViewById(R.id.et_DOB);
        country=findViewById(R.id.et_Country);
        matches=findViewById(R.id.et_Matches);
        runs=findViewById(R.id.et_Runs);
        role=findViewById(R.id.et_Role);
        wickets=findViewById(R.id.et_Wickets);
        rating=findViewById(R.id.et_Ranking);
        pid=findViewById(R.id.et_ID);
        adddata=findViewById(R.id.btn_Add);
        updatedata=findViewById(R.id.btn_Update);
        deletedata=findViewById(R.id.btn_Delete);
        AddData();
        UpdateData();
        DeleteData();



    }
    public void DeleteData()
    {
        deletedata.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        Integer deletedRows=myDb.deleteData(pid.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(AdminActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AdminActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData()
    {
        updatedata.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        boolean isUpdated=myDb.updatePlayerData(pid.getText().toString(),country.getText().toString(),playerName.getText().toString(),
                                dob.getText().toString(), rating.getText().toString(),role.getText().toString(),matches.getText().toString(),
                                runs.getText().toString(),wickets.getText().toString());
                        if(isUpdated==true)
                        {
                            Toast.makeText(AdminActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(AdminActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData()
    {
        adddata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted=myDb.insertPlayerData(playerName.getText().toString(),country.getText().toString(),dob.getText().toString(),
                                rating.getText().toString(),role.getText().toString(),matches.getText().toString(),runs.getText().toString(),
                                wickets.getText().toString());
                        if(isInserted==true)
                            Toast.makeText(AdminActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AdminActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
