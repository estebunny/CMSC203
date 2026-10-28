
import java.util.Scanner;

public class MovieDriverTask2 {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String again = "y";

        while (again.equalsIgnoreCase("y")) {

            Movie movie = new Movie();

            System.out.println("Enter the name of a movie");
            String title = keyboard.nextLine();
            movie.setTitle(title);

            System.out.println("Enter the rating of the movie");
            String rating = keyboard.nextLine();
            movie.setRating(rating);

            System.out.println("Enter the number of tickets sold for this movie");
            int tickets = Integer.parseInt(keyboard.nextLine());
            movie.setSoldTickets(tickets);

            System.out.println(movie.toString());

            System.out.println("Do you want to enter another? (y or n)");
            again = keyboard.nextLine();
        }

        System.out.println("Goodbye");
        keyboard.close();
    }
}
