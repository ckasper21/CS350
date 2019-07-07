// Chris Kasper
// CS 350 - Lab 1
// Clock.java

public class Clock {

    protected Time currentTime;

    // Constructor
    Clock(Time t) {
        currentTime = t;
    }

    public Time getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(Time t) {
        this.currentTime = t;
    }

    public void tick() {
        this.currentTime.incrementOneSecond();
    }
}
