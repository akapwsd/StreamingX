package com.code.bean;

import java.util.ArrayList;

public class LanguageBean {
    private ArrayList<String> languagePairs;

    public ArrayList<String> getLanguagePairs() {
        return languagePairs;
    }

    public void setLanguagePairs(ArrayList<String> languagePairs) {
        this.languagePairs = languagePairs;
    }

    @Override
    public String toString() {
        return "LanguageBean{" +
                "languagePairs=" + languagePairs +
                '}';
    }
}
