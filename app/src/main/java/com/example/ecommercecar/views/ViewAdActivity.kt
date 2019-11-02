package com.example.ecommercecar.views

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log

import com.example.ecommercecar.R
import kotlinx.android.synthetic.main.activity_view_ad.*

class ViewAdActivity : AppCompatActivity() {

    val TAG = "ViewAdActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ad)

        val intent = intent

        val carImageValue = intent.getIntExtra("car-image", 0)
        val carNameValue = intent.getStringExtra("car-name")
        val carPriceValue = intent.getStringExtra("car-price")
        val modelYearValue = intent.getStringExtra("model-year")
        val kmUsedValue = intent.getStringExtra("km-used")
        val sellerNameValue = intent.getStringExtra("seller-name")
        val locationValue = intent.getStringExtra("location")

        carImage.setImageResource(carImageValue)
        carName.setText("Car name: $carNameValue")
        carPrice.setText("Price: Rs. $carPriceValue")
        modelYear.setText("Model Year: $modelYearValue")
        kmUsed.setText("$kmUsedValue")
        sellerName.setText("Seller Name: $sellerNameValue")
        location.setText("City: $locationValue")

        Log.d(TAG, "onCreate")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent: Intent = Intent(this@ViewAdActivity, HomeActivity::class.java)
        startActivity(intent)
    }
}
