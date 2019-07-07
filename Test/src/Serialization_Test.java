/* Chris Kasper
 * Serialization test
 */

import java.io.*;

public class Serialization_Test {

    static public void main( String[] argv ){
        Person A = new Person("Chris", "blue");
        Person B = new Person("John", "red");

        System.out.println(A.name + " likes " + A.color);
        System.out.println(B.name + " likes " + B.color);
        A.setColor("black");

        String filename = "person_objects.ser";

        System.out.println("\nSerialization...");
        // Serialization
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(A);
            out.writeObject(B);

            out.close();
            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        A = null;
        B = null;

        System.out.println("Deserialization...\n");
        // Deserialization
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            A = (Person) in.readObject();
            B = (Person) in.readObject();

            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(A.name + " likes " + A.color);
        System.out.println(B.name + " likes " + B.color);
    }

}