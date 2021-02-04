package com.msa.tic_tac_toe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   // private InterstitialAd mInterstitialAd;

    private Button[][] buttons = new Button[3][3];

    private Boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
   // private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        //MobileAds.initialize(this, "ca-app-pub-1091775549314057~8091837422");

        /*
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1091775549314057/1537552263");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

         */


        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id",getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i==j) {
                    buttons[i][j].setBackgroundColor(getResources().getColor(R.color.defek));
                }
                if(i==0 && j==1 || i==1 && j==2 || i==1 && j==0 || i==2 && j==1){
                    buttons[i][j].setBackgroundColor(getResources().getColor(R.color.defdo));
                }
                if(i==0 && j==2 || i==2 && j==0){
                    buttons[i][j].setBackgroundColor(getResources().getColor(R.color.defteen));
                }
            }
        }

        Button buttonReset = findViewById(R.id.button_resrt);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    @Override


    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals(""))
        {
            return;
        }

        if(player1Turn){
            ((Button) v).setText("X");
            ((Button) v).setBackgroundColor(getResources().getColor(R.color.orange));
        }else{
            ((Button) v).setText("O");
            ((Button) v).setBackgroundColor(getResources().getColor(R.color.green));

        }

        roundCount++;

        if(checkForWin()){
            if(player1Turn){
                player1wins();
            }else{
                player2wins();
            }
        }else if(roundCount == 9)
        {
            draw();
        }else{
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin(){
        String[][] field = new String[3][3];

        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i=0; i<3 ; i++){
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")){
                return true;
            }
        }

        for(int i=0; i<3 ; i++){
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    private  void player1wins(){
        player1Points++;
        Toast.makeText(this, "PLAYER 1 WINS !!!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2wins(){
        player2Points++;
        Toast.makeText(this, "PLAYER 2 WINS !!!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private  void draw(){
        Toast.makeText(this, "DRAW !!!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText(){
        textViewPlayer1.setText("PLAYER I  (X):" + player1Points);
        textViewPlayer1.setTextColor(getResources().getColor(R.color.orange));
        textViewPlayer2.setText("PLAYER II (O):" + player2Points);
        textViewPlayer2.setTextColor(getResources().getColor(R.color.green));
    }

    private void resetBoard()
    {
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                if(i==j) {
                    buttons[i][j].setBackgroundColor(getResources().getColor(R.color.defek));
                }
                if(i==0 && j==1 || i==1 && j==2 || i==1 && j==0 || i==2 && j==1){
                    buttons[i][j].setBackgroundColor(getResources().getColor(R.color.defdo));
                }
                if(i==0 && j==2 || i==2 && j==0){
                    buttons[i][j].setBackgroundColor(getResources().getColor(R.color.defteen));
                }
            }
        }

        roundCount =0;
        player1Turn = true;
    }

    private  void resetGame(){
        player1Points =0;
        player2Points =0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1points", player1Points);
        outState.putInt("player2points",player2Points);
        outState.putBoolean("player1Turn",player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");

        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }
}
