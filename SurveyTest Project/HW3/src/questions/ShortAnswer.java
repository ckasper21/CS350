/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 13th, 2018
 */

package questions;
import io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class ShortAnswer extends Essay implements Serializable {
    protected int charLimit;

    public ShortAnswer(Input in, Output o) {
        super(in,o);
        this.charLimit = 0;
    }

    // Get character limit for question
    public int getCharLimit() {
        return this.charLimit;
    }

    // Set character limit for question
    public void setCharLimit(int i) {
        this.charLimit = i;
    }

    // Answer short answer question
    @Override
    public void answerQuestion() {
        ArrayList<String> userResp = new ArrayList<String>();

        this.o.setDisplay("Enter Answer:\n");
        this.o.getDisplay();

        int tmpLength;

        this.userResponse = new RCA(this.in,this.o);
        for (int i = 0; i < this.getMaxResponses(); i++) {
            this.userResponse.addResponse();

            tmpLength = this.userResponse.getResponses().get(i).length();
            while (tmpLength > this.getCharLimit() || userResp.contains(this.userResponse.getResponses().get(i))) {
                if (tmpLength > this.getCharLimit()) {
                    this.o.setDisplay("Answer too long...try again\n");
                    this.o.getDisplay();
                } else {
                    this.o.setDisplay("You already gave that answer...try again\n");
                    this.o.getDisplay();
                }

                this.userResponse.removeResponse(i);
                this.userResponse.addResponse();
                tmpLength = this.userResponse.getResponses().get(i).length();
            }
            userResp.add(this.userResponse.getResponses().get(i));
        }
        this.setUserResponse(this.userResponse);
    }

    @Override
    public void display() {
        this.o.setDisplay(this.getPrompt() + " (" + this.getCharLimit() + " character limit)\n");
        this.o.getDisplay();
    }
}