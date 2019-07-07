/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
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

    // Display question
    @Override
    public void display() {
        this.o.setDisplay(this.getPrompt() + "\n");
        this.o.getDisplay();
        this.o.setDisplay("T/F" + "\n");
        this.o.getDisplay();

    }
}