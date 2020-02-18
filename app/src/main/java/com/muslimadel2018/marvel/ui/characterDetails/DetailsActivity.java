package com.muslimadel2018.marvel.ui.characterDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muslimadel2018.marvel.R;
import com.muslimadel2018.marvel.pojo.Characters;
import com.muslimadel2018.marvel.ui.CharacterViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private CharacterViewModel characterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView nameText=findViewById(R.id.character_detail_name);
        final TextView descriptionText=findViewById(R.id.character_detail_description);
        final ImageView characterImage=findViewById(R.id.character_detail_image);



        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        characterViewModel.getCharacter();
        characterViewModel.characterMutableLiveData.observe(this, new Observer<List<Characters>>() {
            @Override
            public void onChanged(List<Characters> characters) {

                String name =characters.get(itemPosition()).getName();
                String description =characters.get(itemPosition()).getDescription();
                nameText.setText(name);
                descriptionText.setText(description);
                Picasso.get().load(characters.get(itemPosition()).getThumbnail().getPath()+".jpg").into(characterImage);


            }
        });
    }

    //get item position
    public int itemPosition(){
        int position=0;

        Intent intent= getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
             position= (int) bundle.get("POSITION");
        }
        return position;
    }
}
