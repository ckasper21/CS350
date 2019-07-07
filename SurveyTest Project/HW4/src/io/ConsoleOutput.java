/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 27th, 2018
 */

package io;

public class ConsoleOutput extends Output {

    public ConsoleOutput() {
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
