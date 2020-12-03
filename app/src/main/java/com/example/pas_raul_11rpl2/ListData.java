package com.example.pas_raul_11rpl2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListData extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<ModelFootballRealm> DataArrayList; //kit add kan ke adapter
    private ImageView tambah_data;
    ProgressDialog dialog;
    private Object ModelFootballRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        recyclerView = (RecyclerView) findViewById(R.id.rv_data);
        dialog = new ProgressDialog(ListData.this);
        //addData();
        addDataOnline();
    }


    void addDataOnline(){
        //kasih loading
        dialog.setMessage("Loading...");
        dialog.show();
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d("hasiljson", "onResponse: " + response.toString());
                        //Debug berhasil -> lanjut code bawah
                        DataArrayList = new ArrayList<>();
                        ModelFootballRealm Modeldb;

                        try {
                            Log.d("hasiljson", "onResponse: " + response.toString());
                            JSONArray jsonArray = response.getJSONArray("teams");
                            Log.d("hasiljson2", "onResponse: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Modeldb = new ModelFootballRealm();
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Modeldb.setId(jsonObject.getInt("idTeam"));
                                Modeldb.setTeam(jsonObject.getString("strTeam"));
                                Modeldb.setDescription(jsonObject.getString("strDescriptionEN"));
                                Modeldb.setImageUrl(jsonObject.getString("strTeamBadge"));
                                Modeldb.setFormedYear(jsonObject.getString("intFormedYear"));
                                Modeldb.setStadionName(jsonObject.getString("strStadium"));
                                Modeldb.setStadionDesc(jsonObject.getString("strStadiumDescription"));
                                Modeldb.setStadionLocation(jsonObject.getString("strStadiumLocation"));
                                Modeldb.setStadionImage(jsonObject.getString("strStadiumThumb"));
                                DataArrayList.add (Modeldb);
                            }

                            //Handle Click
                            adapter = new DataAdapter(DataArrayList, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    ModelFootballRealm football = DataArrayList.get(position);
                                    Log.d("stadiumImage", ""+football.getStadionImage());
                                    Log.d("year", ""+football.getFormedYear());
                                    Intent intent = new Intent(getApplicationContext(), DetailFootball.class);
                                    intent.putExtra("idTeam",football.getId());
                                    intent.putExtra("strTeam",football.getTeam());
                                    intent.putExtra("strDescriptionEN",football.getDescription());
                                    intent.putExtra("strTeamBadge",football.getImageUrl());
                                    intent.putExtra("intFormedYear",football.getFormedYear());
                                    intent.putExtra("strStadium",football.getStadionName());
                                    intent.putExtra("strStadiumDescription",football.getStadionDesc());
                                    intent.putExtra("strStadiumLocation",football.getStadionLocation());
                                    intent.putExtra("strStadiumThumb",football.getStadionImage());
                                    startActivity(intent);
                                }

                                @Override
                                public void test() {

                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListData.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            if (dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("errorku", "onError errorCode : " + error.getErrorCode());
                        Log.d("errorku", "onError errorBody : " + error.getErrorBody());
                        Log.d("errorku", "onError errorDetail : " + error.getErrorDetail());
                    }
                });
    }
}