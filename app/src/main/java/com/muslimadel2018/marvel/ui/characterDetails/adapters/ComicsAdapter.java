package com.muslimadel2018.marvel.ui.characterDetails.adapters;

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

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ViewHolder> {
    private ComicsAdapter.OnItimeClickListner mListner;

    public interface OnItimeClickListner {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ComicsAdapter.OnItimeClickListner listener) {
        mListner = listener;
    }

    private List<Characters> characters;
    private Context context;
    private int index;

    public ComicsAdapter(Context context, List<Characters> characters, int index) {
        this.context = context;
        this.characters = characters;
        this.index = index;
    }

    @NonNull
    @Override
    public ComicsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list_raw, parent, false);
        return new ComicsAdapter.ViewHolder(view, mListner);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsAdapter.ViewHolder holder, int position) {
        holder.name.setText(characters.get(index).getComics().getItems().get(position).getName());
        Picasso.get().load(characters.get(position).getThumbnail().getPath() + ".jpg").into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return characters.get(index).getComics().getItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView name;


        public ViewHolder(@NonNull View itemView, final ComicsAdapter.OnItimeClickListner listner) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            name = itemView.findViewById(R.id.item_text);

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