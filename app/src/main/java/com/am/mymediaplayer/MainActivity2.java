package com.am.mymediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    String path;
    ImageButton  imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mediaPlayer = new MediaPlayer();
        imageButton=findViewById(R.id.imageButton);
        path=getIntent().getStringExtra("songpath");
    }

    public void playy(View view) {

        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            imageButton.setBackgroundResource(android.R.drawable.ic_media_play);


        }
        else {
            imageButton.setBackgroundResource(android.R.drawable.ic_media_pause);

            Uri uri = Uri.parse("file://" + path);
            try{
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDataSource(MainActivity2.this,uri);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void stp(View view) {
        mediaPlayer.stop();
    }

    public void pau(View view) {
        mediaPlayer.pause();
    }
}