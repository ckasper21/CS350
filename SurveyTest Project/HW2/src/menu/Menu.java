/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 1st, 2018
 */

package menu;
import surveytest.*;
import io.*;

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
        this.in = new Input();
        this.o = new Output();
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

    public void createSurvey() {

    }

    public void loadSurvey() {

    }

    // Save the survey/test
    public void saveSurvey() {
        if (this.survey == null) {
            this.o.setDisplay("No survey/test loaded!\n");
            this.o.getDisplay();
            return;
        }

        String dir = "./" + this.menuName.toLowerCase().split(" ")[0] + "/";
        String fileName = dir + this.survey.getName() + "." + this.menuName.toLowerCase().split(" ")[0]+"ser";

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this.survey);

            out.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Display survey/test
    public void displaySurvey() {
        if (this.survey == null) {
            this.o.setDisplay("No survey/test loaded!\n");
            this.o.getDisplay();
        } else {
            this.survey.display();
        }
    }

}