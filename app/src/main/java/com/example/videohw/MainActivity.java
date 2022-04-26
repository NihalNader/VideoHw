package com.example.videohw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.videohw.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    String videolink="https://firebasestorage.googleapis.com/v0/b/learn-easily-2cf49.appspot.com/o/videos%2F1650976255097?alt=media&token=81f19119-b502-4334-9006-b0a17024c793";
ActivityMainBinding binding;
PlayerView pv;
SimpleExoPlayer player;
boolean playerReady=true;
long currentPosition=0;
    int currentWindow=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pv=binding.playerView;

    }

    private void initPlayer(){
        player=new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);
        MediaItem item=MediaItem.fromUri(videolink);
        player.setMediaItem(item);
        player.setPlayWhenReady(playerReady);
        player.seekTo(currentWindow,currentPosition);
        player.prepare();
    }
private  void releasePlayer(){
        if(player !=null){
playerReady=player.getPlayWhenReady();
currentWindow =player.getCurrentWindowIndex();
currentPosition=player.getCurrentPosition();
player = null;

        }


}


    @Override
    protected void onStart() {
        super.onStart();
       initPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
       if(player !=null) initPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
}