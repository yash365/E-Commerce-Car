package com.example.ecommercecar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommercecar.R;
import com.example.ecommercecar.model.CarItem;
import com.example.ecommercecar.views.ViewAdActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    private ArrayList<CarItem> mCarList;
    private Context mContext;


    public static class CarViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCarName;
        public TextView mTextViewCarPrice;
        public TextView mTextViewLocation;
        public TextView mTextViewKmUsed;
        public TextView mTextViewSellerName;
        public CardView mCardView;
        public TextView mModelYear;

        public CarViewHolder( View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.carViewImage);
            mTextViewCarName = itemView.findViewById(R.id.carName);
            mTextViewCarPrice = itemView.findViewById(R.id.carPrice);
            mTextViewLocation = itemView.findViewById(R.id.location);
            mTextViewKmUsed = itemView.findViewById(R.id.kmUsed);
            mTextViewSellerName = itemView.findViewById(R.id.sellerName);
            mModelYear = itemView.findViewById(R.id.modelYear);
            mCardView = itemView.findViewById(R.id.cardViewId);
        }
    }

    public CarAdapter(Context context, ArrayList<CarItem> carList){
        mCarList = carList;
        mContext = context;
    }

    @NotNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        CarViewHolder evh = new CarViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        final CarItem currentItem = mCarList.get(position);

        holder.mImageView.setImageResource(currentItem.getmCarImage()); // image
        holder.mTextViewCarName.setText(currentItem.getmCarName());  // car name
        holder.mTextViewCarPrice.setText(currentItem.getmPrice()); // price
        holder.mTextViewLocation.setText(currentItem.getmCity());   // city
        holder.mTextViewKmUsed.setText(currentItem.getmKmUsed());   // km used
        holder.mTextViewSellerName.setText(currentItem.getmSellerName());   // seller named
        holder.mModelYear.setText(currentItem.getmYear());  // model year

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewAdActivity.class);

                intent.putExtra("car-image", currentItem.getmCarImage());
                intent.putExtra("car-name", currentItem.getmCarName());
                intent.putExtra("car-price", currentItem.getmPrice());
                intent.putExtra("location", currentItem.getmCity());
                intent.putExtra("km-used", currentItem.getmKmUsed());
                intent.putExtra("seller-name", currentItem.getmSellerName());
                intent.putExtra("model-year", currentItem.getmYear());

                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mCarList.size();
    }

}
