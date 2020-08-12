package com.example.epapp_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.epapp_demo.R;
import com.example.epapp_demo.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ItemHolder>{

    private List<Category> categories = new ArrayList<>();
    private Context context;
    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public CategoriesAdapter(Context context){
        this.context = context;
        String[] categoryNames = {"Cơm Phần", "Trà Sữa",
                "Gà Rán", "Bún/Phở","Ăn Vặt", "Món Hàn"};

        int images_array[] = {
                R.drawable.rice,
                R.drawable.milk,
                R.drawable.ic_fried_chicken,
                R.drawable.ic_noodles,
                R.drawable.ic_snack,
                R.drawable.ic_koreanfood,
        };

        for (int i = 0; i < 6; i++){
            Category category = new Category(categoryNames[i], images_array[i]);
            categories.add(category);
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.theloai_one_item, viewGroup, false);
        ItemHolder holder = new ItemHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        final Category category =  categories.get(position);
        holder.mCategoryName.setText(category.getCategoryName());
        holder.mCategoryImage.setImageResource(category.getCategoryDrawable());
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mCategoryName;
        public ImageView mCategoryImage;
        public View mView;


        public ItemHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mView = itemView;
            mCategoryName = itemView.findViewById(R.id.category_name);
            mCategoryImage = itemView.findViewById(R.id.category_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        @Override
        public void onClick(View v) {}
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
