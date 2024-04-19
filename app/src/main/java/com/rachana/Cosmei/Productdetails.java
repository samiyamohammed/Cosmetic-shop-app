package com.rachana.Cosmei;// Inside Productdetails activity

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rachana.Cosmei.model.Products;

public class Productdetails extends AppCompatActivity {
    Button button2;
    Button btnincre, btndecre;
    TextView counterval;
    int count = 0;
    Products selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetails);

        // Retrieve the selected product details from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedProduct")) {
            selectedProduct = (Products) intent.getSerializableExtra("selectedProduct");

            // Display the product details in your layout components
            ImageView productImage = findViewById(R.id.imageView7);
            TextView productName = findViewById(R.id.textView11);
            TextView productQty = findViewById(R.id.textView13);
            TextView productPrice = findViewById(R.id.textView12);

            productImage.setImageResource(selectedProduct.getImageUrl());
            productName.setText(selectedProduct.getProductName());
            productQty.setText(selectedProduct.getProductDescription());
            productPrice.setText(selectedProduct.getProductPrice()+" ETB");
        }

        button2 = findViewById(R.id.button2);
        btnincre = findViewById(R.id.btnincrement);
        btndecre = findViewById(R.id.btndecrement);
        counterval = findViewById(R.id.counter);


        btnincre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                counterval.setText(String.valueOf(count));
            }
        });

        btndecre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                counterval.setText(String.valueOf(count));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart(selectedProduct, count);
                Toast.makeText(Productdetails.this, "Your item has been added!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart(Products product, int quantity) {
        // Add the selected product and quantity to your database (DHelper)
        DBhelper DB = new DBhelper(this);
        DB.addData(product, quantity);
    }
}
