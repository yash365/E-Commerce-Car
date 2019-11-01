package com.example.ecommercecar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ecommercecar.R;
import com.example.ecommercecar.model.CarItem;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<CarItem> carList = new ArrayList<>();
        carList.add(new CarItem(R.drawable.ic_launcher_background, "BMW", "Rs.10,00,000/-"));
        carList.add(new CarItem(R.drawable.ic_launcher_background, "Mercedes", "Rs.20,00,000/-"));
        carList.add(new CarItem(R.drawable.ic_launcher_background, "Ford", "Rs.15,00,000/-"));
        carList.add(new CarItem(R.drawable.ic_launcher_background, "Nissan", "Rs.20,00,000/-"));
        carList.add(new CarItem(R.drawable.ic_launcher_background, "Honda", "Rs.2,00,000/-"));
        carList.add(new CarItem(R.drawable.ic_launcher_background, "Toyota", "Rs..10,00,000/-"));

    }
}
