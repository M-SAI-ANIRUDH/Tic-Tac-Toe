package com.msa.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();

        LogoLaunch logoLaunch = new LogoLaunch();
        logoLaunch.start();


    }

    public class LogoLaunch extends Thread{
        public void run(){
            try{
                sleep(2500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
            SecondActivity.this.finish();
        }
    }
}
