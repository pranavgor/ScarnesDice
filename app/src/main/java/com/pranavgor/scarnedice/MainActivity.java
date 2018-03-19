package com.pranavgor.scarnedice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rng = new Random();
    int t_score=0, u_score=0, com_score=0, flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(250);
        rotate.setInterpolator(new LinearInterpolator());

        final AlphaAnimation alpha = new AlphaAnimation(1.0f,0.5f);
        alpha.setDuration(150);
        alpha.setInterpolator(new LinearInterpolator());

        final AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(alpha);
        set.setInterpolator(new LinearInterpolator());

        final ImageView dice = (ImageView) findViewById(R.id.dice);
        final TextView score_user = (TextView) findViewById(R.id.score_user);
        final TextView score_comp = (TextView) findViewById(R.id.score_comp);
        final TextView who_won = (TextView) findViewById(R.id.who_won);
        final TextView turn_score_display = (TextView) findViewById(R.id.turn_score_display);
        final Button reset = (Button) findViewById(R.id.reset);
        final Button roll = (Button) findViewById(R.id.roll);
        final Button hold = (Button) findViewById(R.id.hold);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                who_won.setText("");
                rolldice(dice, turn_score_display, score_user, score_comp, who_won);
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t_score = 0;
                u_score = 0;
                com_score = 0;
                turn_score_display.setText("Turn Score : " + Integer.toString(t_score));
                score_user.setText(Integer.toString(u_score));
                score_comp.setText(Integer.toString(com_score));
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holdit(turn_score_display, score_user, score_comp, who_won);
                int count = rng.nextInt(3)+1;
                int number = rng.nextInt(6)+1;
                t_score = t_score+number;
                int i=0;
                while (number != 1 && i<count ){
                    number = rng.nextInt(6)+1;
                    t_score = t_score + number;
                    i++;
                }
                if(number == 1)
                {
                    t_score = 0;
                    holdcomp(turn_score_display, score_comp);
                }
                else
                {
                    holdcomp(turn_score_display, score_comp);
                }
                if(com_score >= 100)
                {
                    who_won.setText("Computer Won !!!");
                    t_score = 0;
                    u_score = 0;
                    com_score = 0;
                    turn_score_display.setText("Turn Score : " + Integer.toString(t_score));
                    score_user.setText(Integer.toString(u_score));
                    score_comp.setText(Integer.toString(com_score));
                    Toast.makeText(getApplicationContext(), "Computer Won !!", Toast.LENGTH_LONG).show();
                }
                if (flag == 1)
                {
                    t_score = 0;
                    u_score = 0;
                    com_score = 0;
                    turn_score_display.setText("Turn Score : " + Integer.toString(t_score));
                    score_user.setText(Integer.toString(u_score));
                    score_comp.setText(Integer.toString(com_score));
                    flag = 0;
                }
            }
        });

    }

    private void rolldice(ImageView dice, TextView turnscore, TextView finalscore, TextView compscore, TextView winner) {
        int number = rng.nextInt(6) + 1;
        t_score = t_score+number;
        turnscore.setText("Turn Score : " + Integer.toString(t_score));

        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(250);
        rotate.setInterpolator(new LinearInterpolator());

        AlphaAnimation alpha = new AlphaAnimation(1.0f,0.5f);
        alpha.setDuration(150);
        alpha.setInterpolator(new LinearInterpolator());

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(alpha);
        set.setInterpolator(new LinearInterpolator());

        switch(number) {
            case 1: dice.startAnimation(set);
                    dice.setImageResource(R.drawable.dice1);
                    t_score = 0;
                    holdit(turnscore,finalscore,compscore,winner);
                    int count = rng.nextInt(3)+1;
                    int no = rng.nextInt(6)+1;
                    t_score = t_score+no;
                    turnscore.setText("Turn Score : " + Integer.toString(t_score));
                    int i=0;
                    while (no != 1 && i<count ){
                        no = rng.nextInt(6)+1;
                        t_score = t_score + no;
                        i++;
                    }
                    if(no == 1)
                    {
                        t_score = 0;
                        holdcomp(turnscore, compscore);
                    }
                    else
                    {
                        holdcomp(turnscore, compscore);
                    }
                    if(com_score >= 100)
                    {
                        winner.setText("Computer Won !!!");
                        t_score = 0;
                        u_score = 0;
                        com_score = 0;
                        turnscore.setText("Turn Score : " + Integer.toString(t_score));
                        finalscore.setText(Integer.toString(u_score));
                        compscore.setText(Integer.toString(com_score));
                        Toast.makeText(getApplicationContext(), "Computer Won !!", Toast.LENGTH_LONG).show();
                    }
                    if (flag == 1)
                    {
                        t_score = 0;
                        u_score = 0;
                        com_score = 0;
                        turnscore.setText("Turn Score : " + Integer.toString(t_score));
                        finalscore.setText(Integer.toString(u_score));
                        compscore.setText(Integer.toString(com_score));
                        flag = 0;
                    }
                    break;
            case 2: dice.startAnimation(set);
                    dice.setImageResource(R.drawable.dice2);
                    break;
            case 3: dice.startAnimation(set);
                    dice.setImageResource(R.drawable.dice3);
                    break;
            case 4: dice.startAnimation(set);
                    dice.setImageResource(R.drawable.dice4);
                    break;
            case 5: dice.startAnimation(set);
                    dice.setImageResource(R.drawable.dice5);
                    break;
            case 6: dice.startAnimation(set);
                    dice.setImageResource(R.drawable.dice6);
                    break;
        }
    }

    private void holdit(TextView turnscore, TextView finalscore, TextView compscore, TextView winner) {
        u_score = u_score + t_score;
        if(u_score >= 100)
        {
            winner.setText("You Won !!!");
            t_score = 0;
            u_score = 0;
            com_score = 0;
            turnscore.setText("Turn Score : " + Integer.toString(t_score));
            finalscore.setText(Integer.toString(u_score));
            compscore.setText(Integer.toString(com_score));
            flag=1;
            Toast.makeText(getApplicationContext(), "You Won !!", Toast.LENGTH_LONG).show();
        }
        t_score = 0;
        turnscore.setText("Turn Score : " + Integer.toString(t_score));
        finalscore.setText(Integer.toString(u_score));
    }

    private void holdcomp(TextView turnscore, TextView finalscore) {
        com_score = com_score + t_score;
        t_score = 0;
        turnscore.setText("Turn Score : " + Integer.toString(t_score));
        finalscore.setText(Integer.toString(com_score));
    }

    final Handler handler = new Handler();
    private Runnable var = new Runnable() {
        @Override
        public void run() {
            // Do something after 5s = 5000ms
        }
    };

}

