/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 27st, 2018
 */

package menu;
import surveytest.*;
import io.*;
import questions.*;

import java.io.*;
import java.util.*;

public abstract class Menu implements Serializable {

    protected int numChoices;
    protected String menuName;
    protected ArrayList<String> menuOptions;
    protected Survey survey;
    protected Input in;
    protected Output o;

    public Menu(Survey s) {
        this.survey = s;
        this.menuOptions = new ArrayList<String>();
        this.in = new ConsoleInput();
        this.o = new ConsoleOutput();
    }

    // Run menu (will drive program)
    public Menu run() {
        return null;
    }

    // Get user menu selection
    public int getSelection() {
        int sel = 0;
        String response;

        while (sel > this.numChoices || sel < 1) {
            this.o.setDisplay("Enter selection: ");
            this.o.getDisplay();

            try {
                response = in.getUserInput();
                sel = Integer.parseInt(response);
            } catch (NumberFormatException e) {
            }
        }
            return sel;
    }

    // Display the menu
    public void display() {
        String s = "";
        s += "\n"+this.menuName+"\n-----------\n";
        for (int i=0; i < this.numChoices; i++) {
            s += (String)((i+1) + ") " + this.menuOptions.get(i)) + "\n";
        }
        this.o.setDisplay(s);
        this.o.getDisplay();
    }

}