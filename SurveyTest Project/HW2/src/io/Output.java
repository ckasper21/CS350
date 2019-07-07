package io;

import java.io.Serializable;

/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

public class Output implements Serializable {
    protected String outputString;

    public Output() {
        this.outputString ="";
    }

    // Print out to console
    public void getDisplay() {
        System.out.print(this.outputString);
    }

    // Set text to print to console
    public void setDisplay(String s) {
        this.outputString = s;
    }

}