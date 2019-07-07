/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

package questions;
import io.*;
import java.io.Serializable;
import java.util.*;

public class MultipleChoice extends Question implements Serializable {

    public MultipleChoice(Input in, Output o) {
        super(in,o);
    }

    // Add choice
    public void addChoice(String c) {
        this.choices.add(c);
    }

    // Get choices
    public ArrayList<String> getChoices() {
        return this.choices;
    }

    // Format question
    private String choicesToString() {
        String stringChoices = "";
        char alphabet = 'A';

        for (String s: this.getChoices()){
            stringChoices += alphabet+") " + s + "  ";
            alphabet++;
        }

        return stringChoices + "\n";
    }

    // Answer multiple choice question
    @Override
    public void answerQuestion() {
        this.o.setDisplay("Enter Answer:\n");
        this.o.getDisplay();

        this.userResponse = new RCA(this.in,this.o);
        for (int i = 0; i < this.getMaxResponses(); i++) {
            this.userResponse.addResponse();
        }
        this.setUserResponse(this.userResponse);
    }

    @Override
    public void display() {
        String choiceString = choicesToString();

        this.o.setDisplay(this.getPrompt()+"\n");
        this.o.getDisplay();
        this.o.setDisplay(choiceString);
        this.o.getDisplay();

    }
}