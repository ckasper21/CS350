/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 31st, 2018
 */

package surveytest;

import io.*;
import questions.*;

import java.io.*;
import java.util.*;

public class Survey implements Serializable {

    protected String name;
    protected ArrayList<Question> questions;
    protected int numQuestions;
    protected Input in;
    protected Output o;

    public Survey() {
        this.name = "";
        this.questions = new ArrayList<Question>();
        this.numQuestions = 0;
        this.in = new Input();
        this.o = new Output();
    }

    // Set name for survey
    public void setName(String s) {
        this.name = s;
    }

    // Get name for survey
    public String getName() {
        return this.name;
    }

    // Add question to survey, increment number of questions
    public void addQuestion(Question q) {
        this.questions.add(q);
        this.numQuestions++;
    }

    // Remove a specific question
    public void removeQuestion(int i) {
        this.questions.remove(i);
        this.numQuestions--;
    }

    // Edit a specific question
    public void editQuestion(int i) {
        // TODO implement here
    }

    // Get list of questions associated with this survey
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    // Display survey (just questions)
    public void display() {
        this.o.setDisplay("\n####################\n" + this.getName() +"\n####################\n");
        this.o.getDisplay();
        for (Question q: this.questions) {
            q.display();
            this.o.setDisplay("\n");
            this.o.getDisplay();
        }
    }

    // TODO: tabulate survey
    public void tabulate() {
    }

}