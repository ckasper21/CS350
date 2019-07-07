/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 13th, 2018
 */

package questions;
import io.*;

import java.io.CharConversionException;
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

    // Get letters of choices to be assigned answers
    private ArrayList<String> choiceOptions() {
        ArrayList<String> options = new ArrayList<String>();
        int choice = 1;

        char alphabet = 'A';

        for (String s: choices) {
            options.add(Character.toString(alphabet));
            alphabet++;
        }
        return options;
    }

    // Answer multiple choice question
    @Override
    public void answerQuestion() {
        ArrayList<String> options = this.choiceOptions();
        ArrayList<String> userResp = new ArrayList<String>();

        this.o.setDisplay("Enter Answer:\n");
        this.o.getDisplay();

        this.userResponse = new RCA(this.in,this.o);

        for (int i = 0; i < this.getMaxResponses(); i++) {
            this.userResponse.addResponse();
            while (!options.contains(this.userResponse.getResponses().get(i)) || userResp.contains(this.userResponse.getResponses().get(i))) {
                if (userResp.contains(this.userResponse.getResponses().get(i))) {
                    this.o.setDisplay("You already gave that answer...try again\n");
                    this.o.getDisplay();
                }
                this.userResponse.removeResponse(i);
                o.setDisplay("Enter a valid answer\n");
                o.getDisplay();
                this.userResponse.addResponse();
            }
            userResp.add(this.userResponse.getResponses().get(i));
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