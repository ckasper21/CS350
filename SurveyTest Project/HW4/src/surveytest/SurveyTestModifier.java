/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 27th, 2018
 */

package surveytest;
import questions.*;
import io.*;
import java.util.*;

public class SurveyTestModifier {
    protected Survey s;
    protected Boolean isTest;
    protected Input in;
    protected Output o;

    public SurveyTestModifier(Survey s, Boolean isTest) {
        this.s = s;
        this.isTest = isTest;
        this.in = new ConsoleInput();
        this.o = new ConsoleOutput();
    }

    // Get survey associated with this object
    public Survey getSurvey() {
        return this.s;
    }

    // Modify the survey/test
    public void modify() {
        // First figure out which question to change
        ArrayList<Question> questions = this.s.getQuestions();
        Question modQ;
        int userSel = 0;

        if (questions.isEmpty()) {
            this.o.setDisplay("No questions in this survey\n");
            this.o.getDisplay();
            return;
        }

        // Prompt user
        this.o.setDisplay("Which question do you want to modify? Input the question #\n");
        this.o.getDisplay();

        int idx = 1;
        for (Question q: questions) {
            this.o.setDisplay("#" + idx + "\t");
            this.o.getDisplay();
            q.display();
            idx++;
            this.o.setDisplay("\n");
            this.o.getDisplay();
        }

        // Get appropriate user input
        while (userSel == 0 ) {
            try {
                userSel = Integer.parseInt(this.in.getUserInput());

                if (userSel > this.s.numQuestions || userSel < 1) {
                    userSel = 0;
                }
            } catch (NumberFormatException e) {
                userSel = 0;
            }
        }

        modQ = questions.get(userSel-1);

        this.modifyQuestion(modQ, userSel-1);
    }

    // Modify question
    public void modifyQuestion(Question q, int qIdx) {
        String choice;

        this.o.setDisplay(q.getPrompt() + "\n\nDo you wish to modify the prompt? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay(q.getPrompt() + "\n\nEnter a new prompt\n");
            this.o.getDisplay();
            q.setPrompt(this.in.getUserInput());
        }

        if (q instanceof TrueFalse) {
            this.modifyTrueFalse(q, qIdx);
        } else if (q instanceof MultipleChoice) {
            this.modifyMultipleChoice(q, qIdx);
        } else if (q instanceof ShortAnswer) {
            this.modifyShortAnswer(q, qIdx);
        } else if (q instanceof Essay) {
            this.modifyEssay(q, qIdx);
        } else if (q instanceof Ranking) {
            this.modifyRanking(q, qIdx);
        } else if (q instanceof Matching) {
            this.modifyMatching(q, qIdx);
        }
    }

    // Modify TrueFalse question
    public void modifyTrueFalse(Question q, int qIdx) {
        if (this.isTest) {
            this.o.setDisplay("Do you want to modify the answer? (Y/N) \n");
            this.o.getDisplay();

            String choice = this.in.getUserInput();

            while (!choice.equals("Y") && !choice.equals("N")) {
                choice =this.in.getUserInput();
            }

            if (choice.equals("Y")) {
                ArrayList<String> ansChoices = ((TrueFalse)q).getChoices();
                this.o.setDisplay("Do you want to answer to be True or False? (T/F) \n");
                this.o.getDisplay();

                choice = this.in.getUserInput();
                while (!choice.equals("T") && !choice.equals("F")) {
                    choice =this.in.getUserInput();
                }
                RCA ans = ((Test) this.s).getAnswer(qIdx);
                ans.removeResponse(0);
                ans.addResponse(choice);
            }

        }
    }

    // Modify MultipleChoice question
    public void modifyMultipleChoice(Question q, int qIdx) {
        String choice;

        ArrayList<String> ansChoices = q.getChoices();
        char alphabet = 'A';
        ArrayList<String> options = new ArrayList<String>();

        String strAns = "";

        for (String s : ansChoices) {
            options.add(Character.toString(alphabet));
            strAns += (alphabet) + ") " + s + "  ";
            alphabet++;
        }
        strAns += "\n";

        this.o.setDisplay("Do you wish to change the number of responses to the question? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay("Enter in the number of responses. \n");
            this.o.getDisplay();

            int numAns = 0;
            while (numAns == 0) {
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
        }

        this.o.setDisplay("Do you wish to modify choices? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay("Which choice do you want to modify? \n" + strAns);
            this.o.getDisplay();

            while (true) {
                choice = this.in.getUserInput();
                if (options.contains(choice)) {
                    break;
                }
            }

            int choiceIdx = options.indexOf(choice);

            this.o.setDisplay("Enter a new value\n");
            this.o.getDisplay();

            ansChoices.set(choiceIdx, this.in.getUserInput());
        }

        if (this.isTest) {
            this.o.setDisplay("Do you want to modify the answer? (Y/N) \n");
            this.o.getDisplay();

            choice = this.in.getUserInput();

            while (!choice.equals("Y") && !choice.equals("N")) {
                choice =this.in.getUserInput();
            }

            if (choice.equals("Y")) {
                RCA ans = ((Test)this.s).getAnswer(qIdx);
                ArrayList<String> Ans = ans.getResponses();
                Ans.clear();
                String newAns;

                this.o.setDisplay("Please enter the correct answer. There should be " + q.getMaxResponses() + " answer(s)\n");
                this.o.getDisplay();

                for (int i = 0; i < q.getMaxResponses(); i++) {
                    newAns = this.in.getUserInput();
                    while (!options.contains(newAns)) {
                        newAns =this.in.getUserInput();
                    }
                    Ans.add(newAns);
                }
            }
        }
    }

    // Modify ShortAnswer question
    public void modifyShortAnswer(Question q, int qIdx) {
        this.o.setDisplay("Do you wish to change the character limit? (Y/N)\n");
        this.o.getDisplay();

        String choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay("Enter a new character limit? \n");
            this.o.getDisplay();
            int charlimit = 0;
            while (charlimit == 0) {
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
        }

        this.o.setDisplay("Do you wish to change the number of responses to the question? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay("Enter in the number of responses. \n");
            this.o.getDisplay();

            int numAns = 0;
            while (numAns == 0) {
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
        }

        if (this.isTest) {
            this.o.setDisplay("Do you want to modify the answer(s)? (Y/N) \n");
            this.o.getDisplay();

            choice = this.in.getUserInput();

            while (!choice.equals("Y") && !choice.equals("N")) {
                choice =this.in.getUserInput();
            }

            if (choice.equals("Y")) {
                RCA ans = ((Test)this.s).getAnswer(qIdx);
                ArrayList<String> strAns = ans.getResponses();
                strAns.clear();
                String newAns;

                this.o.setDisplay("Please enter the correct answer(s). There should be " + q.getMaxResponses() + "\n");
                this.o.getDisplay();

                for (int i = 0; i < q.getMaxResponses(); i++) {
                    newAns = this.in.getUserInput();
                    while (newAns.length() > ((ShortAnswer)q).getCharLimit()) {
                        newAns =this.in.getUserInput();
                    }
                    strAns.add(newAns);
                }
            }
        }
    }

    // Modify Essay question
    public void modifyEssay(Question q, int qIdx) {
        String choice;

        this.o.setDisplay("Do you wish to change the number of responses to the question? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay("Enter in the number of responses. \n");
            this.o.getDisplay();

            int numAns = 0;
            while (numAns == 0) {
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
        }

        if (this.isTest) {
            this.o.setDisplay("Do you want to modify the answer(s)? (Y/N) \n");
            this.o.getDisplay();

            choice = this.in.getUserInput();

            while (!choice.equals("Y") && !choice.equals("N")) {
                choice =this.in.getUserInput();
            }

            if (choice.equals("Y")) {
                RCA ans = ((Test)this.s).getAnswer(qIdx);
                ArrayList<String> strAns = ans.getResponses();
                strAns.clear();
                String newAns;

                this.o.setDisplay("Please enter the correct answer(s). There should be " + q.getMaxResponses() + "\n");
                this.o.getDisplay();

                for (int i = 0; i < q.getMaxResponses(); i++) {
                    newAns = this.in.getUserInput();
                    strAns.add(newAns);
                }
            }
        }
    }

    //Modify Ranking question
    public void modifyRanking(Question q, int qIdx) {
        String choice;

        this.o.setDisplay("Do you wish to change the number of items to rank in the question? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay("Enter in the number of responses. \n");
            this.o.getDisplay();

            int numAns = 0;
            while (numAns == 0) {
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
        }

        if (this.isTest) {
            this.o.setDisplay("Do you want to modify the answer? (Y/N) \n");
            this.o.getDisplay();

            choice = this.in.getUserInput();

            while (!choice.equals("Y") && !choice.equals("N")) {
                choice =this.in.getUserInput();
            }

            if (choice.equals("Y")) {
                RCA ans = ((Test)this.s).getAnswer(qIdx);
                ArrayList<String> strAns = ans.getResponses();
                strAns.clear();
                String newAns;

                this.o.setDisplay("Please enter the correct answer. There should be " + q.getMaxResponses() + " items to rank\n");
                this.o.getDisplay();

                for (int i = 0; i < q.getMaxResponses(); i++) {
                    newAns = this.in.getUserInput();
                    strAns.add(newAns);
                }
            }
        }
    }

    // Modify Matching question
    public void modifyMatching(Question q, int qIdx) {
        String choice;

        ArrayList<String> choicesToMatch = ((Matching)q).getChoicesToMatch();
        ArrayList<String> ansChoices = q.getChoices();

        char alphabet = 'A';
        ArrayList<String> choiceOptions = new ArrayList<String>();

        String strChoicesToMatch = "";
        String strAnsChoices = "";

        for (String s : choicesToMatch) {
            choiceOptions.add(Character.toString(alphabet));
            strChoicesToMatch += (alphabet) + ") " + s + "  ";
            alphabet++;
        }
        strChoicesToMatch += "\n";

        this.o.setDisplay("Do you wish to modify the choices to match (left column) in the question? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            this.o.setDisplay("Enter letter of choice to match\n" + strChoicesToMatch);
            this.o.getDisplay();
            choice = "";

            while (!choiceOptions.contains(choice)) {
                choice =this.in.getUserInput();
            }
            this.o.setDisplay("Enter new value\n");
            this.o.getDisplay();
            choicesToMatch.set(choiceOptions.indexOf(choice),this.in.getUserInput());
        }

        this.o.setDisplay("Do you wish to modify the choices (right column) in the question? (Y/N)\n");
        this.o.getDisplay();

        choice = this.in.getUserInput();

        while (!choice.equals("Y") && !choice.equals("N")) {
            choice =this.in.getUserInput();
        }

        if (choice.equals("Y")) {
            int ansIdx = 0;
            for (String s : ansChoices) {
                strAnsChoices += (ansIdx + 1) + ") " + s + "  ";
                ansIdx++;
            }
            strAnsChoices += "\n";

            this.o.setDisplay("Enter number of choice\n" + strAnsChoices);
            this.o.getDisplay();

            int numAns = 0;
            while (numAns == 0) {
                try {
                    numAns = Integer.parseInt(this.in.getUserInput());
                    if (numAns < 1 || numAns > q.getMaxResponses()) {
                        numAns = 0;
                    }
                } catch (NumberFormatException e) {
                    numAns = 0;
                }
            }

            this.o.setDisplay("Enter new value\n");
            this.o.getDisplay();

            ansChoices.set(numAns-1,this.in.getUserInput());
        }

        if (this.isTest) {
            this.o.setDisplay("Do you want to modify the answer? (Y/N) \n");
            this.o.getDisplay();

            choice = this.in.getUserInput();

            while (!choice.equals("Y") && !choice.equals("N")) {
                choice =this.in.getUserInput();
            }

            if (choice.equals("Y")) {
                RCA ans = ((Test)this.s).getAnswer(qIdx);
                ArrayList<String> Ans = ans.getResponses();
                Ans.clear();
                int newAns;
                alphabet = 'A';

                q.display();

                this.o.setDisplay("Please enter the correct answer\n");
                this.o.getDisplay();

                for (int i = 0; i < q.getMaxResponses(); i++) {
                    this.o.setDisplay(alphabet + " ");
                    this.o.getDisplay();

                    newAns = 0;
                    while (newAns == 0) {
                        try {
                            newAns = Integer.parseInt(this.in.getUserInput());
                            if (newAns < 1 || newAns > q.getMaxResponses()) {
                                newAns = 0;
                            }
                        } catch (NumberFormatException e) {
                            newAns = 0;
                        }
                    }
                    Ans.add(Integer.toString(newAns));
                    alphabet++;
                }
            }
        }

    }

}
