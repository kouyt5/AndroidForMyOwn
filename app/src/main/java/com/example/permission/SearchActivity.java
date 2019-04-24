package com.example.permission;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;

import com.orhanobut.logger.Logger;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView=findViewById(R.id.search_button2);
        searchView.requestFocus();

        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Logger.d("onQueryTextSubmit");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Logger.d(s);
                return true;
            }
        });

    }

}
