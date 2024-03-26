package com.example.labb2_vg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_start_new_game, btn_rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start_new_game = findViewById(R.id.start_new_game);
        btn_rules = findViewById(R.id.rules);

        btn_start_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGame();
            }
        });

        btn_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rulesDialog();
            }
        });

    }

    private void startGame(){
        Intent intent = new Intent(this, HangmanGame.class);
        startActivity(intent);
    }

    private void rulesDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("How to Play:");
        dialog.setMessage("A secret word is chosen, and its letters are hidden, represented by underscores.\n\n" +
                "Your task is to guess the letters of the secret word one by one.\n\n" +
                "Each incorrect guess brings you one step closer to losing the game.\n\n" +
                "If you guess a letter correctly, it will be revealed in its correct position(s) in the word.\n\n" +
                "If you guess all the letters correctly before reaching 7 incorrect guesses, you win!\n\n" +
                "If you reach 7 incorrect guesses before guessing the word, you lose, and the hangman is hanged.\n\n" +
                "\nTips:\n" +
                "• Start with common vowels and consonants when making your initial guesses.\n\n" +
                "• Use logic and word patterns to guess the word correctly.\n\n" +
                "\nGood luck, and have fun playing Hangman!\n");

        //Dialogknapp för ok som stänger dialogen
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog_show = dialog.create();
        dialog_show.show();
    }
}