/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 1st, 2018
 */

package menu;
import questions.*;
import surveytest.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class CreateMenu extends Menu {

    protected Boolean isTest;

    public CreateMenu(Survey s, Boolean isTest) {
        super(s);

        if (isTest) {
            this.survey = new Test();
        } else {
            this.survey = new Survey();
        }

        this.menuName = "Create Menu";
        this.numChoices = 7;
        this.isTest = isTest;
        this.menuOptions.add("Add a new T/F question");
        this.menuOptions.add("Add a new multiple choice question");
        this.menuOptions.add("Add a new short answer question");
        this.menuOptions.add("Add a new essay question");
        this.menuOptions.add("Add a new ranking question");
        this.menuOptions.add("Add a new matching question");
        this.menuOptions.add("<- Go back");
    }

    // Set name for survey/test
    public void configSurvey() {
        if (isTest) {
            this.o.setDisplay("Please enter name for test: ");
        } else {
            this.o.setDisplay("Please enter name for survey ");
        }
        this.o.getDisplay();
        this.survey.setName(this.in.getUserInput());
    }

    // Get the survey/test associated with this object
    public Survey getSurvey() {
        return this.survey;
    }

    // Create a TrueFalse question
    public void createTrueFalse() {
        Question q = new TrueFalse(this.in,this.o);
        this.o.setDisplay("Enter the prompt for your True/False question:\n");
        this.o.getDisplay();

        q.setPrompt(this.in.getUserInput());

        if (isTest) {
            Test t = (Test)this.survey;
            RCA ca = new RCA(this.in,this.o);

            this.o.setDisplay("Enter the correct answer (T/F):\n");
            this.o.getDisplay();

            ca.addResponse();

            while (!ca.getResponses().get(0).contains("T") && !ca.getResponses().get(0).contains("F")) {
                ca.removeResponse(0);
                this.o.setDisplay("Enter a valid answer (T/F):\n");
                this.o.getDisplay();
                ca.addResponse();
            }

            t.addAnswer(ca);
        }
        this.survey.addQuestion(q);
    }

    // Create MultipleChoice question
    public void createMultipleChoice() {
        int numChoices = 0;
        Question q = new MultipleChoice(this.in,this.o);
        this.o.setDisplay("Enter the prompt for your Multiple Choice question:\n");
        this.o.getDisplay();

        q.setPrompt(this.in.getUserInput());

        while (numChoices == 0) {
            this.o.setDisplay("Enter the number of choices for your question:\n");
            this.o.getDisplay();

            try {
                numChoices = Integer.parseInt(this.in.getUserInput());
            } catch (NumberFormatException e) {
                numChoices = 0;
            }
        }

        for (int i=0; i<numChoices;i++) {
            this.o.setDisplay("Enter choice#" + (i+1) + "\n");
            this.o.getDisplay();

            ((MultipleChoice) q).addChoice(this.in.getUserInput());
        }

        int numAns = 0;

        while (numAns == 0) {
            this.o.setDisplay("How many answers does this question have?\n");
            this.o.getDisplay();

            try {
                numAns = Integer.parseInt(this.in.getUserInput());
                if (numAns > numChoices || numAns < 1) {
                    numAns = 0;
                } else {
                    q.setMaxResponses(numAns);
                }
            } catch (NumberFormatException e) {
                numAns = 0;
            }
        }

        if (isTest) {
            int choiceNum = 0;
            RCA ca = new RCA(this.in,this.o);
            Test t = (Test)this.survey;
            ArrayList<String> ans = new ArrayList<String>();
            char alphabet = 'A';

            for (int j=0; j < q.getMaxResponses(); j++){

                while (choiceNum == 0) {
                    this.o.setDisplay("Enter the correct choice number:\n");
                    this.o.getDisplay();

                    try {
                        choiceNum = Integer.parseInt(this.in.getUserInput());

                        if (choiceNum > numChoices || choiceNum < 1) {
                            choiceNum = 0;
                        } else {
                            alphabet += choiceNum - 1;

                            if (ans.contains(String.valueOf(alphabet))) {
                                this.o.setDisplay("Answer already used\n");
                                this.o.getDisplay();
                                choiceNum = 0;
                            } else {
                                ans.add(String.valueOf(alphabet));
                                ca.addResponse(String.valueOf(alphabet));
                                alphabet = 'A';
                            }
                        }

                    } catch (NumberFormatException e) {
                        choiceNum = 0;
                    }
                }
                choiceNum = 0;
            }
            t.addAnswer(ca);
        }
        this.survey.addQuestion(q);
    }

    // Create a ShortAnswer question
    public void createShortAnswer() {
        Question q = new ShortAnswer(this.in,this.o);
        this.o.setDisplay("Enter the prompt for your Short Answer question:\n");
        this.o.getDisplay();

        q.setPrompt(this.in.getUserInput());

        int charlimit = 0;
        while (charlimit == 0) {
            this.o.setDisplay("Enter character limit\n");
            this.o.getDisplay();

            try {
                charlimit = Integer.parseInt(this.in.getUserInput());
                if (charlimit < 1) {
                    charlimit = 0;
                } else {
                    ((ShortAnswer) q).setCharLimit(charlimit);
                }
            } catch (NumberFormatException e) {
                charlimit = 0;
            }
        }

        int numAns = 0;
        while (numAns == 0) {
            this.o.setDisplay("How many answers does this question have?\n");
            this.o.getDisplay();

            try {
                numAns = Integer.parseInt(this.in.getUserInput());
                if (numAns > numChoices || numAns < 1) {
                    numAns = 0;
                } else {
                    q.setMaxResponses(numAns);
                }
            } catch (NumberFormatException e) {
                numAns = 0;
            }
        }

        if (isTest) {
            RCA ca = new RCA(this.in,this.o);
            Test t = (Test)this.survey;
            String ans;

            for (int j=0; j < q.getMaxResponses(); j++){
                this.o.setDisplay("Enter correct answer(s):\n");
                this.o.getDisplay();

                ans = this.in.getUserInput();

                while (ans.length() > ((ShortAnswer) q).getCharLimit()) {
                    this.o.setDisplay("Answer must be less then " + ((ShortAnswer) q).getCharLimit() + " characters \n");
                    this.o.getDisplay();
                    ans = this.in.getUserInput();
                }

                ca.addResponse(ans);
            }
            t.addAnswer(ca);
        }
        this.survey.addQuestion(q);
    }

    // Create an Essay question
    public void createEssay() {
        Question q = new Essay(this.in,this.o);
        this.o.setDisplay("Enter the prompt for your Essay question:\n");
        this.o.getDisplay();

        q.setPrompt(this.in.getUserInput());

        int numAns = 0;
        while (numAns == 0) {
            this.o.setDisplay("How many answers does this question have?\n");
            this.o.getDisplay();

            try {
                numAns = Integer.parseInt(this.in.getUserInput());
                if (numAns > numChoices || numAns < 1) {
                    numAns = 0;
                } else {
                    q.setMaxResponses(numAns);
                }
            } catch (NumberFormatException e) {
                numAns = 0;
            }
        }

        if (isTest) {
            RCA ca = new RCA(this.in,this.o);
            Test t = (Test)this.survey;
            String ans;

            this.o.setDisplay("Enter correct answer(s)\n");
            this.o.getDisplay();

            for (int j=0; j < q.getMaxResponses(); j++){

                ans = this.in.getUserInput();

                ca.addResponse(ans);
            }
            t.addAnswer(ca);
        }

        this.survey.addQuestion(q);
    }

    // Create a Ranking question
    public void createRanking() {
        Question q = new Ranking(this.in,this.o);
        this.o.setDisplay("Enter the prompt for your Ranking question:\n");
        this.o.getDisplay();

        q.setPrompt(this.in.getUserInput());

        int numAns = 0;
        while (numAns == 0) {
            this.o.setDisplay("How many items need to be ranked?\n");
            this.o.getDisplay();

            try {
                numAns = Integer.parseInt(this.in.getUserInput());
                if (numAns < 1) {
                    numAns = 0;
                } else {
                    q.setMaxResponses(numAns);
                }
            } catch (NumberFormatException e) {
                numAns = 0;
            }
        }

        if (isTest) {
            RCA ca = new RCA(this.in,this.o);
            Test t = (Test)this.survey;
            String ans;

            this.o.setDisplay("Enter correct answer(s):\n");
            this.o.getDisplay();
            for (int j=0; j < q.getMaxResponses(); j++){

                ans = this.in.getUserInput();

                ca.addResponse(ans);
            }
            t.addAnswer(ca);
        }
        this.survey.addQuestion(q);
    }

    // Create a Matching question
    public void createMatching() {
        int numChoices = 0;
        Question q = new Matching(this.in,this.o);
        this.o.setDisplay("Enter the prompt for your Matching question:\n");
        this.o.getDisplay();

        q.setPrompt(this.in.getUserInput());

        while (numChoices == 0) {
            this.o.setDisplay("Enter the number of choices for your question:\n");
            this.o.getDisplay();

            try {
                numChoices = Integer.parseInt(this.in.getUserInput());
            } catch (NumberFormatException e) {
                numChoices = 0;
            }
        }

        for (int i=0; i<numChoices;i++) {
            this.o.setDisplay("Enter choice#" + (i+1) + "\n");
            this.o.getDisplay();

            ((Matching) q).addChoicesToMatch(this.in.getUserInput());
        }

        for (int i=0; i<numChoices;i++) {
            this.o.setDisplay("Answer#" + (i+1) + " (enter any answer for one of the choices)\n");
            this.o.getDisplay();

            ((Matching) q).addChoice(this.in.getUserInput());
        }

        q.setMaxResponses(numChoices);

        if (isTest) {
            int choiceNum = 0;
            RCA ca = new RCA(this.in,this.o);
            Test t = (Test)this.survey;
            ArrayList<Integer> ans = new ArrayList<Integer>();

            for (int j=0; j < q.getMaxResponses(); j++){

                while (choiceNum == 0) {
                    this.o.setDisplay("Enter answer# for choice#" + (j+1) + ": \n");
                    this.o.getDisplay();

                    try {
                        choiceNum = Integer.parseInt(this.in.getUserInput());

                        if (choiceNum > numChoices || choiceNum < 1) {
                            choiceNum = 0;
                        } else {
                            if (ans.contains(choiceNum)) {
                                this.o.setDisplay("Answer already used\n");
                                this.o.getDisplay();
                                choiceNum = 0;
                            } else {
                                ans.add(choiceNum);
                                ca.addResponse(Integer.toString(choiceNum));
                            }
                        }

                    } catch (NumberFormatException e) {
                        choiceNum = 0;
                    }
                }
                choiceNum = 0;
            }
            t.addAnswer(ca);
        }
        this.survey.addQuestion(q);
    }

    // Run CreateMenu
    @Override
    public Menu run() {

        if (this.survey.getName().contains("")) {
            this.configSurvey();
        }

        while (true) {
            int sel = 0;
            this.display();
            sel = this.getSelection();

            switch (sel) {
                case 1:
                    this.createTrueFalse();
                    break;
                case 2:
                    this.createMultipleChoice();
                    break;
                case 3:
                    this.createShortAnswer();
                    break;
                case 4:
                    this.createEssay();
                    break;
                case 5:
                    this.createRanking();
                    break;
                case 6:
                    this.createMatching();
                    break;
                case 7:
                    return this;
            }
        }
    }

}