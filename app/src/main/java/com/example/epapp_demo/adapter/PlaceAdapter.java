package com.example.epapp_demo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.model.CuaHang_temp;
import com.example.epapp_demo.model.Place;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ItemHolder>{

    private List<Place> mPlaces = new ArrayList<>();
    private Context context;
    int resource;
    List<CuaHang_temp> objects;
    private OnPlaceClickListener mListener;
    public void setOnPlaceItemClickListener (OnPlaceClickListener onPlaceItemClickListener){
        mListener = onPlaceItemClickListener;
    }
    public PlaceAdapter(Context context){
        this.context = context;

        String[] placeNames = {"Chipotle", "Five Guys",
                "Starbucks", "Subway", "Dunkin Donuts", "Habbib's", "Mac Donalds", "Chipotle", "Starbucks", "Dunkin Donuts"};

        String[] placeDelivery = {"Entrega Grátis", "R$7,00",
                "Entrega Grátis", "R$5,00", "R$9,00", "R$5,00", "Entrega Grátis", "R$7,00", "R$11,00", "R$9,00"};

        for (int i = 0; i < 10; i++){
            Place place = new Place((i + 1),placeNames[i], "Av Paulista, " + (i + 1),
                     "4." + (i + 1), placeDelivery[i]);
            mPlaces.add(place);
        }
    }

    public void setFavorite(int placeId) {
        if(mPlaces.size() > 0) {
           for (int i = 0; i < mPlaces.size(); i++) {
                    if(mPlaces.get(i).getPlaceId() == placeId) {
                        if (!mPlaces.get(i).isFavorite()) {
                            mPlaces.get(i).setFavorite(true);
                            break;
                        } else {
                            mPlaces.get(i).setFavorite(false);
                            break;
                        }
                    }
                }
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.quanan_one_item, viewGroup, false);
        ItemHolder holder = new ItemHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, int position) {
        final Place place =  mPlaces.get(position);

        holder.mItem = place;

        holder.placeName.setText(place.getPlaceName());
        holder.placeLocation.setText(place.getLocation());
        holder.placeRating.setText(place.getRating());
        holder.placeDelivery.setText(place.getDelivery());

        if (holder.mItem.isFavorite()) {
            holder.icFavorite.setImageResource(R.drawable.star);
        } else {
            holder.icFavorite.setImageResource(R.drawable.star2);
        }
        holder.lnlFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener  != null)
                    mListener.onPlaceFavoriteClick(place);
            }
        });
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView placeName, placeLocation, placeRating, placeDelivery;
        public RelativeLayout lnlFavorite;
        public ImageView icFavorite;
        public final View mView;
        public Place mItem;


        public ItemHolder(@NonNull View itemView, final OnPlaceClickListener onPlaceClickListener) {
            super(itemView);
            mView = itemView;
            placeName = itemView.findViewById(R.id.place_name);
            placeLocation = itemView.findViewById(R.id.place_location);
            placeRating = itemView.findViewById(R.id.place_rating);
            placeDelivery = itemView.findViewById(R.id.place_delivery);
            lnlFavorite = itemView.findViewById(R.id.lnl_favorite);
            icFavorite = itemView.findViewById(R.id.ic_favorite);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPlaceClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onPlaceClickListener.onPlaceItemClick(position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Clicked Item", Toast.LENGTH_SHORT).show();
        }
    }

    public interface OnPlaceClickListener {
        void onPlaceItemClick(int position);
        void onPlaceFavoriteClick(Place place);
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }
}
