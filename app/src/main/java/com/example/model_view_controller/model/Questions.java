package com.example.model_view_controller.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Questions {

    private String question;
    private List<String> listeReponse;
    private int index;

    public void setListeReponse(List<String> listeReponse) {
            this.listeReponse = listeReponse;
        }

    public void setIndex(int index) {
        this.index = index;
        }
    public void setQuestion(String question) {
            this.question = question;
        }
    public String getQuestion() {
        return this.question;
    }
    public int getIndex() {
        return index;
    }
    public List<String> getListeReponse() {
        return listeReponse;
    }

    public Questions ( String s, List<String>l, int i) {
        setQuestion( s );
        setListeReponse( l );
        setIndex( i );
    }

}
