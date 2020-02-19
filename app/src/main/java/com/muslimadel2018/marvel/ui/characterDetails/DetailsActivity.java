package com.muslimadel2018.marvel.ui.characterDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.muslimadel2018.marvel.R;
import com.muslimadel2018.marvel.pojo.Characters;
import com.muslimadel2018.marvel.ui.listsDetailsActivity.ListsDetailsActivity;
import com.muslimadel2018.marvel.ui.mainActivity.MainActivity;
import com.muslimadel2018.marvel.viewModel.CharacterViewModel;
import com.muslimadel2018.marvel.ui.characterDetails.adapters.ComicsAdapter;
import com.muslimadel2018.marvel.ui.characterDetails.adapters.EventsAdapter;
import com.muslimadel2018.marvel.ui.characterDetails.adapters.SeriesAdapter;
import com.muslimadel2018.marvel.ui.characterDetails.adapters.StoriesAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private CharacterViewModel characterViewModel;
    private RecyclerView comicsList,seriesList,storiesList,eventsList;
    private ComicsAdapter comicsAdapter;
    private SeriesAdapter seriesAdapter;
    private EventsAdapter eventsAdapter;
    private StoriesAdapter storiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final TextView nameText=findViewById(R.id.character_detail_name);
        final TextView descriptionText=findViewById(R.id.character_detail_description);
        final ImageView characterImage=findViewById(R.id.character_detail_image);

        comicsList=findViewById(R.id.comicsList);
        seriesList=findViewById(R.id.seriesList);
        storiesList=findViewById(R.id.storiesList);
        eventsList=findViewById(R.id.eventsList);




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

                comicsAdapter = new ComicsAdapter(DetailsActivity.this, characters,itemPosition());
                comicsList.setAdapter(comicsAdapter);
                comicsAdapter.setOnItemClickListener(new ComicsAdapter.OnItimeClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=new Intent(DetailsActivity.this, ListsDetailsActivity.class);
                        intent.putExtra("POSITION",position);
                        intent.putExtra("INDEX",itemPosition());
                        intent.putExtra("TYPE",1);

                        startActivity(intent);
                    }
                });

                seriesAdapter=new SeriesAdapter(DetailsActivity.this,characters,itemPosition());
                seriesList.setAdapter(seriesAdapter);
                seriesAdapter.setOnItemClickListener(new SeriesAdapter.OnItimeClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=new Intent(DetailsActivity.this, ListsDetailsActivity.class);
                        intent.putExtra("POSITION",position);
                        intent.putExtra("INDEX",itemPosition());
                        intent.putExtra("TYPE",2);

                        startActivity(intent);

                    }
                });

                eventsAdapter=new EventsAdapter(DetailsActivity.this,characters,itemPosition());
                eventsList.setAdapter(eventsAdapter);
                eventsAdapter.setOnItemClickListener(new EventsAdapter.OnItimeClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=new Intent(DetailsActivity.this, ListsDetailsActivity.class);
                        intent.putExtra("POSITION",position);
                        intent.putExtra("INDEX",itemPosition());
                        intent.putExtra("TYPE",4);

                        startActivity(intent);
                    }
                });

                storiesAdapter=new StoriesAdapter(DetailsActivity.this,characters,itemPosition());
                storiesList.setAdapter(storiesAdapter);
                storiesAdapter.setOnItemClickListener(new StoriesAdapter.OnItimeClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=new Intent(DetailsActivity.this, ListsDetailsActivity.class);
                        intent.putExtra("POSITION",position);
                        intent.putExtra("INDEX",itemPosition());
                        intent.putExtra("TYPE",3);

                        startActivity(intent);
                    }
                });



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
