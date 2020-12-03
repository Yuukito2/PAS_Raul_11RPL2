package com.example.pas_raul_11rpl2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;

public class DetailFavourite extends AppCompatActivity {
    Realm realm;
    RealmHelper realmHelper;
    ModelFootballRealm footballModel;


    Bundle extras;
    int id;
    String team;
    String imageUrl;
    String description;
    String formedYear;
    String stadionName;
    String stadionDesc;
    String stadionLocation;
    String stadionImage;

    TextView tv_TeamFav;
    TextView tv_FormedYearFav;
    TextView tv_TeamDescFav;
    TextView tv_StadionFav;
    TextView tv_StadionLocationFav;
    TextView tv_StadionDescFav;
    ImageView iv_TeamLogoFav;
    ImageView iv_StadionImageFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite);
        extras = getIntent().getExtras();
        tv_TeamFav = (TextView)findViewById(R.id.tv_TeamFav);
        tv_TeamDescFav = (TextView)findViewById(R.id.tv_TeamDescFav);
        tv_FormedYearFav = (TextView)findViewById(R.id.tv_FormedYearFav);
        tv_StadionFav = (TextView) findViewById(R.id.tv_StadionFav);
        tv_StadionLocationFav = (TextView) findViewById(R.id.tv_StadionLocationFav);
        tv_StadionDescFav = (TextView) findViewById(R.id.tv_StadionDescFav);
        iv_TeamLogoFav = (ImageView) findViewById(R.id.iv_TeamLogoFav);
        iv_StadionImageFav = (ImageView) findViewById(R.id.iv_StadionImageFav);


        if (extras != null) {
            id = extras.getInt("id");
            team = extras.getString("strTeam");
            imageUrl = extras.getString("strTeamBadge");
            description = extras.getString("strDescriptionEN");
            formedYear = extras.getString("intFormedYear");
            stadionName = extras.getString("strStadium");
            stadionImage = extras.getString("strStadiumThumb");
            stadionDesc = extras.getString("strStadiumDescription");
            stadionLocation = extras.getString("strStadiumLocation");

            Log.d("Test", ""+stadionImage);

            tv_TeamFav.setText(team);
            tv_FormedYearFav.setText(""+formedYear);
            tv_TeamDescFav.setText(description);
            Glide.with(DetailFavourite.this)
                    .load(imageUrl)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_TeamLogoFav);

            tv_StadionFav.setText(stadionName);
            tv_StadionLocationFav.setText(stadionLocation);
            tv_StadionDescFav.setText(stadionDesc);
            Glide.with(this)
                    .load(stadionImage)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_StadionImageFav);
            // and get whatever type user account id is
        }
    }
}