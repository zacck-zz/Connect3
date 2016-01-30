package com.zacck.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red
    int activePlayer = 0;

    //2 means unplayed
    int[] mGameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean isGameActive = true;


    public void dropin(View view)
    {
        ImageView counter = (ImageView)view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(mGameState[tappedCounter] == 2 && isGameActive) {

            mGameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions)
            {
                if(mGameState[winningPosition[0]] == mGameState[winningPosition[1]] &&
                        mGameState[winningPosition[1]] == mGameState[winningPosition[2]] &&
                        mGameState[winningPosition[0]] != 2)
                {
                    //someone has won
                    isGameActive = false;
                    String winner;
                    if(mGameState[winningPosition[0]] == 0)
                    {
                        winner = "Yellow";
                    }
                    else
                    {
                        winner = "Red";
                    }


                    TextView mWinnerMessage = (TextView)findViewById(R.id.tvWOn);
                    mWinnerMessage.setText(winner + " has Won");
                    LinearLayout mLayout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    mLayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    boolean gameIsOver = true;
                    for(int counterState : mGameState)
                    {
                        if(counterState == 2)
                            gameIsOver =false;
                    }

                    if(gameIsOver)
                    {
                        TextView mWinnerMessage = (TextView)findViewById(R.id.tvWOn);
                        mWinnerMessage.setText("Its A draw");
                        LinearLayout mLayout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        mLayout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }

    }
    public void playAgain(View view)
    {
        isGameActive = true;
        LinearLayout mLayout = (LinearLayout)findViewById(R.id.playAgainLayout);

        mLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for (int i=0; i < mGameState.length; i++)
        {
            mGameState[i] = 2 ;
        }

        GridLayout mGridLayout = (GridLayout)findViewById(R.id.playGrid);

        for(int i = 0; i<mGridLayout.getChildCount(); i++)
        {
            ((ImageView)mGridLayout.getChildAt(i)).setImageResource(0);
        }






    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
