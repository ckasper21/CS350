/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 13th, 2018
 */

package surveytest;
import questions.*;

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

    // Grade test
    public void getGrade() {
        float grade = 0;
        int ansIdx = 0;
        int points = 0;
        int possiblePTS = 0;

        RCA userResponse;

        for (Question q: this.getQuestions()) {
            userResponse = q.getUserResponse();
            possiblePTS += 10;

            if (userResponse == null) {
                this.o.setDisplay("Test has not been taken!\n");
                this.o.getDisplay();
                return;
            }

            if (q instanceof TrueFalse || q instanceof MultipleChoice || q instanceof ShortAnswer) {
                if (this.getAnswers().get(ansIdx).compare(userResponse, false)){
                    points += 10;
                }

            } else if (q instanceof Ranking || q instanceof Matching) {
                if (this.getAnswers().get(ansIdx).compare(userResponse, true)){
                    points += 10;
                }
            }
            ansIdx++;
        }

        this.o.setDisplay("\n#######################\n Grade for " + this.getName() + "\n#######################\n");
        this.o.getDisplay();

        this.o.setDisplay(points + "/" + possiblePTS + "\n");
        this.o.getDisplay();
    }
}