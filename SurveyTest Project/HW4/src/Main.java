/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 1st, 2018
 */

import surveytest.*;
import menu.*;

public class Main {

    public static void main(String[] args) {
        Survey survey = null;
        Menu menu = new MainMenu(survey);
        int sel;

        while (true) {
            menu = menu.run();
        }

    }

}