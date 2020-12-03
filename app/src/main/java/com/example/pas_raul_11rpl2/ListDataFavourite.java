package com.example.pas_raul_11rpl2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ListDataFavourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    private RecyclerView recyclerView;
    private DataAdapterFavourite adapter;
    private List<ModelFootballRealm> DataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
       getSupportActionBar().hide();

        recyclerView = (RecyclerView) findViewById(R.id.rv_data);
        DataArrayList = new ArrayList<>();
        // Setup Realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        realmHelper = new RealmHelper(realm);
        DataArrayList = realmHelper.getAllFootball();
        adapter = new DataAdapterFavourite(DataArrayList, new DataAdapterFavourite.Callback() {
            @Override
            public void onClick(int position) {
                Intent move = new Intent(getApplicationContext(), DetailFavourite.class);
                move.putExtra("idTeam",DataArrayList.get(position).getId());
                move.putExtra("strTeam",DataArrayList.get(position).getTeam());
                move.putExtra("strDescriptionEN",DataArrayList.get(position).getDescription());
                move.putExtra("strTeamBadge",DataArrayList.get(position).getImageUrl());
                move.putExtra("intFormedYear",DataArrayList.get(position).getFormedYear());
                move.putExtra("strStadium",DataArrayList.get(position).getStadionName());
                move.putExtra("strStadiumDescription",DataArrayList.get(position).getStadionDesc());
                move.putExtra("strStadiumLocation",DataArrayList.get(position).getStadionLocation());
                move.putExtra("strStadiumThumb",DataArrayList.get(position).getStadionImage());

                startActivity(move);
            }

            @Override
            public void test() {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataFavourite.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}