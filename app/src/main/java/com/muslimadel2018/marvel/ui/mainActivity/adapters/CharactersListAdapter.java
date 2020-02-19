package com.muslimadel2018.marvel.ui.mainActivity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muslimadel2018.marvel.R;
import com.muslimadel2018.marvel.pojo.Characters;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.ViewHolder> {
    private OnItimeClickListner mListner;

    public interface OnItimeClickListner {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItimeClickListner listener) {
        mListner = listener;
    }

    private List<Characters> characters ;
    private Context context;

    public CharactersListAdapter(Context context, List<Characters> characters) {
        this.context = context;
        this.characters = characters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_raw, parent, false);
        return new CharactersListAdapter.ViewHolder(view, mListner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(characters.get(position).getName());
        Picasso.get().load(characters.get(position).getThumbnail().getPath()+".jpg").into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView name;


        public ViewHolder(@NonNull View itemView, final OnItimeClickListner listner) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_list_image);
            name = itemView.findViewById(R.id.list_item_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listner.onItemClick(position);
                        }

                    }

                }
            });


        }


    }

}
