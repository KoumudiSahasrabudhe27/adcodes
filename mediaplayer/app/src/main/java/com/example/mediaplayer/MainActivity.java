package com.example.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView title, artist;
    SeekBar seekBar;
    Button btnPlay, btnNext, btnPrev;

    MediaPlayer mediaPlayer;
    Handler handler = new Handler();

    int currentIndex = 0;

    int[] songs = {
            R.raw.song1,
            R.raw.song2
    };

    int[] images = {
            R.drawable.song1,
            R.drawable.song2
    };

    String[] titles = {
            "Nevada (Slowed)",
            "Until I Found You"
    };

    String[] artists = {
            "Vicetone",
            "Stephen Sanchez"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        artist = findViewById(R.id.artist);
        seekBar = findViewById(R.id.seekBar);
        btnPlay = findViewById(R.id.btnPlay);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);

        loadSong(currentIndex);

        btnPlay.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnPlay.setText("PLAY");
            } else if (mediaPlayer != null) {
                mediaPlayer.start();
                btnPlay.setText("PAUSE");
            }
        });

        btnNext.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % songs.length;
            loadSong(currentIndex);
        });

        btnPrev.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + songs.length) % songs.length;
            loadSong(currentIndex);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void loadSong(int index) {

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, songs[index]);

        imageView.setImageResource(images[index]);
        title.setText(titles[index]);
        artist.setText(artists[index]);

        mediaPlayer.start();
        btnPlay.setText("PAUSE");

        seekBar.setMax(mediaPlayer.getDuration());

        updateSeekBar();
    }

    private void updateSeekBar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
                handler.postDelayed(this, 500);
            }
        }, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}