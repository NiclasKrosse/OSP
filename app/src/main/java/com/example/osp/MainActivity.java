package com.example.osp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView.Adapter mAdapter;
    private RaumbetreuerDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomlist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataSource = new RaumbetreuerDataSource(this);

        InitList();
/*        ListView nView = findViewById(R.id.listview_rooms);
        String[] nDataset = {"C001","C002","C003","C004","C005","B001"};//new String[5];

        nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset));*/
    }


    private void InitList()
  {
      ListView nView = findViewById(R.id.listview_rooms);
      String[] nDataset = {"C001","C002","C003","C004","C005","B001"};//new String[5];

      nView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nDataset));

      nView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id)
          {
              Intent intent = new Intent(MainActivity. this,Activity_RoomDetail.class);

              //Raumnummer übergeben
              intent.putExtra("room_number",parent.getItemAtPosition(position).toString());
              //based on item add info to intent
              startActivity(intent);
          }
      });
  }


}


