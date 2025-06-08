import java.util.Scanner;
public class w2053262_PlaneManagement {

    private static final int rows = 4;
    private static final int[] seats_per_row = {14, 12, 12, 14};
    private static final char[] row_character = {'A', 'B', 'C', 'D'};

    private static final boolean[][] seats= new boolean[rows][];
    public static Ticket[] tickets=new Ticket[rows*seats_per_row[0]];



    public static void main(String args[]) {
        initialize_seats();
        int option;
        do {
            // Display the entry message and menu
            System.out.println("Welcome to the plane management application");
            System.out.println("********************************");
            System.out.println("*        MENU OPTIONS          *");
            System.out.println("********************************");
            System.out.println("     1) Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     3) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print tickets information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");
            System.out.println("*********************************");


            Scanner input = new Scanner(System.in);
            // Ask user to enter an option
            System.out.println("Please enter an option 0 0r 1-6:");
            option = input.nextInt();

            switch (option) {

                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available();
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    Ticket.print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                default:
                    System.out.println("program terminated");
            }
        } while (option != 0);


    }
    private static void initialize_seats(){
        for(int i=0; i<rows; i++){
            seats[i] = new boolean[seats_per_row[i]];
        }
    }
    private static void buy_seat() {
        // Ask user to input a row letter and a seat number
        Scanner input = new Scanner(System.in);
        System.out.println("Please input the row letter of the seat that you want to buy (A,B,C,D)");
        String row_letter = input.next().toUpperCase();

        if(!row_letter.matches("[A-D]")){
            System.out.println("Try again. Invalid row letter");
            return;
        }

        System.out.println("Enter the seat number that you want to buy");
        int seat_no = input.nextInt();

        if (seat_no < 1 || seat_no > seats_per_row[row_letter.charAt(0) - 'A']) {
            System.out.println("Try again. Invalid seat number");
            return;
        }


        System.out.println("Enter person details:");
        Person person = Person.getDetails();

        adding_ticket(row_letter,seat_no);
        Person.printPersonInfo();
        Ticket.print_tickets_info();
        Ticket.save();

        //Check that the seat and row entered valid or not
        if (seats[row_letter.charAt(0) - 'A'][seat_no - 1]) {
            System.out.println("Seat " + row_letter + seat_no + " is already purchased");
        } else {
            seats[row_letter.charAt(0) - 'A'][seat_no - 1] = true;
            System.out.println("Seat " + row_letter + seat_no + " has been successfully purchased");
        }
    }
    public static void adding_ticket(String row_letter, int seat_no) {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] == null) {
                tickets[i] = new Ticket(row_letter, seat_no);
                break;
            }
        }
    }
    public static void cancelling_ticket(String row_letter, int seat_no) {
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null && tickets[i].getRow() == row_letter && tickets[i].getSeatNo() == seat_no) {
                tickets[i] = null;
                break;
            }
        }
    }

    private static void cancel_seat() {
        Scanner input = new Scanner(System.in);
        //Ask user to enter the row letter and seat number that want to cancel
        System.out.println("Enter the row letter of the seat that you want to cancel (A, B, C, D):");
        String row_letter = input.next().toUpperCase();

        if (!row_letter.matches("[A-D]")) {
            System.out.println("Invalid row letter");
            return;
        }

        System.out.println("Enter the seat number that you want to cancel");
        int seat_no = input.nextInt();

        if (seat_no < 1 || seat_no > seats_per_row[row_letter.charAt(0) - 'A']) {
            System.out.println("Invalid seat");
            return;
        }
        //Check that the entered seat is reserved not
        if (!seats[row_letter.charAt(0) - 'A'][seat_no - 1]) {
            System.out.println("Seat " + row_letter + seat_no + " is not reserved");
        } else {
            seats[row_letter.charAt(0) - 'A'][seat_no - 1] = false;
            cancelling_ticket(row_letter,seat_no);
            System.out.println("Seat " + row_letter + seat_no + " is cancelled successfully");
        }
    }

        //Display the first available seat
        private static void find_first_available() {
        boolean seat_available = false;
        int row = -1;
        int seatNo = -1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats_per_row[i]; j++) {
                if (!seats[i][j]) {
                    row = i;
                    seatNo = j;
                    seat_available = true;
                    break;
                }
            }
            if (seat_available) {
                break;
            }
        }

        if (seat_available) {
            System.out.println("The first available seat is row " + row_character[row] + " seat " + (seatNo + 1));
        } else {
            System.out.println("No available seats");
        }
    }
    private static void show_seating_plan() {
        for (int i=0 ; i<rows ; i++){
            System.out.print(row_character[i] + " ");
            for (int j=0 ; j<seats_per_row[i]; j++ ){
                if (!seats[i][j]){
                    System.out.print("O");
                }
                else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
    private static void search_ticket() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the row letter of the seat you want to search (A, B, C, D):");
        String row_letter = input.next().toUpperCase();

        if (!row_letter.matches("[A-D]")) {
            System.out.println("Invalid row letter");
            return;
        }

        System.out.println("Enter the seat number you want to search:");
        int seat_no = input.nextInt();

        if (seat_no < 1 || seat_no > seats_per_row[row_letter.charAt(0) - 'A']) {
            System.out.println("Invalid seat");
            return;
        }

        boolean seat_found = false;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null && tickets[i].getRow().equals(row_letter) && tickets[i].getSeatNo() == seat_no) {
                seat_found = true;
                System.out.println("Ticket Information:");
                System.out.println("Seat: " + tickets[i].getRow() + tickets[i].getSeatNo());
                System.out.println("Price: £" + Ticket.calculateSeatPrice());
                System.out.println("Passenger Information:");
                Person.printPersonInfo();
                break;
            }
        }

        if (!seat_found) {
            System.out.println("This seat is available");
        }
    }

}