package com.example.model_view_controller.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.model_view_controller.R;
import com.example.model_view_controller.model.Questions;
import com.example.model_view_controller.model.User;

import org.xml.sax.ext.Locator2;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private EditText editTxtPseudo;
    private TextView lblAffichePseudo;
    private User user;
    public static final int CODE_ACTIVITY_GAME = 2; // code que j'attribut pour l'activitée Game
    private SharedPreferences preference;
    private static String PREFERENCE_USER_NAME = "PREFERENCE_USER_NAME";
    private static String PREFERENCE_SCORE = "PREFERENCE_SCORE";

    // méthode de récupération des données (score) dans l' activitée Game
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (CODE_ACTIVITY_GAME == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(Game.EXTRA_SCORE, 0);
            preference.edit().putInt( PREFERENCE_SCORE, score ).apply();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxtPseudo = ( EditText ) findViewById( R.id.editTxtPseudo );
        btnPlay = ( Button ) findViewById( R.id.btnPlay );
        lblAffichePseudo = ( TextView ) findViewById( R.id.lblAffichePseudo );

        btnPlay.setOnClickListener(btnPlayListener);

        user = new User();
        preference = getPreferences(MODE_PRIVATE);

        gestionPrefAcceuil();

    }



    public View.OnClickListener btnPlayListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("info", "bouton play clicked !!!");
            user.setUserName( editTxtPseudo.getText().toString() );

            preference.edit().putString( PREFERENCE_USER_NAME, user.getUserName() ).apply();

            Intent gameIntent = new Intent(MainActivity.this, Game.class);
            //startActivity(gameIntent);
            startActivityForResult( gameIntent, CODE_ACTIVITY_GAME);
        }
    };

    @SuppressLint("SetTextI18n")
    public void gestionPrefAcceuil() {

        boolean verifification = false;
        verifification = getPreferences(MODE_PRIVATE).contains( PREFERENCE_USER_NAME );

        if( verifification ) {
            String pref_pseudo = getPreferences(MODE_PRIVATE).getString( PREFERENCE_USER_NAME , null);
            int pref_score = getPreferences(MODE_PRIVATE).getInt( PREFERENCE_SCORE , 0);
            lblAffichePseudo.setText( "Bonjour " + pref_pseudo + " !" + "\n" + "Ton précédent score est de : " + pref_score + " sur 4");
            editTxtPseudo.setText( pref_pseudo );
        }else{
            lblAffichePseudo.setText("Bienvenue à toi nouveau joueur, quel est ton nom ?");
        }
    }


}