/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 1st, 2018
 */

package menu;
import surveytest.*;

import java.io.Serializable;
import java.util.*;

public class TestMenu extends SurveyMenu implements Serializable {

    public TestMenu(Survey s) {
        super(s);
        this.numChoices = 5;
        this.menuName = "Test Menu";
        this.menuOptions.clear();
        this.menuOptions.add("Create a new Test");
        this.menuOptions.add("Display a Test");
        this.menuOptions.add("Load a Test");
        this.menuOptions.add("Save a Test");
        this.menuOptions.add("Quit");
    }

    // Create test, delegates to CreateMenu object
    @Override
    public void createSurvey() {
        CreateMenu cm = new CreateMenu(this.survey, true);
        cm.run();
        this.survey = cm.getSurvey();
    }

    // Load test, delegates to LoadMenu object
    @Override
    public void loadSurvey() {
        LoadMenu lm = new LoadMenu(this.survey,true);
        lm.run();
        this.survey = lm.getSurvey();
    }

    // Run TestMenu
    @Override
    public Menu run() {
        int sel;
        this.display();
        sel = this.getSelection();

        switch (sel) {
            case 1:
                this.createSurvey();
                break;
            case 2:
                this.displaySurvey();
                break;
            case 3:
                this.loadSurvey();
                break;
            case 4:
                this.saveSurvey();
                break;
            case 5:
                this.quit();
                break;
        }

        return this;
    }

}