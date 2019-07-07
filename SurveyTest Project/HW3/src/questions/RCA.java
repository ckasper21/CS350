/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 13th, 2018
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

    // Compare two RCAs (for grading)33
    // (inOrder = false for short answer, essay, multiple choice, true/false)
    // (inOrder = true for ranking, matching)
    public Boolean compare(RCA a, Boolean inOrder) {
        if (inOrder) {
            for (int i = 0; i < responses.size(); i++) {
                String s1 = this.responses.get(i);
                String s2 = a.responses.get(i);

                if (!s1.equals(s2)) {
                    return false;
                }
            }
        } else {
            // Construct each answer
            ArrayList<String> ans = this.getResponses();
            ArrayList<String> userResp = a.getResponses();

            for (String resp: userResp) {
                if (!ans.contains(resp)) {
                    return false;
                }
            }

        }
        return true;
    }

}