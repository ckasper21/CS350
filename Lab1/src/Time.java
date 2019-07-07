// Chris Kasper
// CS 350 - Lab 1
// Time.java

public class Time {

    protected int hr, min, sec;
    protected String pd;

    // Constructor
    Time(int hr, int min, int sec, String pd) {
        this.hr = hr;
        this.min = min;
        this.sec = sec;
        this.pd = pd;
    }

    // Changes AM to PM or PM to AM
    public void switchPeriod() {
        if (this.pd == "AM") {
            this.pd = "PM";
        } else {
            this.pd = "AM";
        }
    }

    // This is to allow for ticking in the Clock class
    public void incrementOneSecond() {
        this.sec++;
        if (this.sec == 60) {
            this.sec = 0;
            this.min++;
            if (this.min == 60) {
                this.min = 0;
                this.hr++;
                if (this.hr == 13){
                    this.hr = 1;
                    this.switchPeriod();
                }
            }
        }
    }

    @Override
    public String toString() {
        if (this.min < 10) {
            return Integer.toString(this.hr) + ":0" + Integer.toString(this.min) + ' ' + this.pd;
        } else {
            return Integer.toString(this.hr) + ':' + Integer.toString(this.min) + ' ' + this.pd;
        }
    }

    // Returns Time in string format for comparision purposes.
    public String displayTime() {
        if (this.min < 10) {
            return Integer.toString(this.hr) + ":0" + Integer.toString(this.min) + ' ' + this.pd;
        } else {
            return Integer.toString(this.hr) + ':' + Integer.toString(this.min) + ' ' + this.pd;
        }
    }
}
