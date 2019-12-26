package com.example.lablistview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Drinks_Options_Activity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks__options_);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int item=getIntent().getExtras().getInt("item");
        String tablesName="";

        if(item==1){
            tablesName="DRINK";
        }
        else if(item==2){
            tablesName="FOOD";
        }
        else if(item==3){
            tablesName="STORE";
        }

        SQLiteOpenHelper starrbuzzDatabaseHelper=new StarbuzzDatabaseHelper(this);
        ListView listDrinks=(ListView)findViewById(R.id.list_drinks);
        try{
            db=starrbuzzDatabaseHelper.getReadableDatabase();
            cursor=db.query(tablesName,new String[]{"_id","NAME"},null,null,null,null,null);
            SimpleCursorAdapter listAdapter=new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},0);
            listDrinks.setAdapter(listAdapter);
        }catch(SQLiteException e){
            Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT).show();
        }

        //Create the listener
        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Pass the drink to Drink_Activity when user clicks
                Intent intent=new Intent(Drinks_Options_Activity.this,Drink_Activity.class);
                intent.putExtra(Drink_Activity.EXTRA_DRINKID,(int)id);
                intent.putExtra("item",item);
                startActivity(intent);
            }
        };
        listDrinks.setOnItemClickListener(itemClickListener);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu; this adds items to the app bar. */
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_create_order:
                Intent intent=new Intent(this,CreateOrder.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
