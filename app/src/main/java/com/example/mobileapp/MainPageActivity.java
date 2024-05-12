package com.example.mobileapp;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainPageActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getName();

    FirebaseUser user;

    private RecyclerView recyclerView;
    private ArrayList<Items> items;
    private ItemAdapter adapter;

    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){finish();}

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,gridNumber));
        items = new ArrayList<>();

        adapter = new ItemAdapter(this, items);

        recyclerView.setAdapter(adapter);

    }

    private void initializeData() throws JSONException {
        String[] itemBrand;
        String[] itemModel;
        String[] itemBancmarkPoint;
        TypedArray itemsImageResource;
        }

}
