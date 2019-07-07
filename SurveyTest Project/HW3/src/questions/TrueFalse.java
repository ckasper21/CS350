/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 18th, 2018
 */

package questions;
import io.*;
import java.io.Serializable;
import java.util.*;

public class TrueFalse extends MultipleChoice implements Serializable {

    public TrueFalse(Input in, Output o) {
        super(in,o);
        this.maxResponses = 1;
    }

    // Get choices for this question
    private ArrayList<String> choiceOptions() {
        ArrayList<String> options = new ArrayList<String>();
        options.add("T");
        options.add("F");
        return options;
    }

    // Display question
    @Override
    public void display() {
        this.o.setDisplay(this.getPrompt() + "\n");
        this.o.getDisplay();
        this.o.setDisplay("T/F" + "\n");
        this.o.getDisplay();

    }

    // Answer true/false question
    @Override
    public void answerQuestion() {
        ArrayList<String> options = this.choiceOptions();

        this.o.setDisplay("Enter Answer:\n");
        this.o.getDisplay();

        this.userResponse = new RCA(this.in,this.o);

        for (int i = 0; i < this.getMaxResponses(); i++) {
            this.userResponse.addResponse();
            while (!options.contains(this.userResponse.getResponses().get(i))) {
                this.userResponse.removeResponse(i);
                o.setDisplay("Enter a valid answer\n");
                o.getDisplay();
                this.userResponse.addResponse();
            }
        }
        this.setUserResponse(this.userResponse);
    }
}