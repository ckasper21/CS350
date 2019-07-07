/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 7th, 2018
 */

package menu;
import surveytest.*;

import java.io.Serializable;
import java.util.*;

public class TestMenu extends SurveyMenu implements Serializable {

    public TestMenu(Survey s) {
        super(s);
        this.numChoices = 9;
        this.menuName = "Test Menu";
        this.menuOptions.clear();
        this.menuOptions.add("Create a new Test");
        this.menuOptions.add("Display a Test");
        this.menuOptions.add("Load a Test");
        this.menuOptions.add("Save a Test");
        this.menuOptions.add("Modify Test");
        this.menuOptions.add("Take a Test");
        this.menuOptions.add("Tabulate a Test");
        this.menuOptions.add("Grade a Test");
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

    // Modify test
    public void modifySurvey() {
        if (this.survey == null) {
            this.o.setDisplay("No test loaded - please first load in the test you want to modify\n");
            this.o.getDisplay();
        } else {
            SurveyModifier sm = new SurveyModifier(this.survey, true);
            sm.modify();
            this.survey = sm.getSurvey();
            this.saveSurvey();
        }
    }

    // Grade a Test
    public void gradeTest() {
        if (this.survey == null) {
            this.o.setDisplay("No test loaded - please first load in the test you want to grade\n");
            this.o.getDisplay();
        } else {
            ((Test) this.survey).getGrade();
        }
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
                this.modifySurvey();
                break;
            case 6:
                this.takeSurvey();
                break;
            case 7:
                this.tabulateSurvey();
                break;
            case 8:
                this.gradeTest();
                break;
            case 9:
                this.quit();
                break;
        }

        return this;
    }

}