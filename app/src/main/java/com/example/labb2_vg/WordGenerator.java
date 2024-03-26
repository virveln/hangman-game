package com.example.labb2_vg;

import android.util.Log;

import java.util.Random;

public class WordGenerator {
    private String hangman_word = "";
    private String[] words_list = {
            "sturdy",
            "jolly",
            "weight",
            "loaf",
            "brother",
            "vessel",
            "transport",
            "describe",
            "loose",
            "animated",
            "alive",
            "overrated",
            "book",
            "intelligent",
            "bang",
            "plastic",
            "safe",
            "punch",
            "drum",
            "squalid"
    };

    public WordGenerator(){
        randomWord();
    }
    public void randomWord(){
        //slumpar indexplats till vektor innehållande ord som ska gissas
        Random r = new Random();
        int rIndex = r.nextInt(words_list.length);
        hangman_word = words_list[rIndex].toUpperCase();
    }

    public String getHangmanWord(){
        return hangman_word;
    }
    public void setHangmanWord(String hangman_word){
        this.hangman_word = hangman_word;
    }
}