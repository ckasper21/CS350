/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 7th, 2018
 */

package menu;
import surveytest.*;

import java.io.*;

public class SurveyMenu extends Menu implements Serializable {

    public SurveyMenu(Survey s) {
        super(s);
        this.numChoices = 8;
        this.menuName = "Survey Menu";
        this.menuOptions.add("Create a new Survey");
        this.menuOptions.add("Display a Survey");
        this.menuOptions.add("Load a Survey");
        this.menuOptions.add("Save a Survey");
        this.menuOptions.add("Modify Survey");
        this.menuOptions.add("Take a Survey");
        this.menuOptions.add("Tabulate a Survey");
        this.menuOptions.add("Quit");
    }

    // Create survey, delegates to CreateMenu object
    public void createSurvey() {
        CreateMenu cm = new CreateMenu(this.survey, false);
        cm.run();
        this.survey = cm.getSurvey();
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

    // Load survey, delegates to LoadMenu object
    public void loadSurvey() {
        LoadMenu lm = new LoadMenu(this.survey,false);
        lm.run();
        this.survey = lm.getSurvey();
    }

    // Save survey
    public void saveSurvey() {
        if (this.survey == null) {
            this.o.setDisplay("No survey/test loaded!\n");
            this.o.getDisplay();
            return;
        }

        String dir = "./" + this.menuName.toLowerCase().split(" ")[0] + "/";
        String fileName = dir + this.survey.getName() + ".ser";

        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this.survey);

            out.close();
            file.close();


            this.o.setDisplay("Survey/test saved!\n");
            this.o.getDisplay();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Modify survey
    public void modifySurvey() {
        if (this.survey == null) {
            this.o.setDisplay("No survey loaded - please first load in the survey you want to modify\n");
            this.o.getDisplay();
        } else {
            SurveyTestModifier sm = new SurveyTestModifier(this.survey, false);
            sm.modify();
            this.survey = sm.getSurvey();
            this.saveSurvey();
        }
    }

    // Take a Survey
    public void takeSurvey() {
        if (this.survey == null) {
            this.o.setDisplay("No survey loaded - please first load in the survey/test you want to take\n");
            this.o.getDisplay();
        } else {
            this.survey.fillOut();
            this.saveSurvey();
        }
    }

    // Tabulate a Survey
    public void tabulateSurvey() {
        if (this.survey == null) {
            this.o.setDisplay("No survey loaded - please first load in the survey/test you want to tabulate\n");
            this.o.getDisplay();
        } else {
            this.survey.tabulate();
        }
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
                this.modifySurvey();
                break;
            case 6:
                this.takeSurvey();
                break;
            case 7:
                this.tabulateSurvey();
                break;
            case 8:
                this.quit();
                break;
        }

        return this;
    }

}