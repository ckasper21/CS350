public class Person implements java.io.Serializable {
    public String name;
    public String color;

    // Constructor
    public Person(String my_name, String favorite_color) {
        name = my_name;
        color = favorite_color;
    }

    // Method Set Age
    public void setColor(String new_color){
        color = new_color;
    }
}
