// Chris Kasper
// CS 350 - Lab 1
// Radio.java

public class Radio {

    protected Station currentRadioStation;
    protected Boolean isOn = false;
    protected int volume = 0;

    // Constructor
    Radio(Station s, int vol){
        this.currentRadioStation = s;
        this.volume = vol;
        this.turnRadioOn();
    }

    public void setRadioStation(Station s) {
        this.currentRadioStation = s;
    }

    public Station getRadioStation() {
        return this.currentRadioStation;
    }

    public void turnRadioOn() {
        this.isOn = true;
    }

    public void turnRadioOff() {
        this.isOn = false;
    }

    public Boolean isRadioOn() {
        return this.isOn;
    }

    public void setVolume(int vol) {
        this.volume = vol;
    }

    public int getVolume() {
        return this.volume;
    }
}
