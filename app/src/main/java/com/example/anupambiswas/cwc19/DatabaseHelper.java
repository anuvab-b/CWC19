package com.example.anupambiswas.cwc19;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by ANUVAB BISWAS on 29-Sep-18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CWC.db";
    public static final String TABLE_PLAYER = "player_table";
    public static final String TABLE_SCHEDULE = "schedule_table";
    public static final String TABLE_STANDINGS = "standings_table";
    public static final String PCOL_1 = "ID";
    public static final String PCOL_2 = "NAME";
    public static final String PCOL_3 = "COUNTRY";
    public static final String PCOL_4 = "DOB";
    public static final String PCOL_5 = "RATING";
    public static final String PCOL_6 = "ROLE";
    public static final String PCOL_7 = "MATCHES";
    public static final String PCOL_8 = "RUNS";
    public static final String PCOL_9 = "WICKETS";

    public static final String SHCOL_1 = "ID";
    public static final String SHCOL_2 = "TEAM1";
    public static final String SHCOL_3 = "TEAM2";
    public static final String SHCOL_4 = "VENUE";
    public static final String SHCOL_5 = "RESULT";

    public static final String STCOL_1 = "POS";
    public static final String STCOL_2 = "TEAM";
    public static final String STCOL_3 = "PLAYED";
    public static final String STCOL_4 = "WINS";
    public static final String STCOL_5 = "DRAWS";
    public static final String STCOL_6 = "LOSSES";
    public static final String STCOL_7 = "POINTS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db=this.getWritableDatabase();Creates Database and Table and view it
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_PLAYER + " (PID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,COUNTRY TEXT,DOB TEXT,RATING TEXT,ROLE TEXT,MATCHES TEXT,RUNS TEXT,WICKETS TEXT)");
        db.execSQL("create table " + TABLE_SCHEDULE + " (SID INTEGER PRIMARY KEY AUTOINCREMENT,TEAM1 TEXT,TEAM2 TEXT,VENUE TEXT,RESULT TEXT)");
        db.execSQL("create table " + TABLE_STANDINGS + " (SID INTEGER PRIMARY KEY AUTOINCREMENT,TEAM TEXT,PLAYED TEXT,WINS TEXT,DRAWS TEXT,LOSSES TEXT,POINTS TEXT)");
        setDefaultLabel(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STANDINGS);
        onCreate(db);
    }

    public void setDefaultLabel(SQLiteDatabase db) {
        //db.execSQL("INSERT INTO TABLE_PLAYER(ID,NAME,COUNTRY,DOB,RATING,ROLE,MATCHES,RUNS,WICKETS) VALUES('1','Virat Kohli','India','09/06/1998','1','Batsman','135','8000','1')");
        //db.execSQL("INSERT INTO TABLE_PLAYER(ID,NAME,COUNTRY,DOB,RATING,ROLE,MATCHES,RUNS,WICKETS) VALUES('2','Eoin Morgan','England','09/06/1988','11','Batsman','150','6000','0')");
        String value[][] = {
                {"Virat Kohli,India,09/06/1998,1,Batsman,135,8000,1"},
                {"Rohit Sharma,India,09/06/1998,2,Batsman,120,7000,11"},
                {"Rishabh Pant,India,09/06/1998,27,Wicket Keeper,15,350,0"},
                {"Dinesh Karthik,India,09/06/1988,40,Batsman,100,2700,0"},
                {"Hardik Pandya,India,09/06/1998,7,All-Rounder,50,1100,53"},
                {"Kuldeep Yadav,India,09/06/1998,15,Bowler,25,130,43"},
                {"Jaspreet Bumrah,India,09/06/1998,9,Bowler,30,37,37"},
                {"Eoin Morgan,England,09/06/1988,11,Batsman,150,6000,0"},
                {"Alex Hales,England,09/06/1998,23,Batsman,70,2500,0"},
                {"Jonny Bairstow,England,09/06/1998,9,Batsman,67,2000,0"},
                {"Jos Buttler,England,09/06/1998,18,Wicket Keeper,70,2500,0"},
                {"David Willey,England,09/06/1998,20,Bowler,50,370,67"},
                {"Adil Rashid,England,09/06/1998,35,All-Rounder,45,290,64"},
                {"Mark Wood,England,09/06/1998,40,Bowler,30,67,37"},
                {"Faf du Plessis,South Africa,09/06/1998,15,Batsman,100,4000,0"},
                {"Hashim Amla,South Africa,09/06/1998,4,Batsman,200,8000,0"},
                {"Aiden Markram,South Africa,09/06/1998,53,Batsman,17,360,0"},
                {"Quinton De Cock,South Africa,09/06/1998,6,Wicket Keeper,100,3900,0"},
                {"JP Duminy,South Africa,09/06/1998,17,All-Rounder,120,4000,57"},
                {"Dale Steyn,South Africa,09/06/1998,11,Bowler,110,230,178"},
                {"Imran Tahir,South Africa,09/06/1998,9,Bowler,50,67,83"},
                {"Aaron Finch,Australia,09/06/1998,13,Batsman,50,1900,3"},
                {"Chris Lynn,Australia,09/06/1998,60,Batsman,7,200,0"},
                {"Glenn Maxwell,Australia,09/06/1998,35,All-Rounder,60,1300,45"},
                {"Josh Hazelwood,Austraila,09/06/1998,7,Bowler,40,34,58"},
                {"Patric Cummins,Australia,09/06/1998,17,Bowler,17,25,27"},
                {"Mitchell Starc,Australia,09/06/1998,1,Bowler,75,338,127"},
        };
        int rows = value.length;
        for (int i = 0; i < rows; i++) {
            String val = value[i][0];
            String array[] = val.split(",");
            fillData(db, array);
        }
    }
    public void fillData(SQLiteDatabase db,String array[])
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PCOL_2, array[0]);
        contentValues.put(PCOL_3, array[1]);
        contentValues.put(PCOL_4, array[2]);
        contentValues.put(PCOL_5, array[3]);
        contentValues.put(PCOL_6, array[4]);
        contentValues.put(PCOL_7, array[5]);
        contentValues.put(PCOL_8, array[6]);
        contentValues.put(PCOL_9, array[7]);
        db.insert(TABLE_PLAYER, null, contentValues);
    }
    public boolean insertPlayerData(String name,String country,String dob,String rating,String role,String matches,String runs,String wickets)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(PCOL_2,name);
        contentValues.put(PCOL_3,country);
        contentValues.put(PCOL_4,dob);
        contentValues.put(PCOL_5,rating);
        contentValues.put(PCOL_6,role);
        contentValues.put(PCOL_7,matches);
        contentValues.put(PCOL_8,runs);
        contentValues.put(PCOL_9,wickets);
        long val=db.insert(TABLE_PLAYER,null,contentValues);
        if(val==-1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(String columns[],String selection,String selectionArgs[],String Orderby){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_PLAYER,null);
        String table="TABLE_PLAYER";
        String groupBy = null;
        String having = null;
        String limit=null;
        Cursor cursor = db.query("player_table", columns, selection, selectionArgs, groupBy, having, Orderby, limit);
        return cursor;
    }
    public boolean updatePlayerData(String id,String country,String name,String dob,String rating,String role,String matches,String runs,String wickets) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PCOL_2, name);
        contentValues.put(PCOL_3, country);
        contentValues.put(PCOL_4, dob);
        contentValues.put(PCOL_5, rating);
        contentValues.put(PCOL_6, role);
        contentValues.put(PCOL_7, matches);
        contentValues.put(PCOL_8, runs);
        contentValues.put(PCOL_9, wickets);
        db.update(TABLE_PLAYER, contentValues, "ID = ?", new String[]{id});
        return true;
    }
    public void getrows()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        int numRows = (int) DatabaseUtils.longForQuery(db, "SELECT COUNT(*) FROM TABLE_PLAYER", null);
        Log.d("myTag", "This is my message"+numRows);
    }
    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_PLAYER,"ID=?",new String[]{id});
    }
    /**
     * This function used to select the records from DB.
     * @param tableName
     * @param tableColumns
     * @param whereClase
     * @param whereArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return A Cursor object, which is positioned before the first entry.
     */
    public Cursor selectRecordsFromDB(String tableName, String[] tableColumns,String whereClase, String whereArgs[], String groupBy,String having, String orderBy)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.query(tableName, tableColumns, whereClase, whereArgs,groupBy, having, orderBy);
    }

    /**
     * select records from db and return in list
     * @param tableName
     * @param tableColumns
     * @param whereClase
     * @param whereArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @return ArrayList<ArrayList<String>>
     */
    public ArrayList<ArrayList<String>> selectRecordsFromDBList(String tableName, String[] tableColumns,String whereClase, String whereArgs[], String groupBy,String having, String orderBy)
    {

        ArrayList<ArrayList<String>> retList = new ArrayList<ArrayList<String>>();
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = db.query(tableName, tableColumns, whereClase, whereArgs,
                groupBy, having, orderBy);
        if (cursor.moveToFirst())
        {
            do
            {
                list = new ArrayList<String>();
                for(int i=0; i<cursor.getColumnCount(); i++)
                {
                    list.add( cursor.getString(i) );
                }
                retList.add(list);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return retList;

    }
}
