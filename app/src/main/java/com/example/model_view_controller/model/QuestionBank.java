package com.example.model_view_controller.model;


import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private final List<Questions> listeQuestions;
    private int nextQuestionIndex;

    public QuestionBank (List<Questions> q_list) {
        listeQuestions = q_list;
        Collections.shuffle(listeQuestions);
        nextQuestionIndex = 0;
    }

    public Questions getQuestion() {
        if( nextQuestionIndex == listeQuestions.size() ) {
            nextQuestionIndex = 0;
        }
        return listeQuestions.get( nextQuestionIndex++ );
    }

}
