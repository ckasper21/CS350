/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: October 31st, 2018
 */


package surveytest;
import questions.*;
import io.*;

import java.io.Serializable;
import java.util.*;

public class Test extends Survey implements Serializable {

    protected ArrayList<RCA> answers;

    public Test() {
        super();
        this.answers = new ArrayList<RCA>();
    }

    // Add answer to test
    public void addAnswer(RCA r) {
        this.answers.add(r);
    }

    // Get answer for a specific question
    public RCA getAnswer(int i) {
        return this.answers.get(i);
    }

    // Remove answer for a specific question
    public void removeAnswer(int i) {
        this.answers.remove(i);
    }

    // Edit answer for a specific question
    public void editAnswer(int i) {
        // TODO implement here
    }

    // Get all the answers associated with this test
    public ArrayList<RCA> getAnswers() {
        return this.answers;
    }

    // Display questions with answers
    public void display() {
        this.o.setDisplay("\n####################\n" + this.getName() +"\n####################\n");
        this.o.getDisplay();

        for (int i=0;i<this.numQuestions;i++) {
            Question q = this.questions.get(i);
            ArrayList<String> choices = q.getChoices();

            ArrayList<String> ans = new ArrayList<String>();

            try {
                ans = this.getAnswer(i).getResponses();
            } catch (IndexOutOfBoundsException e) {

            }

            String stringAns = "";
            String checkAns = "";
            char alphabet = 'A';
            q.display();

            if (choices.isEmpty()) {
                if (ans.isEmpty()){
                    this.o.setDisplay("No answer(s) for this question\n\n");
                    this.o.getDisplay();
                } else {
                    for (String a: ans) {
                        stringAns += "\n" + a + " ";
                    }
                    this.o.setDisplay("Correct answer(s): " + stringAns + "\n\n");
                    this.o.getDisplay();
                }

            } else {
                for (int j=0; j < choices.size(); j++ ) {
                    checkAns = (char) (alphabet + j) + "";
                    String checkNumAns = Integer.toString(j+1);
                    if (ans.contains(checkAns) ) {
                        stringAns += checkAns + ") " + choices.get(j) + "  ";
                    } else if (ans.contains(checkNumAns)){
                        stringAns += checkAns + ") " + ans.get(j) + "  ";
                    }
                }
                this.o.setDisplay("Correct answer(s): " + stringAns + "\n\n");
                this.o.getDisplay();
            }
        }
    }

    // TODO: Grade a test
    public float grade() {
        return 1;
    }
}