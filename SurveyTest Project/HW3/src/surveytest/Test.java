/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 13th, 2018
 */

package surveytest;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

    public static void main(String[] args) {
        Survey mysurvey = new Test();
        mysurvey.setName("MyTest!");

        Question q1 = new TrueFalse(mysurvey.in, mysurvey.o);
        q1.setPrompt("The Eagles are my favorite football team");

        RCA ca1 = new RCA(mysurvey.in,mysurvey.o);
        ca1.addResponse("T");

        ((Test) mysurvey).addAnswer(ca1);

        mysurvey.addQuestion(q1);

        Question q2 = new MultipleChoice(mysurvey.in, mysurvey.o);
        q2.setPrompt("Which teams do I like?");
        q2.setMaxResponses(2);
        ((MultipleChoice) q2).addChoice("Eagles");
        ((MultipleChoice) q2).addChoice("Steelers");
        ((MultipleChoice) q2).addChoice("Rams");
        ((MultipleChoice) q2).addChoice("Sixers");

        RCA ca2 = new RCA(mysurvey.in,mysurvey.o);
        ca2.addResponse("A");
        ca2.addResponse("D");

        ((Test) mysurvey).addAnswer(ca2);

        mysurvey.addQuestion(q2);

        Question q3 = new Essay (mysurvey.in,mysurvey.o);
        q3.setMaxResponses(2);
        q3.setPrompt("Give two reasons why you like CS350?");

        RCA ca3 = new RCA(mysurvey.in,mysurvey.o);
        ca3.addResponse("Free candy");
        ca3.addResponse("Cool professor");

        ((Test) mysurvey).addAnswer(ca3);

        mysurvey.addQuestion(q3);

        Question q4 = new ShortAnswer(mysurvey.in, mysurvey.o);
        q4.setPrompt("What are your two favorite Star Wars movies?");
        ((ShortAnswer) q4).setCharLimit(5);
        q4.setMaxResponses(2);

        RCA ca4 = new RCA(mysurvey.in,mysurvey.o);
        ca4.addResponse("VI");
        ca4.addResponse("V");

        ((Test) mysurvey).addAnswer(ca4);

        mysurvey.addQuestion(q4);

        Question q5 = new Ranking(mysurvey.in, mysurvey.o);
        q5.setPrompt("Rank the four major Philadelphia Sports teams from best to worst");
        q5.setMaxResponses(4);

        RCA ca5 = new RCA(mysurvey.in,mysurvey.o);
        ca5.addResponse("Eagles");
        ca5.addResponse("Sixers");
        ca5.addResponse("Flyers");
        ca5.addResponse("Phillies");

        ((Test) mysurvey).addAnswer(ca5);

        mysurvey.addQuestion(q5);

        Question q6 = new Matching(mysurvey.in,mysurvey.o);
        q6.setMaxResponses(3);
        q6.setPrompt("Match player to team");
        ((Matching) q6).addChoicesToMatch("Carson Wentz");
        ((Matching) q6).addChoicesToMatch("Ben Simmons");
        ((Matching) q6).addChoicesToMatch("Rhys Hoskins");
        ((Matching) q6).addChoice("Sixers");
        ((Matching) q6).addChoice("Eagles");
        ((Matching) q6).addChoice("Phillies");

        RCA ca6 = new RCA(mysurvey.in,mysurvey.o);
        ca6.addResponse("2");
        ca6.addResponse("1");
        ca6.addResponse("3");

        ((Test) mysurvey).addAnswer(ca6);

        mysurvey.addQuestion(q6);

        mysurvey.fillOut();

        ((Test) mysurvey).getGrade();

    }


}