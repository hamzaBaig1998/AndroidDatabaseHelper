package com.example.lablistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="starbuzz";/*Name of our database*/
    private static final int DB_VERSION=12;/*version of the database*/

    StarbuzzDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db,2,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("CHECK","IT WAS HERE!");
        updateMyDataBase(db,oldVersion,newVersion);
    }

    private static void insertDrink(SQLiteDatabase db,String name,String description,int resourceId){
        ContentValues drinkValues=new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        drinkValues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("DRINK",null,drinkValues);
    }
    private static void insertStore(SQLiteDatabase db,String name,String description,int resourceId){
        ContentValues drinkValues=new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        drinkValues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("STORE",null,drinkValues);
    }
    private static void insertFood(SQLiteDatabase db,String name,String description,int resourceId){
        ContentValues drinkValues=new ContentValues();
        drinkValues.put("NAME",name);
        drinkValues.put("DESCRIPTION",description);
        drinkValues.put("IMAGE_RESOURCE_ID",resourceId);
        db.insert("FOOD",null,drinkValues);
    }
    private void updateMyDataBase(SQLiteDatabase db,int oldVersion,int newVersion){
        if(newVersion<10){

//            db.execSQL("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//                    +"NAME TEXT,"+
//                    "DESCRIPTION TEXT,"+
//                    "IMAGE_RESOURCE_ID INTEGER);");
            //Table for store
            db.execSQL("CREATE TABLE STORE(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +"NAME TEXT,"+
                    "DESCRIPTION TEXT,"+
                    "IMAGE_RESOURCE_ID INTEGER);");
            //Table for Food
            db.execSQL("CREATE TABLE FOOD(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +"NAME TEXT,"+
                    "DESCRIPTION TEXT,"+
                    "IMAGE_RESOURCE_ID INTEGER);");
//            insertDrink(db,"Latte","Espresso, hot milk and steamed-milk foam",R.drawable.coffee_1);
//            insertDrink(db,"Cappucino","Espresso, hot milk and steamed-milk foam",R.drawable.coffee_2);
//            insertDrink(db,"Filter","Our best drip coffee",R.drawable.coffee_3);
            insertFood(db,"Burger","Amazing double patty burgers",R.drawable.coffee_1);
            insertFood(db,"Pizza","The most cheezious pizza in town",R.drawable.coffee_1);
            insertStore(db,"McDonalds","24/7 at your service",R.drawable.coffee_1);
            insertStore(db,"Pizza Hut","24/7 at your service",R.drawable.coffee_1);
        }
        if(newVersion<13){
            //db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
            db.execSQL("ALTER TABLE FOOD ADD COLUMN FAVORITE NUMERIC");
            db.execSQL("ALTER TABLE STORE ADD COLUMN FAVORITE NUMERIC");
        }
    }

}
