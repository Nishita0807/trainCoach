public class TrainCoach {

    private final int totalSeats = 80;
    private final int seatsPerRow = 7;
    // private final int lastRowSeats = 3;
    private final boolean[] availableSeats = new boolean[totalSeats];

    public TrainCoach() {
        // Initialize all seats as available
        for (int i = 0; i < totalSeats; i++) {
            availableSeats[i] = true;
        }
    }

    /**
     * Books seats for a user based on availability.
     * 
     * @param numSeats: Number of seats to book (1-7)
     * @return String: List of booked seats or message if booking failed.
     */
    public String bookSeats(int numSeats) {
        if (numSeats <= 0 || numSeats > 7) {
            return "Invalid number of seats (1-7 allowed).";
        }

        StringBuilder bookedSeats = new StringBuilder();

        // Check for available seats
        int availableCount = 0;
        for (boolean seat : availableSeats) {
            if (seat) {
                availableCount++;
            }
        }

        if (availableCount < numSeats) {
            return "Not enough seats available.";
        }

        // Try booking in one row
        for (int i = 0; i <= totalSeats - numSeats; i++) {
            boolean allBooked = true;
            for (int j = 0; j < numSeats; j++) {
                if (!availableSeats[i + j]) {
                    allBooked = false;
                    break;
                }
            }
            if (allBooked) {
                for (int j = 0; j < numSeats; j++) {
                    availableSeats[i + j] = false;
                    bookedSeats.append(i + j + 1).append(", ");
                }
                bookedSeats.deleteCharAt(bookedSeats.length() - 2); // Remove trailing comma
                return "Seats booked: " + bookedSeats;
            }
        }

        // Book nearby seats if one row is unavailable
        for (int i = 0; i < totalSeats; i++) {
            int bookedInRow = 0;
            for (int j = 0; j < numSeats; j++) {
                if (i + j < totalSeats && availableSeats[i + j]) {
                    bookedInRow++;
                }
            }
            if (bookedInRow >= numSeats) {
                for (int j = 0; j < numSeats; j++) {
                    if (i + j < totalSeats) {
                        availableSeats[i + j] = false;
                        bookedSeats.append(i + j + 1).append(", ");
                    }
                }
                bookedSeats.deleteCharAt(bookedSeats.length() - 2); // Remove trailing comma
                return "Seats booked (nearby): " + bookedSeats;
            }
        }

        // Booking failed (shouldn't reach here)
        return "Booking failed.";
    }

    /**
     * Displays the seat availability status.
     */
    public void displaySeatAvailability() {
        System.out.println("Seat Availability:");
        for (int i = 0; i < totalSeats; i++) {
            System.out.print(availableSeats[i] ? "X " : "O ");// X is for seat available in coach and 0 is for seat
                                                              // filled by person in train coach
            if ((i + 1) % seatsPerRow == 0 && i != totalSeats - 1) {
                System.out.println();
            } else if (i == totalSeats - 1) {
                System.out.println(); // Last row
            }
        }
    }

    public static void main(String[] args) {
        TrainCoach trainCoach = new TrainCoach();

        // Test Case 1: Booking 4 seats
        System.out.println(trainCoach.bookSeats(4));
        trainCoach.displaySeatAvailability();

        // Test Case 2: Booking 3 seats
        System.out.println(trainCoach.bookSeats(3));
        trainCoach.displaySeatAvailability();

        // Test Case 3: Booking 7 seats
        System.out.println(trainCoach.bookSeats(7));
        trainCoach.displaySeatAvailability();

        // Test Case 4: Booking more seats than available
        System.out.println(trainCoach.bookSeats(100));

        // Test Case 5: Booking seats with invalid input
        System.out.println(trainCoach.bookSeats(0));
        System.out.println(trainCoach.bookSeats(-1));

        // Test Case 6: Booking seats in a row that is not fully available
        System.out.println(trainCoach.bookSeats(4));
        trainCoach.displaySeatAvailability();

        // Test Case 7: Displaying seat availability status
        trainCoach.displaySeatAvailability();
    }
}