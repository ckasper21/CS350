// Chris Kasper
// CS 350 - Lab 1
// main.java - Testing AlarmClockRadio Class

public class main {

    public static void main(String args[]) {

        int i, seconds;
        Station s = new Station("97.5 The Fanatic");
        AlarmClockRadio myClock = new AlarmClockRadio(8,0,0,"AM",8,5,"AM",s);
        myClock.turnAlarmOn();
        myClock.turnRadioOff();
        myClock.setVolume(5);

        System.out.println("Time: " + myClock.getCurrentTime());
        System.out.println("Alarm will go off at " + myClock.getAlarm());
        System.out.println("Radio is currently off");

        for (i = 0; i < 5; i++) {
            for (seconds = 0; seconds < 60; seconds++){
                myClock.tick();
            }

            System.out.println("Time: " + myClock.getCurrentTime());

            if (myClock.isAlarmOn()){
                if (myClock.checkAlarm()) {
                    myClock.outputAlarm();
                }
            }
        }

        myClock.snooze();
        myClock.setVolume(9);
        myClock.wakeByRadioOn();
        System.out.println("Snoozing for 9 minutes and turning wake by radio on!");

        for (i = 0; i < 9; i++) {
            for (seconds = 0; seconds < 60; seconds++){
                myClock.tick();
            }

            System.out.println("Time: " + myClock.getCurrentTime());

            if (myClock.isAlarmOn()){
                if (myClock.checkAlarm()) {
                    myClock.outputAlarm();
                }
            }
        }

        myClock.turnAlarmOff();
        myClock.turnRadioOff();

        if (!myClock.isRadioOn() && !myClock.isAlarmOn()) {
            System.out.println("Radio and alarm turned off");
        }

    }
}
