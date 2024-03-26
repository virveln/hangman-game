package com.example.labb2_vg;

import android.util.Log;
import android.widget.TextView;

public class SecretWord {

    private char[] word_hidden_array;
    private String word_hidden = "";

    public SecretWord(String hangman_word){
        //Visar streck i stället för bokstäver i det hemliga ordet
        for(int i = 0; i < hangman_word.length(); i++){
            word_hidden += "_";
        }
        //Lagrar det gömda ordet i en vektor för att enkelt gemföra vid dissning
        word_hidden_array = word_hidden.toCharArray();
    }

    public boolean checkLetter(TextView clicked_letter, String hangman_word) {
        boolean guess = false;

        //Kontrollerar om gissad bokstav finns i ordet, om sant byts streck ut mot bokstaven
        for (int i = 0; i < hangman_word.toCharArray().length; i++) {
            if (hangman_word.toCharArray()[i] == clicked_letter.getText().charAt(0)) {
                word_hidden_array[i] = clicked_letter.getText().charAt(0);
                setWordHiddenArray(word_hidden_array);

                guess = true;
            }
        }
        return guess;
    }

    public String getWordHidden() {
        return word_hidden;
    }
    public void setWordHidden(String word_hidden) {
        this.word_hidden = word_hidden;
    }

    public char[] getWordHiddenArray() {
        return word_hidden_array;
    }
    public void setWordHiddenArray(char[] word_hidden_array) {
        this.word_hidden_array = word_hidden_array;
    }
}