package com.ldnanoline.FLEX;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button simonBtn = (Button)findViewById(R.id.simonBtn);
        simonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SimonActivity.class);
                startActivity(startIntent);
            }
        });

        Button mazeBtn = (Button)findViewById(R.id.mazeBtn);
        mazeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MazeActivity.class);
                startActivity(startIntent);
            }
        });

        Button shapeBtn = (Button)findViewById(R.id.shapeBtn);
        shapeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ShapeActivity.class);
                startActivity(startIntent);
            }
        });
    }
}
