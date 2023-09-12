package com.annguyen.riki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnHome,btnCar,btnMotobike,btnBicycle,btnClean,btnGoWith;
        btnHome=findViewById(R.id.btnHome);
        btnCar=findViewById(R.id.btnCar);
        btnMotobike=findViewById(R.id.btnMotobike);
        btnBicycle=findViewById(R.id.btnBicycle);
        btnClean=findViewById(R.id.btnClean);
        btnGoWith=findViewById(R.id.btnGoWith);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1= new Intent(HomeActivity.this,Home.class);
                startActivity(int1);
            }
        });
        btnCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2= new Intent(HomeActivity.this,Car.class);
                startActivity(int2);
            }
        });
        btnMotobike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3= new Intent(HomeActivity.this,Motobike.class);
                startActivity(int3);
            }
        });
        btnBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int4= new Intent(HomeActivity.this,Bicycle.class);
                startActivity(int4);
            }
        });
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int5= new Intent(HomeActivity.this,Clean.class);
                startActivity(int5);
            }
        });
        btnGoWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int6= new Intent(HomeActivity.this,GoWith.class);
                startActivity(int6);
            }
        });
    }
}