package com.example.lablistview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Drink_Activity extends AppCompatActivity {
    public static final String EXTRA_DRINKID="drinkId";
    static String tablesName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_);

        /*Get drink id from intent*/
        int drinkId=(Integer)getIntent().getExtras().get(EXTRA_DRINKID);

        /*Toolbar support*/
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*Create a cursor*/

        final int item=getIntent().getExtras().getInt("item");


        if(item==1){
            tablesName="DRINK";
        }
        else if(item==2){
            tablesName="FOOD";
        }
        else if(item==3){
            tablesName="STORE";
        }


        SQLiteOpenHelper starbuzzDatabaseHelper=new StarbuzzDatabaseHelper(this);
        try {
            SQLiteDatabase db=starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor=db.query(tablesName,new String[] {"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","FAVORITE"},"_id=?",new String[]{Integer.toString(drinkId)},null,null,null);
            /*Move to the first record in the cursor*/
            if(cursor.moveToFirst()){
                //Get the drink details from the cursor
                String nameText=cursor.getString(0);
                String descriptionText=cursor.getString(1);
                int photoId=cursor.getInt(2);
                boolean isFavorite=(cursor.getInt(3)==1);
                TextView name=(TextView)findViewById(R.id.name);
                name.setText(nameText);
                TextView description=(TextView)findViewById(R.id.description);
                description.setText(descriptionText);
                ImageView photo=(ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                CheckBox favorite=(CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);

            }
        }catch(SQLiteException e){
            Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT).show();
        }

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //Update the database when the checkbox is clicked
    public void onFavoriteClicked(View view){
        int drinkId=(Integer) getIntent().getExtras().get(EXTRA_DRINKID);
        new UpdateDrinkTask().execute(drinkId);
    }

    //Inner class to update the drink.
    private class UpdateDrinkTask extends AsyncTask<Integer,Void,Boolean>{
        private ContentValues drinkValues;

        @Override
        protected void onPreExecute() {
            CheckBox favorite=(CheckBox)findViewById(R.id.favorite);
            drinkValues=new ContentValues();
            drinkValues.put("FAVORITE",favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            int drinkId=drinks[0];
            SQLiteOpenHelper starbuzzDatabaseHelper=new StarbuzzDatabaseHelper(Drink_Activity.this);
            try{
                SQLiteDatabase db=starbuzzDatabaseHelper.getWritableDatabase();
                db.update(Drink_Activity.tablesName,drinkValues,"_id=?",new String[]{Integer.toString(drinkId)});
                db.close();
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if(!success){
                Toast.makeText(Drink_Activity.this,"Database unavailable",Toast.LENGTH_SHORT).show();
            }
        }
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
