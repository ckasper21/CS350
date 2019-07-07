/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

package questions;
import io.*;

import java.io.Serializable;
import java.util.*;

public abstract class Question implements Serializable {

    protected String questionPrompt;
    protected ArrayList<RCA> allResponses;
    protected RCA userResponse;
    protected int maxResponses;
    protected ArrayList<String> choices;
    protected Input in;
    protected Output o;

    public Question(Input in, Output o) {
        this.questionPrompt = "";
        this.allResponses = new ArrayList<RCA>();
        this.choices = new ArrayList<String>();
        this.in = in;
        this.o = o;
    }

    // Set prompt for question
    public void setPrompt(String s) {
        this.questionPrompt = s;
    }

    // Get question prompt
    public String getPrompt() {
        return this.questionPrompt;
    }

    // Get all responses to this question
    public ArrayList<RCA> getResponses() {
        return this.allResponses;
    }

    // Get the user response for this question
    public RCA getUserResponse() {
        return this.userResponse;
    }

    // Add response to all responses
    public void addResponse(RCA r) {
        this.allResponses.add(r);
    }

    // Set the user response;
    public void setUserResponse(RCA r) {
        this.userResponse = r;
        this.addResponse(r);
    }

    // Set max responses (for multiple answer questions)
    public void setMaxResponses(int i) {
        this.maxResponses = i;
    }

    // Get max responses
    public int getMaxResponses() {
        return this.maxResponses;
    }

    public void answerQuestion() {

    }

    // Get choices for answer
    public ArrayList<String> getChoices() {
        return this.choices;
    }

    // Display question
    public void display() {
        this.o.setDisplay(this.getPrompt() + "\n");
        this.o.getDisplay();
    }

}