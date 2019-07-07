/**
 * Chris Kasper
 * CS350 - Survey Test Project
 * Last updated: November 13th, 2018
 */

package surveytest;

import io.*;
import questions.*;

import java.io.*;
import java.util.*;

public class Survey implements Serializable {

    protected String name;
    protected ArrayList<Question> questions;
    protected int numQuestions;
    protected Input in;
    protected Output o;

    public Survey() {
        this.name = "";
        this.questions = new ArrayList<Question>();
        this.numQuestions = 0;
        this.in = new Input();
        this.o = new Output();
    }

    // Set name for survey
    public void setName(String s) {
        this.name = s;
    }

    // Get name for survey
    public String getName() {
        return this.name;
    }

    // Add question to survey, increment number of questions
    public void addQuestion(Question q) {
        this.questions.add(q);
        this.numQuestions++;
    }

    // Remove a specific question
    public void removeQuestion(int i) {
        this.questions.remove(i);
        this.numQuestions--;
    }

    // Get list of questions associated with this survey
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    // Display survey (just questions)
    public void display() {
        this.o.setDisplay("\n#######################\n" + this.getName() +"\n#######################\n");
        this.o.getDisplay();
        for (Question q: this.questions) {
            q.display();
            this.o.setDisplay("\n");
            this.o.getDisplay();
        }
    }

    // Take survey/test
    public void fillOut() {
        this.o.setDisplay("\n#######################\n Filling out " + this.getName() + "\n#######################\n");
        this.o.getDisplay();

        for (Question q: this.getQuestions()) {
            q.display();
            q.answerQuestion();
            this.o.setDisplay("\n");
            this.o.getDisplay();
        }
    }

    // Tabulate survey/test
    public void tabulate() {
        this.o.setDisplay("\n#######################\n Tabulation \n#######################\n");
        this.o.getDisplay();

        ArrayList<RCA> allResponses;

        // Iterate over questions, check what type of question, tabulate accordingly
        for (Question q: this.getQuestions()) {
            allResponses = q.getResponses();
            if (allResponses.isEmpty()) {
                this.o.setDisplay("Survey/Test not filled out completely.\n");
                this.o.getDisplay();
                return;
            }
            if (q instanceof TrueFalse) {
                int numTrue = 0;
                int numFalse = 0;

                for (RCA r: allResponses) {
                    if (r.getResponses().get(0).equals("T")) {
                        numTrue++;
                    } else if (r.getResponses().get(0).equals("F")) {
                        numFalse++;
                    }
                }
                q.display();
                this.o.setDisplay("True: " + numTrue + "\nFalse: " + numFalse + "\n");
                this.o.getDisplay();
            } else if (q instanceof MultipleChoice || q instanceof ShortAnswer) {
                HashMap<String,Integer> res = new HashMap<>();

                for (RCA r: allResponses) {
                    for (String s: r.getResponses()) {
                        if (res.containsKey(s)) {
                           res.put(s,res.get(s) + 1);
                        } else {
                            res.put(s,1);
                        }
                    }
                }

                q.display();

                for (String s: res.keySet()) {
                    this.o.setDisplay(s + ": " + res.get(s) + "\n");
                    this.o.getDisplay();
                }

            } else if (q instanceof Essay) {
                q.display();

                for (RCA r: allResponses) {
                    for (String s: r.getResponses()){
                        this.o.setDisplay(s + "\n");
                        this.o.getDisplay();
                    }
                    this.o.setDisplay("-----------\n");
                    this.o.getDisplay();
                }

            } else if (q instanceof Ranking) {
                HashMap<String,Integer> res = new HashMap<>();

                for (RCA r: allResponses) {
                    // Construct key (keep track of permutations)
                    String key = "";
                    for (String s: r.getResponses()) {
                        key += s + "-";
                    }
                    if (res.containsKey(key)) {
                        res.put(key,res.get(key) + 1);
                    } else {
                        res.put(key,1);
                    }
                }

                q.display();

                for (String s: res.keySet()) {
                    this.o.setDisplay(res.get(s) + "\n");
                    this.o.getDisplay();

                    for (String ss: s.split("-")) {
                        this.o.setDisplay(ss +"\n");
                        this.o.getDisplay();
                    }
                    this.o.setDisplay("\n");
                    this.o.getDisplay();
                }

            } else if (q instanceof Matching) {
                HashMap<String,Integer> res = new HashMap<>();

                for (RCA r: allResponses) {
                    // Construct key (keep track of permutations)
                    String key = "";
                    for (String s: r.getResponses()) {
                        key += s + "-";
                    }
                    if (res.containsKey(key)) {
                        res.put(key,res.get(key) + 1);
                    } else {
                        res.put(key,1);
                    }
                }

                q.display();

                for (String s: res.keySet()) {
                    char alphabet = 'A';
                    this.o.setDisplay(res.get(s) + "\n");
                    this.o.getDisplay();

                    for (String ss: s.split("-")) {
                        this.o.setDisplay(alphabet + " " + ss + "\n");
                        this.o.getDisplay();
                        alphabet++;
                    }
                    this.o.setDisplay("\n");
                    this.o.getDisplay();
                }
            }

            this.o.setDisplay("\n");
            this.o.getDisplay();
        }
    }

    public static void main(String[] args) {
        Survey mysurvey = new Survey();
        mysurvey.setName("MySurvey!");

        Question q1 = new TrueFalse(mysurvey.in, mysurvey.o);
        q1.setPrompt("The Eagles are my favorite football team");

        mysurvey.addQuestion(q1);

//        Question q2 = new MultipleChoice(mysurvey.in, mysurvey.o);
//        q2.setPrompt("Which teams do I like?");
//        q2.setMaxResponses(2);
//        ((MultipleChoice) q2).addChoice("Eagles");
//        ((MultipleChoice) q2).addChoice("Steelers");
//        ((MultipleChoice) q2).addChoice("Rams");
//        ((MultipleChoice) q2).addChoice("Sixers");
//
//        mysurvey.addQuestion(q2);
//
//        Question q3 = new Essay (mysurvey.in,mysurvey.o);
//        q3.setMaxResponses(2);
//        q3.setPrompt("Give two reasons why you like CS350?");
//
//        mysurvey.addQuestion(q3);

//        Question q4 = new ShortAnswer(mysurvey.in, mysurvey.o);
//        q4.setPrompt("What are your two favorite Star Wars movie?");
//        ((ShortAnswer) q4).setCharLimit(240);
//        q4.setMaxResponses(2);
//
//        mysurvey.addQuestion(q4);

//        Question q5 = new Ranking(mysurvey.in, mysurvey.o);
//        q5.setPrompt("Rank your favorite Star Wars movies");
//        q5.setMaxResponses(3);
//
//        mysurvey.addQuestion(q5);

        Question q6 = new Matching(mysurvey.in,mysurvey.o);
        q6.setMaxResponses(3);
        q6.setPrompt("Match player to team");
        ((Matching) q6).addChoicesToMatch("Carson Wentz");
        ((Matching) q6).addChoicesToMatch("Ben Simmons");
        ((Matching) q6).addChoicesToMatch("Rhys Hoskins");
        ((Matching) q6).addChoice("Sixers");
        ((Matching) q6).addChoice("Eagles");
        ((Matching) q6).addChoice("Phillies");

        mysurvey.addQuestion(q6);

        mysurvey.fillOut();
        mysurvey.fillOut();
        mysurvey.fillOut();

        mysurvey.tabulate();
        System.out.println("Done!");

    }

}