package com.example.imagepro;

public class SignsList {
    public String letter;
    public String letterTURL;
    public SignsList(String letter, String letterTURL) {
        this.letter = letter;
        this.letterTURL = letterTURL;
    }
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
    public String getLetterTURL() {
        return letterTURL;
    }

    public void setLetterTURL(String letterTURL) {
        this.letterTURL = letterTURL;
    }
}
