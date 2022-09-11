package CinemaProgram;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        Scanner sc = new Scanner(System.in);

        while (true) {
            showMenu();
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    cinema.addMovie();
                    break;
                case 2:
                    cinema.addSeance();
                    break;
                case 3:
                    cinema.removeMovie();
                    break;
                case 4:
                    cinema.removeSeance();
                    break;
                case 5:
                    cinema.showLibrary();
                    break;
                case 6:
                    cinema.showSeances();
                    break;
                case 7:
                    cinema.cinemaInfo();
                    break;
                default:
                    System.out.println("Такого варіанту відповіді не існує, спробуйте ще раз...");
                    break;
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n1 - добавити фільм до бібліотеки");
        System.out.println("2 - створити новий сеанс");
        System.out.println("3 - видалити фільм з бібліотеки та сеансів");
        System.out.println("4 - видалити певний сеанс");
        System.out.println("5 - вивести бібліотеку фільмів");
        System.out.println("6 - вивести повний список сеансів");
        System.out.println("7 - інформація про кінозал");
        System.out.print("Enter: ");
    }
}
