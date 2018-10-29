package com.example.xiaoxiong.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewTestActivity extends AppCompatActivity {

    private String[] data = {"111","222","333","444","555","666"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ListViewTestActivity.this,android.R.layout.simple_expandable_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
