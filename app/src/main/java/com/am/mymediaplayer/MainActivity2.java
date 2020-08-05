package com.am.mymediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    String path;
    ImageButton  imageButton;
    TextView textView;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mediaPlayer = new MediaPlayer();
        textView=findViewById(R.id.textView);
        imageButton=findViewById(R.id.imageButton);
        path=getIntent().getStringExtra("songpath");
        uri = Uri.parse("file://" + path);
        try {
            mediaPlayer.setDataSource(MainActivity2.this,uri);
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playy(View view) {

        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            imageButton.setBackgroundResource(android.R.drawable.ic_media_play);
        }
        else {
            imageButton.setBackgroundResource(android.R.drawable.ic_media_pause);
            mediaPlayer.start();
        }
    }

    public void stp(View view) {
        mediaPlayer.stop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}