package com.am.mymediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    ContentResolver contentResolver;
    Cursor cursor;
    Uri uri;
    MediaPlayer mediaPlayer;
    Uri uri2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        listView = findViewById(R.id.lv);
        arrayList = new ArrayList();

        getfiles();


        arrayAdapter = new ArrayAdapter(MainActivity.this
                , android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        mediaPlayer = new MediaPlayer();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this,
                        MainActivity2.class);

                intent.putExtra("songpath",arrayList.get(i)+"");
                startActivity(intent);

            }
        });
    }

    public void loadmedia(View view) {
///        getfiles();
    }

    private void getfiles() {
        contentResolver=MainActivity.this.getContentResolver();
        uri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String [] proj = { MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA };
        cursor = contentResolver.query(uri,proj,null,null,null);

        if (cursor==null){
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        else if (!cursor.moveToFirst()){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        do {
            String SongName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            if (path.endsWith(".mp3")){
                arrayList.add(path);
            }
        }while (cursor.moveToNext());
    }















}