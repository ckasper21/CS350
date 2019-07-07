/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 1st, 2018
 */

package menu;
import surveytest.*;
import java.util.*;

public class MainMenu extends Menu {

    public MainMenu(Survey s) {
        super(s);
        this.menuName = "Main Menu";
        this.numChoices = 2;
        this.menuOptions.add("Survey");
        this.menuOptions.add("Test");
    }

    // Run MainMenu
    public Menu run() {
        int sel;
        this.display();

        sel = this.getSelection();

        if (sel == 1) {
            return new SurveyMenu(this.survey);
        } else {
            return new TestMenu(this.survey);
        }
    }
}