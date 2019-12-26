package com.example.lablistview;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupOptionsListView();
        setupFavoritesListView();
    }

    private void setupOptionsListView(){

        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Intent intent=new Intent(MainActivity.this,Drinks_Options_Activity.class);
                if(position==0){
                    intent.putExtra("item",1);
                }
                else if(position==1){
                    intent.putExtra("item",3);
                }
                else if(position==2){
                    intent.putExtra("item",3);
                }
                startActivity(intent);

            }
        };
        //Add the listener to the list view
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setOnItemClickListener(itemClickListener);
    }

    private void setupFavoritesListView(){
        //Populate the list_favorites ListView from a cursor
        ListView listFavorites=(ListView)findViewById(R.id.list_favorites);
        try{
            SQLiteOpenHelper starrbuzzDatabaseHelper=new StarbuzzDatabaseHelper(this);
            db=starrbuzzDatabaseHelper.getReadableDatabase();
            favoritesCursor=db.query("DRINK",
                    new String[] {"_id","NAME"},
                    "FAVORITE=1",
                    null,null,null,null);
            CursorAdapter favoriteAdapter=new SimpleCursorAdapter(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    favoritesCursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},0);
            listFavorites.setAdapter(favoriteAdapter);
        }catch(SQLiteException e){
            Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT).show();
        }
        //Navigate to the DrinkActivity if a drink is clicked
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,Drink_Activity.class);
                intent.putExtra(Drink_Activity.EXTRA_DRINKID,(int)id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Cursor newCursor=db.query("DRINK",new String[]{"_id","NAME"},"FAVORITE=1",null,null,null,null);
        ListView listFavorites=(ListView)findViewById(R.id.list_favorites);
        CursorAdapter adapter=(CursorAdapter)listFavorites.getAdapter();
        adapter.changeCursor(newCursor);
        favoritesCursor=newCursor;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
