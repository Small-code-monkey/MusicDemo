package com.musicdemo;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Music小Demo
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button)
    Button button;

    MediaPlayer mediaPlayer;//播放器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initview();
            }
        });
    }

    private void initview() {
        //设置音量
        AudioManager audioManager=(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //获取当前音量
        int mvolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //获取最大声音
        int maxvolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //通过设置最小声音
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxvolume,0);


        AssetFileDescriptor descriptor;
        try {
            descriptor=MainActivity.this.getAssets().openFd("邓丽欣 - 苹果 (铃声).mp3");

            mediaPlayer=new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor());

            descriptor.getStartOffset();
            descriptor.getLength();//获取音频长度

            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
