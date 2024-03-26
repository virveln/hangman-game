package com.example.labb2_vg;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HangmanGame extends AppCompatActivity {

    private TextView show_count, show_guessed_letters, show_word;
    private TextView a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
    private Button btn_rules;
    private WordGenerator wordGenerator;
    private SecretWord secretWord;
    private String letters_guessed = "";
    private int count = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);

        show_count = findViewById(R.id.quantity_guesses);
        show_guessed_letters = findViewById(R.id.letter_guessed);
        show_word = findViewById(R.id.secret_word);
        btn_rules = findViewById(R.id.rules);
        a = findViewById(R.id.letter_a);
        b = findViewById(R.id.letter_b);
        c = findViewById(R.id.letter_c);
        d = findViewById(R.id.letter_d);
        e = findViewById(R.id.letter_e);
        f = findViewById(R.id.letter_f);
        g = findViewById(R.id.letter_g);
        h = findViewById(R.id.letter_h);
        i = findViewById(R.id.letter_i);
        j = findViewById(R.id.letter_j);
        k = findViewById(R.id.letter_k);
        l = findViewById(R.id.letter_l);
        m = findViewById(R.id.letter_m);
        n = findViewById(R.id.letter_n);
        o = findViewById(R.id.letter_o);
        p = findViewById(R.id.letter_p);
        q = findViewById(R.id.letter_q);
        r = findViewById(R.id.letter_r);
        s = findViewById(R.id.letter_s);
        t = findViewById(R.id.letter_t);
        u = findViewById(R.id.letter_u);
        v = findViewById(R.id.letter_v);
        w = findViewById(R.id.letter_w);
        x = findViewById(R.id.letter_x);
        y = findViewById(R.id.letter_y);
        z = findViewById(R.id.letter_z);

        //Loop som sätter klicklyssnare för a-z som anaropar "updateDisplay"
        for(char index = 'a'; index <= 'z'; index++){
            int resId = getResources().getIdentifier("letter_" + index, "id", getPackageName());
            TextView alphabet = findViewById(resId);
            alphabet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateDisplay(alphabet);
                }
            });
        }

        btn_rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rulesDialog();
            }
        });

        //Startar spel
        startNewGame();
    }

    public HangmanGame(){
        wordGenerator = new WordGenerator();
    }

    public void startNewGame(){
        //Hämtar ett slumpat ord och visar ordet genom antal streck
        secretWord = new SecretWord(wordGenerator.getHangmanWord());
        show_word.setText(secretWord.getWordHidden());
    }

    private void updateDisplay(TextView clicked_letter){
        if(secretWord.checkLetter(clicked_letter, wordGenerator.getHangmanWord()) == false) {
            //Räknar antalet felgissningar
            count -= 1;
            show_count.setText(String.valueOf(count));

            //Visar vilka bokstäver som felgissats
            letters_guessed += clicked_letter.getText().charAt(0) + ", ";
            show_guessed_letters.setText(letters_guessed);

            //Uppdaterar hangman-bild
            updateHangman();
        }
        //Gör gissad bokstav oklickbar och osynlig
        clicked_letter.setOnClickListener(null);
        clicked_letter.setBackgroundColor(Color.LTGRAY);

        //Uppdaterar hangman-text
        show_word.setText(String.valueOf(secretWord.getWordHiddenArray()));

        //Kontrollerar om spelet är slut
        checkGameOver();
    }

    private void updateHangman(){
        //Uppdaterar bild beroende på hur många felgissningar som gjorts
        ImageView img = (ImageView) findViewById(R.id.image);
        if(count == 6) {
            img.setImageResource(R.drawable.hangman1);
        }else if(count == 5){
            img.setImageResource(R.drawable.hangman2);
        }else if(count == 4){
            img.setImageResource(R.drawable.hangman3);
        }else if(count == 3){
            img.setImageResource(R.drawable.hangman4);
        }else if(count == 2){
            img.setImageResource(R.drawable.hangman5);
        }else if(count == 1){
            img.setImageResource(R.drawable.hangman6);
        }else if(count == 0){
            img.setImageResource(R.drawable.hangman7);
        }
    }

    //Kontrollerar om spelet är klart och om spelare vann eller inte
    private void checkGameOver(){
        int count_full_word = 0;
        for(int i = 0; i < secretWord.getWordHiddenArray().length; i++){
            if(secretWord.getWordHiddenArray()[i] != '_') {
                count_full_word += 1;
            }
        }

        //Anropar dialogruta med argument beroende på om vinst eller förlust
        if(count_full_word == secretWord.getWordHiddenArray().length){
            gameOverDialog(true);
        } else if (count == 0) {
            gameOverDialog(false);
        }
    }

    private void gameOverDialog(boolean result) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        //Om spelare vann
        if (result == true) {
            dialog.setTitle("Congrats! You won!");
            dialog.setMessage("The word was " + wordGenerator.getHangmanWord());
        }
        //Om spelare förlorade
        else if (result == false) {
            dialog.setTitle("You lost!");
            dialog.setMessage("The word was " + wordGenerator.getHangmanWord());
        }

        //Dialogknapp för att spela igen
        dialog.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), HangmanGame.class);
                startActivity(intent);
            }
        });

        //Dialogknapp för att gå till meny
        dialog.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog_show = dialog.create();
        dialog_show.show();

        //Så man inte kan klicka på kvarvarande bokstäver efter spelet är klart om man klickar ner dialogrutan
        for (char index = 'a'; index <= 'z'; index++) {
            int resId = getResources().getIdentifier("letter_" + index, "id", getPackageName());
            TextView alphabet = findViewById(resId);
            alphabet.setOnClickListener(null);
        }
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

        //Dialogknapp för ok
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        //Dialogknapp för att starta om spel
        dialog.setNegativeButton("Restart Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), HangmanGame.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog_show = dialog.create();
        dialog_show.show();
    }
}