package com.android.edraak.Model;

public class QuizModel {

    private String question ;
    private String firstAnswer ;
    private String secondeAnswer ;
    private String thirdAnswer ;
    private String lastAnswer ;
    private int correctAnswer ;

    public QuizModel() {
    }

    public QuizModel(String question, String firstAnswer, String secondeAnswer, String thirdAnswer, String lastAnswer, int correctAnswer) {
        this.question = question;
        this.firstAnswer = firstAnswer;
        this.secondeAnswer = secondeAnswer;
        this.thirdAnswer = thirdAnswer;
        this.lastAnswer = lastAnswer;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public String getSecondeAnswer() {
        return secondeAnswer;
    }

    public void setSecondeAnswer(String secondeAnswer) {
        this.secondeAnswer = secondeAnswer;
    }

    public String getThirdAnswer() {
        return thirdAnswer;
    }

    public void setThirdAnswer(String thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }

    public String getLastAnswer() {
        return lastAnswer;
    }

    public void setLastAnswer(String lastAnswer) {
        this.lastAnswer = lastAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
