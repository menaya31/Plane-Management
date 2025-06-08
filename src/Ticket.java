import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private static  String row_letter;
    private static int seat_no;

    public Ticket(String row_letter, int seat_no) {
        Ticket.row_letter = row_letter;
        Ticket.seat_no = seat_no;
    }

    public String getRow() {
        return row_letter;
    }

    public int getSeatNo() {
        return seat_no;
    }

    public static int calculateSeatPrice() {
        if (seat_no >= 1 && seat_no <= 5) {
            return 200;
        } else if (seat_no >= 6 && seat_no <= 9) {
            return 150;
        } else {
            return 180;
        }
    }

    public static void print_tickets_info() {
        System.out.println("Ticket information:");
        int total = 0;
        for (int i = 0; i < w2053262_PlaneManagement.tickets.length; i++) {
            Ticket ticket = w2053262_PlaneManagement.tickets[i];
            if (ticket != null) {
                int price =ticket.calculateSeatPrice();
                total += price;
                System.out.println("Row    : " + ticket.getRow());
                System.out.println("Seat   : " + ticket.getSeatNo());
                System.out.println("Price  : £" + price);
                System.out.println();
            }
        }
        System.out.println("Total Amount: £" + total);
    }
    //Save ticket and person details to a text file
    public static void save() {
        String filename = row_letter + seat_no + ".txt"; // Construct filename
        try (FileWriter writer = new FileWriter(filename)) {
            // Write ticket information to the file
            writer.write("Ticket Information:\n");
            writer.write("Seat: " + row_letter + seat_no + "\n");
            writer.write("Price: £" + calculateSeatPrice() + "\n");
            writer.write("Person Information:\n");
            writer.write("Name: " + Person.getName() + "\n");
            writer.write("Surname: " + Person.getSurname() +"\n");
            writer.write("Email: " + Person.getEmail() +"\n");
            System.out.println("Ticket information saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing ticket information to file: " + e.getMessage());
        }
    }
}