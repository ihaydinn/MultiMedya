package com.ismailhakkiaydin.multimedya;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity
    {
        private String muzikler[];
        private Spinner cboMuzikler;
        private ToggleButton btnStart_Stop;
        private MediaPlayer mp;
        private int secilen_muzik;
        private CheckBox chkLoop;

        public class SpinnerSecici implements AdapterView.OnItemSelectedListener
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                String secilen = arg0.getItemAtPosition(arg2).toString();

                switch (secilen) {
                    case "tada":
                        secilen_muzik = R.raw.tada;
                        break;
                    case "exlove":
                        secilen_muzik = R.raw.exlove;
                        break;
                    case "ringout":
                        secilen_muzik = R.raw.ringout;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
            }
        }

        public void Start_Stop_Click(View btn)
        {
            try{if (btnStart_Stop.isChecked()) {
                mp = MediaPlayer.create(this, secilen_muzik);
                mp.start();

            } else {
                if (mp.isPlaying()) {
                    mp.stop();
                    mp = null;
                }
            }}catch (Exception e){
                Log.e("CSD",e.getMessage());
            }

        }

        private void init()
        {
            muzikler = this.getResources().getStringArray(R.array.muzikler);
            cboMuzikler = (Spinner) findViewById(R.id.cboMuzikler);
            btnStart_Stop = (ToggleButton) findViewById(R.id.btnStart_Stop);

            ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, muzikler);
            cboMuzikler.setAdapter(adp);


        }



        private void registerHandlers()
        {

            cboMuzikler.setOnItemSelectedListener(new SpinnerSecici());
        }

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            init();
            registerHandlers();
        }


    }
