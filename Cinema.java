package cinema;

import java.util.Scanner;

public class Cinema {
    private static Scanner sc = new Scanner(System.in);
    private static int[][] seat;
    private static int currentIncome;
    private static int purchasedTickets;

    public static void main(String[] args) {
        menu();
    }

    /**
     * Print options for Cinema and call method for selected option.
     */
    public static void menu() {
        boolean flag = true;
        inputSeats();

        while (flag) {
            printOptions();
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    displayCinema();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }

    }

    /**
     * Printing options for cinema menu.
     */
    private static void printOptions() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit\n");
    }

    /**
     * Get user input of rows and seatsPerRows.
     * @return int[][] return 2-dimensional array.
     */
    private static int[][] inputSeats() {
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = sc.nextInt();
        return seat = new int[rows][seatsPerRow];
    }

    /**
     * Display cinema rows and seats, if seat is occupied display B if not display S.
     */
    public static void displayCinema() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int seatsPerRow = 1; seatsPerRow <= seat[0].length; seatsPerRow++) {
            System.out.print(seatsPerRow + " ");
        }
        System.out.println(""); // skip to next line
        for (int row = 0; row < seat.length; row++) {
            System.out.print((row + 1) + " ");
            for (int seatsPerRow = 0; seatsPerRow < seat[0].length; seatsPerRow++) {
                if (seat[row][seatsPerRow] == 1) {
                    System.out.print("B ");
                    continue;
                }
                System.out.print("S ");
            }
            System.out.println(""); // skip to next line
        }
    }

    /**
     * User enter input row and seat to buy a ticket.
     * If Cinema Capacity rows * seatsPerRow <= 60 ticketPrice is 10$
     * If Cinema Capacity is higher than 60 seats, front row ticket price is 10$, back rows price is 8$;
     * seat[][] is 2-dimensional array and we only defined length from user input but not the values of each element..
     * that means that all elements in array have value of 0, so we set seat value in specific row to 1;
     */
    public static void buyTicket() {
        boolean flag = true;
        int ticketRow = 0;
        int ticketSeat = 0;
        while (flag) {
            System.out.println("Enter a row number:");
            ticketRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            ticketSeat = sc.nextInt();

            if (ticketRow > seat.length || ticketSeat > seat[0].length) {
                System.out.println("Wrong input!");
                continue;
            }

            if (!isOccupied(ticketRow, ticketSeat)) {
                flag = false;
            } else {
                System.out.println("That ticket has already been purchased!");
            }
        }

        int totalSeats = seat.length * seat[0].length; // Example: 9 rows * 5 seats in each row;
        int ticketPrice = 0;
        int frontRows = seat.length / 2; // rows divided by 2
        int backRows = seat.length - frontRows;

        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else if (ticketRow <= frontRows) {
            ticketPrice = 10;
        } else {
            ticketPrice = 8;
        }
        System.out.println("Ticket price: $" + ticketPrice);
        purchasedTickets++;
        currentIncome += ticketPrice;
        seat[ticketRow-1][ticketSeat-1] = 1; // set 1 because this seat is taken
    }

    /**
     * Check if user try to buy a taken seat
     * @param ticketRow user ticketRow
     * @param ticketSeat int user seat in ticketRow
     * @return boolean if seat is occupied or not
     */
    private static boolean isOccupied(int ticketRow, int ticketSeat) {
        for (int row = 0; row < seat.length; row++) {
            for (int seatsPerRow = 0; seatsPerRow < seat[0].length; seatsPerRow++) {
                if (seat[ticketRow-1][ticketSeat-1] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Count percentage of purchased tickets and print statistics.
     */
    public static void statistics() {
        int totalSeats = seat.length * seat[0].length;
        double percentage = ((double) purchasedTickets / (double) totalSeats) * 100d;
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome());

    }

    /**
     * Count totalIncome of purchased tickets based on totalSeats and position in the Cinema...
     * @return int totalIncome of purchased tickets.
     */
    private static int totalIncome() {
        int totalSeats = seat.length * seat[0].length; // Example: 9 rows * 5 seats in each row;
        int totalIncome = 0;
        int frontRows = seat.length / 2; // rows divided by 2
        int backRows = seat.length - frontRows;

        if (totalSeats <= 60) {
            return totalIncome = totalSeats * 10;
        }  else {
            return totalIncome = (frontRows * seat[0].length) * 10 + (backRows * seat[0].length) * 8;
        }
    }
}
