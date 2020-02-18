package com.muslimadel2018.marvel.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.muslimadel2018.marvel.data.PosteClint;
import com.muslimadel2018.marvel.pojo.Characters;
import com.muslimadel2018.marvel.pojo.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CharacterViewModel extends ViewModel {

    public MutableLiveData<List<Characters>> characterMutableLiveData = new MutableLiveData<>();


    public void getCharacter() {

        PosteClint.getINISTANCE().getPosts(null).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                characterMutableLiveData.setValue(response.body().getData().getResults());

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

}
