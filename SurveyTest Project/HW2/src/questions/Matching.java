/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

package questions;
import io.*;
import java.io.Serializable;
import java.util.*;

public class Matching extends Question implements Serializable {

    protected ArrayList<String> choicesToMatch;

    public Matching(Input in, Output o) {
        super(in,o);
        this.choicesToMatch = new ArrayList<String>();
        this.maxResponses = 0;
    }

    // Add choices to list of choices to match (left side of question)
    public void addChoicesToMatch(String c) {
        this.choicesToMatch.add(c);
        this.maxResponses++;
    }

    // Get choices to match
    public ArrayList<String> getChoicesToMatch() {
        return this.choicesToMatch;
    }

    // Add choice
    public void addChoice(String c) {
        this.choices.add(c);
    }

    // Get choices
    public ArrayList<String> getChoices() {
        return this.choices;
    }

    // Format question in two columns
    private String choicesToString() {
        String stringChoices = "";
        char alphabet = 'A';
        int idx=0;


        for (String s: this.getChoices()){
            stringChoices += "".format("%s %-25s %s %s\n",alphabet+")",this.choicesToMatch.get(idx),idx+1+")",s);

            //stringChoices += alphabet+") " + s + "      " + (idx+1) + ") " + this.choiceAnswers.get(idx) + "\n";
            alphabet++;
            idx++;
        }

        return stringChoices;
    }

    // Answer matching question
    public void answerQuestion(Input in) {
        o.setDisplay("Enter Answer:\n");
        o.getDisplay();

        char alphabet = 'A';

        this.userResponse = new RCA(this.in,this.o);
        for (int i = 0; i < this.getMaxResponses(); i++) {
            o.setDisplay(String.valueOf(alphabet) + " ");
            o.getDisplay();
            this.userResponse.addResponse();
            alphabet++;
        }
        this.setUserResponse(this.userResponse);
    }

    @Override
    public void display() {
        String choicesString = choicesToString();

        this.o.setDisplay(this.getPrompt() + "\n");
        this.o.getDisplay();
        this.o.setDisplay(choicesString + "\n");
        this.o.getDisplay();

    }
}