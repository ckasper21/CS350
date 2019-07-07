/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

package questions;
import io.*;
import java.io.Serializable;
import java.util.*;

public class RCA implements Serializable {
    protected ArrayList<String> responses;
    Input in;
    Output o;

    public RCA(Input in, Output o) {
        this.in = in;
        this.o = o;
        this.responses = new ArrayList<String>();
    }

    // Get list of responses with this RCA
    public ArrayList<String> getResponses() {
        return this.responses;
    }

    // Add response to question
    public void addResponse() {
        this.responses.add(in.getUserInput());
    }

    // Add response to question (for adding test answers during test creation/modify)
    public void addResponse(String s) {
        this.responses.add(s);
    }

    // Remove specific response with this RCA
    public void removeResponse(int i) {
        this.responses.remove(i);
    }

    // Compare two RCAs
    public Boolean compare(RCA a) {
        for (int i=0; i<responses.size(); i++) {
            String s1 = this.responses.get(i);
            String s2 = a.responses.get(i);

            if (!s1.equals(s2)) {
                return false;
            }
        }
        return true;
    }

}