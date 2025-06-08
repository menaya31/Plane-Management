import java.util.Scanner;

public class Person {
    private  static String name;
    private static String surname;
    private static String email;

    // Constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Static method to get details from user input
    public static Person getDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your name:");
        String name = input.nextLine();

        System.out.println("Enter your surname:");
        String surname = input.nextLine();

        System.out.println("Enter your email address:");
        String email = input.nextLine();

        return new Person(name, surname, email);
    }

    // Getters
    public static String getName() {
        return name;
    }

    public static String getSurname() {
        return surname;
    }

    public static String getEmail() {
        return email;
    }

    // Method to print information of the person
    public static void printPersonInfo() {
        System.out.println("Person information:");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
    }
}
