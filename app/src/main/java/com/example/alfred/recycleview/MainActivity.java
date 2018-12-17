package com.example.alfred.recycleview;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> allText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = (RecyclerView) findViewById(R.id.articles);
        // w celach optymalizacji
        recyclerView.setHasFixedSize(true);

        // ustawiamy LayoutManagera
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ustawiamy animatora, który odpowiada za animację dodania/usunięcia elementów listy
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // tworzymy źródło danych - tablicę z artykułami
        ArrayList<Article> articles = new ArrayList<>();
        allText = new ArrayList<>();

        String header = ""; String body = "";
        StringBuffer sbuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.dane);
        BufferedReader bReader = new BufferedReader( new InputStreamReader(is));

        if(is != null) {
            try {
                while((header = bReader.readLine()) != null && (body = bReader.readLine()) != null ) {
                    articles.add(new Article(header, body));
                    allText.add(header); allText.add(body); allText.add(bReader.readLine());
                }
                is.close();

            }
            catch (IOException e) {

            }
        }

        // tworzymy adapter oraz łączymy go z RecyclerView
        recyclerView.setAdapter(new MyAdapter(articles, recyclerView, this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1)
            if(resultCode == Activity.RESULT_OK) {
                ((MyAdapter)recyclerView.getAdapter()).onActivityResult(requestCode, resultCode, data);

            }

    }
}


