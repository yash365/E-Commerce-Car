package com.example.ecommercecar.database;

import android.provider.BaseColumns;

public class CarContract {

    private CarContract() {}

    public static final class CarEntry implements BaseColumns {
        public static final String TABLE_NAME = "carList";
        public static final String COLUMN_CAR_IMAGE = "car_image";
        public static final String COLUMN_CAR_NAME = "car_name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_MODEL_YEAR = "model_year";
        public static final String COLUMN_KM_USED = "km_used";
        public static final String COLUMN_SELLER_NAME = "seller_name";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }
}
