package com.example.anupambiswas.cwc19;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PlayerInfoActivity extends AppCompatActivity {
    CheckBox player,ratings,role,country,matches,runs,wickets;
    EditText getCountry,getRole;
    Button submit;
    DatabaseHelper myDb;
    RadioButton select;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_info);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup2);
        myDb = new DatabaseHelper(this);
        player=(CheckBox)findViewById(R.id.cb_Player);
        ratings=(CheckBox)findViewById(R.id.cb_Ratings);
        role=(CheckBox)findViewById(R.id.cb_Role);
        country=(CheckBox)findViewById(R.id.cb_Country);
        matches=(CheckBox)findViewById(R.id.cb_Matches);
        runs=(CheckBox)findViewById(R.id.cb_Runs);
        wickets=(CheckBox)findViewById(R.id.cb_Wickets);
        getCountry=(EditText)findViewById(R.id.et_playerName);
        getRole=(EditText)findViewById(R.id.et_Wickets);
        submit=(Button)findViewById(R.id.btn_Submit);
        viewPlayers();

    }


    public void viewPlayers()
    {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PlayerInfoActivity.this, "This is Player view button", Toast.LENGTH_LONG).show();


                int selectedId = radioGroup.getCheckedRadioButtonId();
                select = (RadioButton) findViewById(selectedId);
                String groupBy = "RATING DESC";
                if (selectedId == -1) {
                    Toast.makeText(PlayerInfoActivity.this, "Nothing selected", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PlayerInfoActivity.this, select.getText(), Toast.LENGTH_SHORT).show();
                    groupBy = select.getText() + " DESC";
                }


                String col[] = new String[8];
                int count = 0;
                col[count++]="PID";
                if (player.isChecked()) {
                    col[count] = "NAME";
                    count++;
                }
                if (country.isChecked()) {
                    col[count] = "COUNTRY";
                    count++;
                }
                if (ratings.isChecked()) {
                    col[count] = "RATING";
                    count++;
                }
                if (role.isChecked()) {
                    col[count] = "ROLE";
                    count++;
                }
                if (matches.isChecked()) {
                    col[count] = "MATCHES";
                    count++;
                }
                if (runs.isChecked()) {
                    col[count] = "RUNS";
                    count++;
                }
                if (wickets.isChecked()) {
                    col[count] = "WICKETS";
                }
                Cursor res=null;
                String countryValue = getCountry.getText().toString();
                String roleValue = getRole.getText().toString();
                String selection = "";
                //String selectionArgs[] = new String[2];
                if(country.isChecked()||role.isChecked())//Either checkbox Checked
                {
                if (countryValue.length() == 0 && roleValue.length() != 0) {
                    selection = "ROLE = ? ";
                    String selectionArgs[] = new String[1];
                    selectionArgs[0] = roleValue;
                    res = myDb.getAllData(col, selection, selectionArgs, groupBy);
                } else if (countryValue.length() != 0 && roleValue.length() == 0) {
                    selection ="COUNTRY = ?";
                    String selectionArgs[] = new String[1];
                    selectionArgs[0] = countryValue;
                    res = myDb.getAllData(col, selection, selectionArgs, groupBy);
                } else if(countryValue.length() != 0 && roleValue.length() != 0) {
                    selection = "ROLE = ? AND COUNTRY = ? ";
                    String selectionArgs[] = new String[2];
                    selectionArgs[0] = roleValue;
                    selectionArgs[1] = countryValue;
                    res = myDb.getAllData(col, selection, selectionArgs, groupBy);
                }
                //res = myDb.getAllData(col, selection, selectionArgs, groupBy);
                }
                else if(country.isChecked()==false&&role.isChecked()==false&count!=1)
                {
                    res = myDb.getAllData(col, null, null, groupBy);
                }
                else if(count==1)
                {
                    col[count]="*";
                    res = myDb.getAllData(new String[]{"*"}, null, null, groupBy);
                }
                //Display Data
                    if (res.getCount() == 0) {
                        showMessage("Error", "Nothing found");
                        return;
                    }
                    else {
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            for (int i = 0; i <count; i++) {
                                //buffer.append("PID: "+res.getString(0)+"\n");
                                buffer.append(res.getColumnName(i)+" : "+res.getString(i) + "\n");
                            }
                            buffer.append("\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
            });
        }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);//arg is context
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
