package com.example.model_view_controller.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model_view_controller.R;
import com.example.model_view_controller.model.QuestionBank;
import com.example.model_view_controller.model.Questions;
import com.example.model_view_controller.model.User;

import java.util.Arrays;

public class Game extends AppCompatActivity implements View.OnClickListener {

    private TextView lblQuestion1;
    private Button btnRep1;
    private Button btnRep2;
    private Button btnRep3;
    private QuestionBank q;
    private Questions current_q;
    private int nbQuestion;
    private int score_game = 0;
    public static String EXTRA_SCORE = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        lblQuestion1 = (TextView)findViewById(R.id.lblQuestion1);
        btnRep1 = (Button)findViewById(R.id.btnRepJaune);
        btnRep2 = (Button)findViewById(R.id.btnRepBleu);
        btnRep3 = (Button)findViewById(R.id.btnRepVert);
        btnRep1.setTag( 0 );
        btnRep2.setTag( 1 );
        btnRep3.setTag( 2 );

        btnRep1.setOnClickListener( this );
        btnRep2.setOnClickListener( this );
        btnRep3.setOnClickListener( this );

        nbQuestion = 4;
        q = this.generateQuestion();
        current_q = q.getQuestion();
        afficheQuestion( current_q );

    }

    public QuestionBank generateQuestion () {
        Questions q1 = new Questions("A quoi est égal 1 octet ?",
                Arrays.asList( "8 bits", "8 bytes", "10 bits" ),
                0 );
        Questions q2 = new Questions( "Qu'est-ce qu'un CPU ?",
                Arrays.asList( "un processeur", "un disque dur", "une carte graphique" ),
                0 );
        Questions q3 = new Questions( "En quelle unité mesure-t-on la fréquence d’un microprocesseur ?",
                Arrays.asList( "En Go", "En GHz", "En MIPS" ),
                1 );
        Questions q4 = new Questions( "Combien vaut 1 Mo ?",
                Arrays.asList( "1 million d’octets", "1000 octets", "1024 octets"),
                1 );
        Questions q5 = new Questions( "Comment appelle-t-on l´écran de l’ordinateur ?",
                Arrays.asList( "L´unité centrale", "Le CPU", "Le moniteur"),
                2 );
        return new QuestionBank(Arrays.asList( q1, q2, q3, q4, q5 ) );
    }


    public void afficheQuestion ( Questions q ) {
        lblQuestion1.setText( q.getQuestion() );
        btnRep1.setText( q.getListeReponse().get( 0 ) );
        btnRep2.setText( q.getListeReponse().get( 1 ) );
        btnRep3.setText( q.getListeReponse().get( 2 ) );
    }

    @Override
    public void onClick(View v) {
        int idBtn = ( int ) v.getTag();
        if ( idBtn == current_q.getIndex() ) {
            Toast.makeText (this,  "correct",Toast.LENGTH_SHORT ).show();
            score_game++;
        }else{
            Toast.makeText (this,  "incorrect",Toast.LENGTH_SHORT ).show();
        }
        if ( --nbQuestion == 0 ){
            endGame();
        }else{
            current_q = q.getQuestion();
            afficheQuestion( current_q );
        }
    }

    public void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this );
            builder.setTitle( " Bien joué !!!" ).setMessage( "score : " + score_game + " sur 4" )
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        // je récupère le score pour l'envoyer dans mainActivity
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i("Info", "dialBox endGame clicked");
                            Intent intent = new Intent();
                            intent.putExtra( EXTRA_SCORE, score_game);
                            setResult( RESULT_OK, intent);
                            finish();
                        }
                    })
                    .create()
                    .show();
    }


}