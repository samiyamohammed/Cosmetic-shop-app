package com.rachana.Cosmei;// OrderActivity.java

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class OrderActivity extends AppCompatActivity {

    private TextView totalAmountTextView;
    private EditText cardNumberEditText, expiryDateEditText, cvvEditText;
    private Button placeOrderButton, cancelButton;
    DBhelper DB = new DBhelper(this);

    Float TotalCart=00.00F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        totalAmountTextView = findViewById(R.id.textView3);
        cardNumberEditText = findViewById(R.id.cardNumberEditText);
        expiryDateEditText = findViewById(R.id.expiryDateEditText);
        cvvEditText = findViewById(R.id.cvvEditText);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Get total amount from the previous activity (you should pass it through Intent)

        populateListview();
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement your logic for processing the order
                // Retrieve card information and proceed accordingly
                // Example: Get card information
                String cardNumber = cardNumberEditText.getText().toString();
                String expiryDate = expiryDateEditText.getText().toString();
                String cvv = cvvEditText.getText().toString();

                Toast.makeText(OrderActivity.this, "Your Order is Placed!", Toast.LENGTH_SHORT).show();
                DB.clearCart();
                finish();
                // Add your logic for processing the order
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the cancel button click (e.g., go back to the previous activity)
                finish();
            }
        });
    }



    @SuppressLint("SetTextI18n")
    private void populateListview() {
        Cursor data = DB.getData();
        ArrayList<String> listdata = new ArrayList<>();

        while (data.moveToNext()) {
            // Assuming your database schema has columns for product name, price, and quantity
            String productName = data.getString(data.getColumnIndex("productName"));
            int quantity = data.getInt(data.getColumnIndex("quantity"));
            // Assuming there is a column for the price as well
            double price = data.getDouble(data.getColumnIndex("price"));


            TotalCart= (float) (TotalCart+(price*quantity));
        }
        if(TotalCart==0.00F){
            finish();
        }
        else totalAmountTextView.setText(TotalCart.toString());


    }
}
