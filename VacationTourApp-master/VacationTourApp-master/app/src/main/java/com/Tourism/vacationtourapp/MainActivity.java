package com.Tourism.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.Tourism.vacationtourapp.adapter.RecentsAdapter;
import com.Tourism.vacationtourapp.model.RecentsData;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView searchText;
    Button btnLogout;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;



    ArrayAdapter<String> arrayAdapter;


    RecyclerView recentRecycler;
    RecentsAdapter recentsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.allPlaces);
        searchText.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openSearchMenu();
            }
        });










        btnLogout=findViewById(R.id.logOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(intToSignUp);
            }
        });

        // Now here we will add some dummy data in our model class

        List<RecentsData> recentsDataList = new ArrayList<>();


        recentsDataList.add(new RecentsData("Mughal Gardens",  R.drawable.mughal_gardens));
        recentsDataList.add(new RecentsData("Ooty",  R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("Agra",   R.drawable.img3));
        recentsDataList.add(new RecentsData("AM Lake",   R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nainital",  R.drawable.nainital));

        setRecentRecycler(recentsDataList);


    }

    private void openSearchMenu() {
        Intent intent = new Intent(this,SearchMenu.class);
        startActivity(intent);
    }


    private void setRecentRecycler(List<RecentsData> recentsDataList) {

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }


}



