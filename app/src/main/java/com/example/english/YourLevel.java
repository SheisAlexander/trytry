package com.example.english;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class YourLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_level);

        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(seekBarOnSeekBarChange);
    }

    private SeekBar.OnSeekBarChangeListener seekBarOnSeekBarChange
            = new SeekBar.OnSeekBarChangeListener()
    {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {
            //停止拖曳時觸發事件
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {
            //開始拖曳時觸發事件
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            //拖曳途中觸發事件，回傳參數 progress 告知目前拖曳數值
        }
    };
}