package com.example.ecommercecar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ecommercecar.R;
import com.example.ecommercecar.adapter.CarAdapter;
import com.example.ecommercecar.database.CarContract;
import com.example.ecommercecar.database.CarDBHelper;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class CreateAdActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn, addButton;
    private ImageView imageview;
    private static final String IMAGE_DIRECTORY = "/ecommercecar";
    private int GALLERY = 1, CAMERA = 2;
    private SQLiteDatabase mDatabase;
    private EditText carNameTextValue, carPriceTextValue, carModelYearTextValue, carKmUsedTextValue, carSellerNameTextValue, carCityTextValue;
    ByteArrayOutputStream stream;
    CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ad);

        // db helper
        CarDBHelper dbHelper = new CarDBHelper(this, CarDBHelper.DATABASE_NAME, null, CarDBHelper.DATABASE_VERSION);
        mDatabase = dbHelper.getWritableDatabase();
        //carAdapter = new CarAdapter(this, HomeActivity.carList, getAllItems());

        btn = (Button) findViewById(R.id.btnChoose);
        imageview = (ImageView) findViewById(R.id.iv);
        addButton = (Button) findViewById(R.id.addButton);

        carNameTextValue = (EditText) findViewById(R.id.carNameTextValue);
        carPriceTextValue = (EditText) findViewById(R.id.carPriceTextValue);
        carModelYearTextValue = (EditText) findViewById(R.id.carModelYearTextValue);
        carKmUsedTextValue = (EditText) findViewById(R.id.carKmUsedTextValue);
        carSellerNameTextValue = (EditText) findViewById(R.id.carSellerNameTextValue);
        carCityTextValue = (EditText) findViewById(R.id.carCityTextValue);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMultiplePermissions();
                showPictureDialog();
            }
        });

        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == addButton){
            addItem();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void addItem(){
        // checking if not to accept space value
        if( carNameTextValue.getText().toString().trim().length() == 0 ||
            carPriceTextValue.getText().toString().equals("0") ||
            carModelYearTextValue.getText().toString().equals("0") ||
            carSellerNameTextValue.getText().toString().trim().length() == 0 ||
            carCityTextValue.getText().toString().trim().length() == 0 ||
            stream.toByteArray() == null
        ) {
            return;
        }

        String carName = carNameTextValue.getText().toString();
        String carPrice = carPriceTextValue.getText().toString();
        String modelYear = carModelYearTextValue.getText().toString();
        String kmUsed = carKmUsedTextValue.getText().toString();
        String sellerNamed = carSellerNameTextValue.getText().toString();
        String location = carCityTextValue.getText().toString();

        ContentValues cv = new ContentValues();

        cv.put(CarContract.CarEntry.COLUMN_CAR_IMAGE, stream.toByteArray());
        cv.put(CarContract.CarEntry.COLUMN_CAR_NAME, carName);
        cv.put(CarContract.CarEntry.COLUMN_PRICE, carPrice);
        cv.put(CarContract.CarEntry.COLUMN_MODEL_YEAR, modelYear);
        cv.put(CarContract.CarEntry.COLUMN_KM_USED, kmUsed);
        cv.put(CarContract.CarEntry.COLUMN_SELLER_NAME, sellerNamed);
        cv.put(CarContract.CarEntry.COLUMN_LOCATION, location);

        mDatabase.insert(CarContract.CarEntry.TABLE_NAME, null, cv);
        //carAdapter.swapCursor(getAllItems());
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

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    getBytesFromBitmap(bitmap);
                    //Toast.makeText(CreateAdActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(CreateAdActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(thumbnail);
            assert thumbnail != null;
            getBytesFromBitmap(thumbnail);
            saveImage(thumbnail);
            //Toast.makeText(CreateAdActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    // convert image to bytes
    public byte[] getBytesFromBitmap(Bitmap bitmap) {
        stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }


    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
