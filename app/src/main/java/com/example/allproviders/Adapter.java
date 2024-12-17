package com.example.allproviders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ImageViewHolder> {

    private Context context;
    private List<Model> imagelist;
    private SelectListner listner;

    public void setFilteredList(List<Model> filteredList){
        this.imagelist = filteredList;
        notifyDataSetChanged();
    }

    public Adapter(Context context, List<Model> imagelist, SelectListner listner) {
        this.context = context;
        this.imagelist = imagelist;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        Glide.with(context).load(imagelist.get(position).getImage()).into(holder.imageView);
        holder.nametv.setText(imagelist.get(position).getName());
        holder.professiontv.setText(imagelist.get(position).getProfession());
        holder.locationtv.setText(imagelist.get(position).getDivision());
        holder.email.setText(imagelist.get(position).getEmail());
        holder.bio.setText(imagelist.get(position).getBio());
        holder.district.setText(imagelist.get(position).getDistrict());
        holder.subdistrict.setText(imagelist.get(position).getSubdistrict());
        holder.number.setText(imagelist.get(position).getNumber());
        holder.age.setText(imagelist.get(position).getAge());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onItemClicked(imagelist.get(position));
                ImageView myimage = holder.imageView;
                Bitmap bitmap = ((BitmapDrawable) myimage.getDrawable()).getBitmap();
                Provider.myBitmap = bitmap;

            }
        });
    }

    @Override
    public int getItemCount() {

        return imagelist.size();
    }
}

class ImageViewHolder extends RecyclerView.ViewHolder{

    TextView nametv, professiontv, locationtv, email,bio,district,subdistrict,number,age;
    ImageView imageView;

    CardView cardView;

    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.iv_retrieve);
        nametv = itemView.findViewById(R.id.nametv);
        professiontv = itemView.findViewById(R.id.professiontv);
        locationtv = itemView.findViewById(R.id.locationtv);
        cardView = itemView.findViewById(R.id.cardView);
        email = itemView.findViewById(R.id.emailtv);
        bio = itemView.findViewById(R.id.biotv);
        district = itemView.findViewById(R.id.districttv);
        subdistrict = itemView.findViewById(R.id.subdistricttv);
        number = itemView.findViewById(R.id.numbertv);
        age = itemView.findViewById(R.id.agetv);

    }
}