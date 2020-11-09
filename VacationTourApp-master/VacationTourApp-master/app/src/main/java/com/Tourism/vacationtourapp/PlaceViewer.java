package com.Tourism.vacationtourapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceViewer extends AppCompatActivity {
    ImageView imageView;
    TextView nameView,descView;
    ItemsModel itemsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_viewer);

        imageView=findViewById(R.id.imageView12);
        nameView=findViewById(R.id.itemName);
        descView=findViewById(R.id.itemDesc);

        Intent intent=getIntent();
        if(intent.getExtras()!=null){
            itemsModel = (ItemsModel) intent.getSerializableExtra("item");

            imageView.setImageResource(itemsModel.getImage());
            nameView.setText(itemsModel.getName());
            descView.setText(itemsModel.getDesc());
        }
    }
}