package umn.ac.id;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Korea2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.korea2_screen);

        VideoView korea2 = findViewById(R.id.vidkorea2);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.korea2;
        Uri uri = Uri.parse(videoPath);
        korea2.setVideoURI(uri);

//        MediaController mediaController = new MediaController(this);
//        korea2.setMediaController(mediaController);
//        mediaController.setAnchorView(korea2);

        korea2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        korea2.start();


    }
}
