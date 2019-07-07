// Chris Kasper
// CS 350 - Lab 1
// AlarmClockRadio.java

public class AlarmClockRadio {

    private Radio radio;
    private AlarmClock alarmclock;
    private Boolean wakeByRadio;

    // Constructor
    AlarmClockRadio(int hr, int min, int sec, String pd, int hr_alarm, int min_alarm, String pd_alarm, Station s){
        Time t1 = new Time(hr,min,sec,pd);
        Time t2 = new Time(hr_alarm,min_alarm,0,pd_alarm);
        this.alarmclock = new AlarmClock(t1,t2);
        this.radio = new Radio(s,5);
        this.wakeByRadio = false;
    }

    public void setRadioStation(Station s) {
        this.radio.setRadioStation(s);
    }

    public Station getRadioStation() {
        return this.radio.getRadioStation();
    }

    public Time getCurrentTime() {
        return this.alarmclock.getCurrentTime();
    }

    public void setCurrentTime(Time t) {
        this.alarmclock.setCurrentTime(t);
    }

    public void setAlarm(Time t) {
        this.alarmclock.setAlarm(t);
    }

    public Time getAlarm() {
        return this.alarmclock.getAlarm();
    }

    public void turnAlarmOn() {
        this.alarmclock.turnAlarmOn();
    }

    public void turnAlarmOff() {
        this.alarmclock.turnAlarmOff();
    }

    public Boolean isAlarmOn() {
        return this.alarmclock.isAlarmOn();
    }

    public Boolean checkAlarm() {
        return this.alarmclock.checkAlarm();
    }

    public void outputAlarm() {
        if (this.wakeByRadio) {
            this.turnRadioOn();
            System.out.println("Radio Alarm: Playing " + this.getRadioStation());
        } else {
            this.alarmclock.outputAlarm();
        }
    }

    public void setVolume(int vol) {
        this.radio.setVolume(vol);
    }

    public int getVolume() {
        return this.radio.getVolume();
    }

    public void tick() {
        this.alarmclock.tick();
    }

    public void turnRadioOn() {
        this.radio.turnRadioOn();
    }

    public void turnRadioOff() {
        this.radio.turnRadioOff();
    }

    public Boolean isRadioOn() {
        return radio.isOn;
    }

    public void snooze() {
        this.alarmclock.snooze();
    }

    public void wakeByRadioOn() {
        this.wakeByRadio = true;
    }

    public void wakeByRadioOff() {
        this.wakeByRadio = false;
    }

    public Boolean getWakeByRadio() {
        return this.wakeByRadio;
    }
}
