/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 1st, 2018
 */

package menu;
import surveytest.*;

import java.io.Serializable;
import java.util.*;

public class SurveyMenu extends Menu implements Serializable {

    public SurveyMenu(Survey s) {
        super(s);
        this.numChoices = 5;
        this.menuName = "Survey Menu";
        this.menuOptions.add("Create a new Survey");
        this.menuOptions.add("Display a Survey");
        this.menuOptions.add("Load a Survey");
        this.menuOptions.add("Save a Survey");
        this.menuOptions.add("Quit");
    }

    // Create survey, delegates to CreateMenu object
    public void createSurvey() {
        CreateMenu cm = new CreateMenu(this.survey, false);
        cm.run();
        this.survey = cm.getSurvey();
    }

    // Load survey, delegates to LoadMenu object
    public void loadSurvey() {
        LoadMenu lm = new LoadMenu(this.survey,false);
        lm.run();
        this.survey = lm.getSurvey();
    }

    // Quit from program
    public void quit() {
        System.exit(0);
    }

    // Run SurveyMenu
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