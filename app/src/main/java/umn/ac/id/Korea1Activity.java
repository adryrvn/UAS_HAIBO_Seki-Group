package umn.ac.id;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Korea1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korea1_screen);


        VideoView korea1 = findViewById(R.id.vidkorea1);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.korea1;
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
