/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 13th, 2018
 */

package questions;
import io.*;
import java.io.Serializable;
import java.util.*;

public class Essay extends Question implements Serializable {

    public Essay(Input i, Output o) {
        super(i,o);
    }

    // Answer essay question
    @Override
    public void answerQuestion() {
        this.o.setDisplay("Enter Answer:" + "\n");
        this.o.getDisplay();

        this.userResponse = new RCA(this.in,this.o);
        for (int i = 0; i < this.getMaxResponses(); i++) {
            this.userResponse.addResponse();
        }
        this.setUserResponse(this.userResponse);
    }

}