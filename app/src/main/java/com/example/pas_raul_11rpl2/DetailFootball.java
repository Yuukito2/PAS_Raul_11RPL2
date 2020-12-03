package com.example.pas_raul_11rpl2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailFootball extends AppCompatActivity {
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

    TextView tv_Team;
    TextView tv_FormedYear;
    TextView tv_TeamDesc;
    TextView tv_Stadion;
    TextView tv_StadionLocation;
    TextView tv_StadionDesc;
    ImageView iv_TeamLogo;
    ImageView iv_StadionImage;
    Button btn_bookmark;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_football);
        extras = getIntent().getExtras();
        tv_Team = (TextView)findViewById(R.id.tv_Team);
        tv_TeamDesc = (TextView)findViewById(R.id.tv_TeamDesc);
        tv_FormedYear = (TextView)findViewById(R.id.tv_FormedYear);
        tv_Stadion = (TextView) findViewById(R.id.tv_Stadion);
        tv_StadionLocation = (TextView) findViewById(R.id.tv_StadionLocation);
        tv_StadionDesc = (TextView) findViewById(R.id.tv_StadionDesc);
        iv_TeamLogo = (ImageView) findViewById(R.id.iv_TeamLogo);
        iv_StadionImage = (ImageView) findViewById(R.id.iv_StadionImage);
        btn_bookmark = (Button) findViewById(R.id.btn_bookmark);

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

            tv_Team.setText(team);
            tv_FormedYear.setText(""+formedYear);
            tv_TeamDesc.setText(description);
            Glide.with(DetailFootball.this)
                    .load(imageUrl)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_TeamLogo);

            tv_Stadion.setText(stadionName);
            tv_StadionLocation.setText(stadionLocation);
            tv_StadionDesc.setText(stadionDesc);
            Glide.with(this)
                    .load(stadionImage)
                    .override(Target.SIZE_ORIGINAL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(iv_StadionImage);
            // and get whatever type user account id is
        }

        //Set up Realm
        Realm.init(DetailFootball.this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);


        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                footballModel = new ModelFootballRealm();
                footballModel.setTeam(team);
                footballModel.setDescription(description);
                footballModel.setImageUrl(imageUrl);
                footballModel.setFormedYear(formedYear);
                footballModel.setStadionName(stadionName);
                footballModel.setStadionDesc(stadionDesc);
                footballModel.setStadionImage(stadionImage);
                footballModel.setStadionLocation(stadionLocation);

                realmHelper = new RealmHelper(realm);
                realmHelper.save(footballModel);

            }
        });
    }
}