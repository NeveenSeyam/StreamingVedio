package com.example.neveenstreamingved;


        import androidx.appcompat.app.AppCompatActivity;

        import android.net.Uri;
        import android.os.Bundle;

        import com.google.android.exoplayer2.ExoPlayerFactory;
        import com.google.android.exoplayer2.SimpleExoPlayer;
        import com.google.android.exoplayer2.source.MediaSource;
        import com.google.android.exoplayer2.source.ProgressiveMediaSource;
        import com.google.android.exoplayer2.ui.PlayerView;
        import com.google.android.exoplayer2.upstream.DataSource;
        import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class coronaActivity extends AppCompatActivity {
    // Player Init

    PlayerView playerView;
    // Expolayer Init

    SimpleExoPlayer player;
    // book init

    private boolean PlayWhenReady = true;
    // init index in lvl 0

    private long index = 0;
    // Stream Video Url

    String streamingVidoURL = "https://www.youtube.com/watch?v=nIln6qpcO2w";
//
    private int Window = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona);
        playerView = findViewById(R.id.vidoView4);


    }

    public void initVideo() {
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(player);
        Uri uri = Uri.parse(streamingVidoURL);
        DataSource.Factory DataSource = new DefaultDataSourceFactory(this, "neveen");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(DataSource).createMediaSource(uri);
        player.setPlayWhenReady(PlayWhenReady);
        player.seekTo(Window, index);
        player.prepare(mediaSource, false, false);
    }

    public void StopVideo() {
        if (player != null) {
            PlayWhenReady = player.getPlayWhenReady();
            index = player.getContentPosition();
            Window = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initVideo();


    }

    @Override
    protected void onStop() {
        super.onStop();
        StopVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StopVideo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            initVideo();

        }

    }
}
