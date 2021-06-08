package umn.ac.id;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class Jepang2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jepang2_screen);


        VideoView korea1 = findViewById(R.id.vidjepang2);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.jepang2;
        Uri uri = Uri.parse(videoPath);
        korea1.setVideoURI(uri);

        korea1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

//        MediaController mediaController = new MediaController(this);
//        korea1.setMediaController(mediaController);
//        mediaController.setAnchorView(korea1);
        korea1.start();



    }
}