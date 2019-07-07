// Chris Kasper
// CS 350 - Lab 1
// Station.java

public class Station {
    private String name;

    // Constructor
    Station(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
