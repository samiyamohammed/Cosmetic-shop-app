package com.rachana.Cosmei;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class cartactivity1 extends AppCompatActivity {
    private static final String TAG = "ListDataActivity";
    DBhelper DB;
    ListView lv;
    EditText username1;
    Button checkout;
    String product;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartactivity1);
        lv=(ListView)findViewById(R.id.listView);
        DB=new DBhelper(this);
        username1=(EditText)findViewById(R.id.username1) ;
        populateListview();

        checkout=findViewById(R.id.checkBtn);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cartactivity1.this, OrderActivity.class);
                startActivity(intent);
            }
        });

    }



    private void populateListview() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        Cursor data = DB.getData();
        ArrayList<String> listdata = new ArrayList<>();

        while (data.moveToNext()) {
            // Assuming your database schema has columns for product name, price, and quantity
            String productName = data.getString(data.getColumnIndex("productName"));
            int quantity = data.getInt(data.getColumnIndex("quantity"));

            // Assuming there is a column for the price as well
            double price = data.getDouble(data.getColumnIndex("price"));

            // Modify the string to include product name, price, and quantity
            String itemDetails = productName + "\nPrice: " + price + "ETB \nQuantity: " + quantity+ "\nTotal Price: "+ price*quantity+" ETB";

            listdata.add(itemDetails);
        }

        // Use a custom layout for the adapter if needed, for example, a custom list item layout
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listdata);

        lv.setAdapter(adapter);
    }

}