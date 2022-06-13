package com.example.langdual.models;

import java.util.List;

public class api_response {
    String word = "";
    List<phonetics> phonetics = null;
    List<significado> meanings = null;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<phonetics> phonetics) {
        this.phonetics = phonetics;
    }

    public List<significado> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<significado> meanings) {
        this.meanings = meanings;
    }

}
