package com.example.rifat.mediaplayerapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button playButton;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton=findViewById(R.id.play_button);
        seekBar=findViewById(R.id.seekBar_id);

        mediaPlayer=new MediaPlayer();
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.choose);
        seekBar.setMax(mediaPlayer.getDuration());


       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           // This method show the total duration of our playing music via Toast msg.
           public void onCompletion(MediaPlayer mp) {
               int duration=mp.getDuration();
               String mduroation=String.valueOf(duration/1000); // show MiliSec into Second

               Toast.makeText(getApplicationContext(),"duration: " + mduroation,Toast.LENGTH_SHORT).show();
           }
       });


       seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser){
                        mediaPlayer.seekTo(progress);
                    }
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });

       playButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (mediaPlayer.isPlaying()){
                   pauseMusic();

               }else {
                   startMusic();
               }
           }
       });
    }

    public void startMusic(){
        if (mediaPlayer !=null){
            mediaPlayer.start();
            playButton.setText("Pause");
        }
    }

    public void pauseMusic(){
        if (mediaPlayer !=null){
            mediaPlayer.pause();
            playButton.setText("Play");
        }
    }


   // If we press back  then our playing music activity will destroy and stop the music
    @Override
    protected void onDestroy() {
        if (mediaPlayer !=null && mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        super.onDestroy();
    }
}
