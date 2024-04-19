package com.rachana.Cosmei;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import com.rachana.Cosmei.adapter.ProductAdapter;
import com.rachana.Cosmei.adapter.ProductCategoryAdapter;
import com.rachana.Cosmei.model.ProductCategory;
import com.rachana.Cosmei.model.Products;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ProductCategoryAdapter productCategoryAdapter;
    RecyclerView productCatRecycler, prodItemRecycler;
    ProductAdapter productAdapter;

    Button cart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        cart = findViewById(R.id.cartBtn);

        List<ProductCategory> productCategoryList = new ArrayList<>();
        productCategoryList.add(new ProductCategory(1, "Trending"));
        productCategoryList.add(new ProductCategory(2, "Most Popular"));
        productCategoryList.add(new ProductCategory(4, "Skin Care"));
        productCategoryList.add(new ProductCategory(5, "Hair Care"));
        productCategoryList.add(new ProductCategory(6, "Make Up"));

        setProductRecycler(productCategoryList);


        List<Products> productsList = new ArrayList<>();
        productsList.add(new Products(1, "Cerave hydrating cleanse", "355 ml", 3500.00F, R.drawable.ceraaaa, "CeraVe Hydrating Cleanser is a gentle and effective skincare product designed to cleanse and hydrate the skin. It is specifically formulated for normal to dry skin types, making it suitable for individuals with sensitive or easily irritated skin."));
        productsList.add(new Products(2, "Eco Styling Gel", "470 ml", 600.30F, R.drawable.ettt, "Eco Styling Gel is a popular hair styling product that offers a strong hold and long-lasting control for various hair types and styles. It is known for its unique formula that provides excellent hold without causing excessive stiffness or flaking."));
        productsList.add(new Products(1, "Neutrogena SPF Sunscreen", "30 ml", 1400.50F, R.drawable.niz,"Neutrogena SPF Sunscreen is a trusted and widely recognized brand of sunscreen that provides effective protection against harmful UVA and UVB rays from the sun. It is designed to help prevent sunburn and protect the skin from the damaging effects of sun exposure, including premature aging and increased risk of skin cancer."));
        productsList.add(new Products(2, "RareBeauty Illuminating Primer", "28 ml", 999.50F, R.drawable.newtwo,"Rare Beauty Illuminating Primer is a makeup primer specifically designed to create a luminous and radiant base for your foundation or other makeup products. It is part of the Rare Beauty cosmetics line created by Selena Gomez, known for its focus on enhancing natural beauty and promoting self-acceptance."));
        productsList.add(new Products(1, "Sephora Definition Mascara", "20 ml",530.00F, R.drawable.mas,"The Sephora Collection LashCraft Length & Volume Mascara is designed to provide both length and volume to your lashes. It features a unique formula that coats each lash from root to tip, helping to create a fuller and more dramatic effect."));
        productsList.add(new Products(2, "Fenty Foundation", "30 ml", 2300.50F, R.drawable.fon,"The Fenty Pro Filt'r Soft Matte Longwear Foundation is designed to provide a matte finish with a buildable medium to full coverage. It is formulated to be long-wearing, helping to create a smooth and flawless complexion that lasts throughout the day."));
        setProdItemRecycler(productsList);

        cart.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, cartactivity1.class);
            startActivity(intent);

        });
    }

    private void setProductRecycler(List<ProductCategory> productCategoryList){

        productCatRecycler = findViewById(R.id.cat_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        productCatRecycler.setLayoutManager(layoutManager);
        productCategoryAdapter = new ProductCategoryAdapter(this, productCategoryList);
        productCatRecycler.setAdapter(productCategoryAdapter);

    }

    private void setProdItemRecycler(List<Products> productsList){

        prodItemRecycler = findViewById(R.id.product_recycler);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        prodItemRecycler.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(this, productsList);
        prodItemRecycler.setAdapter(productAdapter);

    }





}