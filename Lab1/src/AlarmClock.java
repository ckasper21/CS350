// Chris Kasper
// CS 350 - Lab 1
// AlarmClock.java

public class AlarmClock extends Clock{

    protected Time alarmTime;
    protected Boolean isAlarmOn;

    // Snooze will be 9 mins (expressed in seconds)
    protected int snoozeTime = 540;
    protected Boolean isSnoozing = false;

    // Constructor
    AlarmClock(Time t1, Time t2) {
        super(t1);
        this.alarmTime = t2;
    }

    public void setAlarm(Time t) {
        this.alarmTime = t;
    }

    public Time getAlarm() {
        return this.alarmTime;
    }

    public void turnAlarmOff() {
        this.isAlarmOn = false;
        this.snoozeTime = 540;
        this.isSnoozing = false;
    }

    public void turnAlarmOn() {
        this.isAlarmOn = true;
    }

    public Boolean isAlarmOn() {
        return this.isAlarmOn;
    }

    public Boolean checkAlarm() {
        if (currentTime.displayTime().equals(alarmTime.displayTime()) || (this.snoozeTime == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public void outputAlarm() {
        System.out.println("Buzz Buzz Buzz");
    }

    @Override
    public void tick() {
        this.currentTime.incrementOneSecond();
        if (this.snoozeTime > 0 && isSnoozing) {
            this.snoozeTime--;
        }
    }

    // Snooze
    public void snooze() {
        this.isSnoozing = true;
    }
}
