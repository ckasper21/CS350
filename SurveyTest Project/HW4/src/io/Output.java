/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 30th, 2018
 */

package io;
import java.io.Serializable;

public abstract class Output implements Serializable {
    protected String outputString;

    public Output() {
    }

    // Print out to console
    public void getDisplay() {
    }

    // Set text to print to console
    public void setDisplay(String s) {
    }

}