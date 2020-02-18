package com.muslimadel2018.marvel.ui.searchActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.muslimadel2018.marvel.R;
import com.muslimadel2018.marvel.data.PosteClint;
import com.muslimadel2018.marvel.pojo.Characters;
import com.muslimadel2018.marvel.pojo.Response;
import com.muslimadel2018.marvel.ui.characterDetails.DetailsActivity;
import com.muslimadel2018.marvel.ui.mainActivity.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView searchList;
    private ProgressBar progressBar;
    private SearchListAdapter searchListAdapter;
    private List<Characters> charactersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progressBar=findViewById(R.id.progress);
        searchList=findViewById(R.id.search_list);
        fetshData("asdsakdsad");

    }

    public void fetshData(String key){


        PosteClint.getINISTANCE().getPosts(key).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                progressBar.setVisibility(View.GONE);
                charactersList=response.body().getData().getResults();
                searchListAdapter=new SearchListAdapter(SearchActivity.this,charactersList);
                searchList.setAdapter(searchListAdapter);
                searchListAdapter.notifyDataSetChanged();

                searchListAdapter.setOnItemClickListener(new SearchListAdapter.OnItimeClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=new Intent(SearchActivity.this, DetailsActivity.class);
                        intent.putExtra("POSITION",position);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SearchActivity.this,"Error In ;"+t.toString(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView=(SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals(""))
                {fetshData(query);}
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.equals(""))
                {fetshData(newText);}

                return false;
            }
        });

        return true;
    }
}
