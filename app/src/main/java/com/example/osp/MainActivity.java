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

import com.example.osp.data.pojo.Raum;


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
    }


    private void InitList()
  {
      ListView nView = findViewById(R.id.listview_rooms);

      RaumbetreuerDataSource nSrc = new RaumbetreuerDataSource(this);

      Raum[] raeume = nSrc.selectRaeume();

      //Variante 1, Nur Raumnummern, Okay da eindeutig
      String[] nDataset = new String[raeume.length];
      for(int i = 0; i < raeume.length; i++) {
          nDataset[i] = raeume[i].raumName;
      }
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


