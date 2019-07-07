/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 4th, 2018
 */

package menu;
import surveytest.*;

import java.io.*;
import java.util.*;

public class LoadMenu extends Menu implements Serializable {
    protected Boolean isTest;
    protected Boolean noFiles;

    public LoadMenu(Survey s, Boolean isTest) {
        super(s);
        this.menuName = "Load Menu";
        this.isTest = isTest;
        this.noFiles = this.getFiles();
    }

    // Get files in test/survey directories
    // Files saved with ".testser" or ".surveyser"
    public Boolean getFiles() {
        String dir = "";

        if (this.isTest) {
            dir += "./test";
        } else {
            dir += "./survey";
        }

        File folder = new File(dir);
        String[] files = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return name.matches(".*ser");
            }
        });

        if (files.length == 0) {
            this.o.setDisplay("No files to be loaded\n");
            this.o.getDisplay();
            return true;
        }

        for (String file : files)
        {
            this.menuOptions.add(file);
            this.numChoices++;
        }
        return false;
    }

    // Load survey/test
    public void loadSurvey(int i) {
        String dir = "";

        if (this.isTest) {
            dir += "./test/";
        } else {
            dir += "./survey/";
        }

        String fileName = dir + this.menuOptions.get(i-1);

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            this.survey = (Survey) in.readObject();

            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // Get survey/test with this object
    public Survey getSurvey() {
        return this.survey;
    }

    // Run the LoadMenu
    @Override
    public Menu run() {
        int sel = 0;

        if (this.noFiles) {
            return this;
        } else {
            this.display();
            sel = this.getSelection();
            this.loadSurvey(sel);
        }
        return this;
    }

}