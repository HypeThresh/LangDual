package com.example.langdual.models;

import java.util.List;

public class significado {
    String partOfSpeech="";
    List<definicion> definitions = null;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<definicion> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<definicion> definitions) {
        this.definitions = definitions;
    }

}
