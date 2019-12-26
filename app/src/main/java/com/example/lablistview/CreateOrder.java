package com.example.lablistview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateOrder extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Spinner category=(Spinner) findViewById(R.id.spinner);

        final Spinner spinner=(Spinner) findViewById(R.id.spinner2);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(category.getSelectedItem().toString().equals(Drink.class.getSimpleName())){
                    ArrayAdapter<Drink> arrayAdapter=new ArrayAdapter<Drink>(CreateOrder.this,android.R.layout.simple_spinner_item,Drink.drinks);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);
                }
                else if(category.getSelectedItem().toString().equals(Food.class.getSimpleName())){
                    ArrayAdapter<Food> arrayAdapter=new ArrayAdapter<Food>(CreateOrder.this,android.R.layout.simple_spinner_item,Food.food);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);
                }
                else if(category.getSelectedItem().toString().equals(Store.class.getSimpleName())){
                    ArrayAdapter<Store> arrayAdapter=new ArrayAdapter<Store>(CreateOrder.this,android.R.layout.simple_spinner_item,Store.store);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setOrder(View v){
        Toast.makeText(this," Order ",Toast.LENGTH_LONG).show();
    }
}
