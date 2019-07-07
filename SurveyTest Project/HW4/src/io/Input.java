 /**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

package io;
import java.io.Serializable;
import java.util.*;

public abstract class Input implements Serializable {
    protected String userInput;

    public Input() {
        this.userInput="";
    }

    // Set user input, won't accept empty response
    public void setUserInput() {
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        while (response.trim().equals("")) {
            response = sc.nextLine();
        }
        this.userInput = response;
    }

    // Get user input
    public String getUserInput() {
        this.setUserInput();
        return this.userInput;
    }

}