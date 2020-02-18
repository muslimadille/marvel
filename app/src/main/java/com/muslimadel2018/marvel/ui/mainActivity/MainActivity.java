package com.muslimadel2018.marvel.ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.muslimadel2018.marvel.R;
import com.muslimadel2018.marvel.pojo.Characters;
import com.muslimadel2018.marvel.viewModel.CharacterViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    CharacterViewModel characterViewModel;
    TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);
        textView.setText("nam");
        final ImageView imageView=findViewById(R.id.image);

        characterViewModel= ViewModelProviders.of(this).get(CharacterViewModel.class);
        characterViewModel.getCharacter();


        characterViewModel.characterMutableLiveData.observe(this, new Observer<List<Characters>>() {
            @Override
            public void onChanged(List<Characters> characters) {

                String name=characters.get(0).getName();
                String img4 = characters.get(0).getThumbnail().getPath();
                Picasso.get().load(img4+".jpg").into(imageView);


                Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
            }
        });




    }


}
