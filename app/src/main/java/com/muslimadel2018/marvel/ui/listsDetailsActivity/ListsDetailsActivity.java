package com.muslimadel2018.marvel.ui.listsDetailsActivity;

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
import com.muslimadel2018.marvel.viewModel.CharacterViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListsDetailsActivity extends AppCompatActivity {
    private ImageView listisItemImage;
    private TextView listisItemtext;
    private Intent intent;
    private Bundle bundle;
    private CharacterViewModel characterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists_details);

        intent= getIntent();
        bundle= intent.getExtras();
        String B= String.valueOf(getIndex(bundle));
        Toast.makeText(ListsDetailsActivity.this,B,Toast.LENGTH_LONG).show();

        listisItemImage=findViewById(R.id.lists_details_image);
        listisItemtext=findViewById(R.id.lists_details_text);

        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        characterViewModel.getCharacter();
        characterViewModel.characterMutableLiveData.observe(this, new Observer<List<Characters>>() {
            @Override
            public void onChanged(List<Characters> characters) {
                switch (getType(bundle)){

                    case 1:
                        listisItemtext.setText(characters.get(getIndex(bundle)).getComics().getItems().get(getPosition(bundle)).getName());
                        Picasso.get().load(characters.get(getIndex(bundle)).getThumbnail().getPath()+".jpg").into(listisItemImage);
                        break;
                    case 2:
                        listisItemtext.setText(characters.get(getIndex(bundle)).getSeries().getItems().get(getPosition(bundle)).getName());
                        Picasso.get().load(characters.get(getIndex(bundle)).getThumbnail().getPath()+".jpg").into(listisItemImage);
                        break;
                    case 3:
                        listisItemtext.setText(characters.get(getIndex(bundle)).getStories().getItems().get(getPosition(bundle)).getName());
                        Picasso.get().load(characters.get(getIndex(bundle)).getThumbnail().getPath()+".jpg").into(listisItemImage);
                        break;
                    case 4:
                        listisItemtext.setText(characters.get(getIndex(bundle)).getEvents().getItems().get(getPosition(bundle)).getName());
                        Picasso.get().load(characters.get(getIndex(bundle)).getThumbnail().getPath()+".jpg").into(listisItemImage);
                        break;
                }

            }
        });
    }
    //............................................................
    public int getIndex(Bundle bundle){
        int index=0;
        if (bundle!=null){
            index= (int) bundle.get("INDEX");
        }
        return index;
    }
    //............................................................
    public int getPosition(Bundle bundle){
        int position=0;
        if (bundle!=null){
            position= (int) bundle.get("POSITION");
        }
        return position;
    }
    //.............................................................
    public int getType(Bundle bundle){
        int type=0;
        if (bundle!=null){
            type= (int) bundle.get("TYPE");
        }
        return type;
    }
}
