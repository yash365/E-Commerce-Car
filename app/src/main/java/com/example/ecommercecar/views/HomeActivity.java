package com.example.ecommercecar.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.ecommercecar.R;
import com.example.ecommercecar.adapter.CarAdapter;
import com.example.ecommercecar.database.CarContract;
import com.example.ecommercecar.database.CarDBHelper;
import com.example.ecommercecar.model.CarItem;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private CarAdapter carAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ImageButton addButton;
    private SQLiteDatabase mDatabase;

    public static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ArrayList<CarItem> carList = new ArrayList<>();

        CarDBHelper dbHelper = new CarDBHelper(this, CarDBHelper.DATABASE_NAME, null, CarDBHelper.DATABASE_VERSION);
        mDatabase = dbHelper.getWritableDatabase();

        // adding dummy data
        carList.add(new CarItem(R.drawable.ford_mustang, "50000", "2005", "3000 km used", "Ford Mustang", "Sunil", "Mumbai"));
        carList.add(new CarItem(R.drawable.audi_r8, "10000", "2015", "3000 km used", "Audi R8", "Bharti", "Hyderabad"));
        carList.add(new CarItem(R.drawable.jeepsi, "100000", "2009", "1000 km used", "Jeepsi", "Manish", "Manali"));
        carList.add(new CarItem(R.drawable.mitsubishi_lancer, "45000", "2011", "3250 km used", "Mitubushi Lancer", "Shekha", "Mumbai"));
        carList.add(new CarItem(R.drawable.nissan_skyline, "400000", "2013", "1000 km used", "Nissan Skyline", "John", "Bangalore"));
        carList.add(new CarItem(R.drawable.rolls_royce, "2500000", "2016", "3000 km used", "Rolls Royce", "Surendra", "Delhi"));

        mRecyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        //mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        carAdapter = new CarAdapter(this, carList);

        // setting linear layout
        mRecyclerView.setAdapter(carAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        addButton.setOnClickListener(this);

        Log.d(TAG, "onCreate");
    }

//    public Cursor getAllItems(){
//        return mDatabase.query(
//                CarContract.CarEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                CarContract.CarEntry.COLUMN_TIMESTAMP + " DESC"
//        );
//    }

    @Override
    public void onClick(View v) {
        if(v == addButton){
            Intent intent = new Intent(this, CreateAdActivity.class);
            startActivity(intent);

            Log.d(TAG, "onClick addButton");
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
