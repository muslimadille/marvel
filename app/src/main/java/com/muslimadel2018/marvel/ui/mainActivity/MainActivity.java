package com.muslimadel2018.marvel.ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.muslimadel2018.marvel.R;
import com.muslimadel2018.marvel.pojo.Characters;
import com.muslimadel2018.marvel.ui.CharacterViewModel;
import com.muslimadel2018.marvel.ui.characterDetails.DetailsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CharacterViewModel characterViewModel;
    private RecyclerView charactersRecyclerView;
    private CharactersListAdapter charactersListAdapter;
    private List<Characters> charactersArrayList;

    //TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        charactersRecyclerView = findViewById(R.id.characters_list);
        characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        characterViewModel.getCharacter();
        characterViewModel.characterMutableLiveData.observe(this, new Observer<List<Characters>>() {
            @Override
            public void onChanged(final List<Characters> characters) {

                charactersListAdapter = new CharactersListAdapter(MainActivity.this, characters);
                charactersRecyclerView.setAdapter(charactersListAdapter);

                charactersListAdapter.setOnItemClickListener(new CharactersListAdapter.OnItimeClickListner() {
                    @Override
                    public void onItemClick(int position) {

                        Intent intent=new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("POSITION",position);
                        startActivity(intent);
                    }
                });


            }
        });


    }


}
