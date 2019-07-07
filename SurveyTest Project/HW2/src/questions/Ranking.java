/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

package questions;
import io.*;
import java.io.Serializable;
import java.util.*;

public class Ranking extends Matching implements Serializable {

    public Ranking(Input in, Output o) {
        super(in,o);
    }

    // Answer Ranking question
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
        this.o.setDisplay(this.getPrompt() + "\n");
        this.o.getDisplay();
    }
}