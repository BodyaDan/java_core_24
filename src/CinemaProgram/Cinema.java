package CinemaProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Cinema {
    private TreeMap<Days, Schedule> schedules = new TreeMap<>();
    private ArrayList<Movie> moviesLibrary = new ArrayList<>();
    private Time open = new Time();
    private Time close = new Time();

    public Cinema () {
        Scanner scn = new Scanner(System.in);
        String time;
        while (true) {
            System.out.print("Введіть час відкриття кінозалу (ГГ:ХХ): ");
            time = scn.next();
            if (open.ifTimeOk(time)) {
                open.timeScan(time);
                break;
            }
            System.out.println("\nВи ввели не вірний формат! Спробуйте ще раз!\n");
        }
        while (true) {
            System.out.print("\nВведіть час закриття кінозалу (ГГ:ХХ): ");
            time = scn.next();
            if (close.ifTimeOk(time)) {
                close.timeScan(time);
                break;
            }
            System.out.println("\nВи ввели не вірний формат! Спробуйте ще раз!\n");
        }
        Arrays.stream(Days.values()).forEach(d -> schedules.put(d, new Schedule(open, close)));
        System.out.println("Чудово! Кінозал активовано!");
    }

    public void addMovie() {
        Scanner scn = new Scanner(System.in);
        System.out.print("Введіть назву фільму: ");
        String name = scn.next();
        Time time = new Time();
        String s_time;
        while (true) {
            System.out.print("Введіть тривалість фільму (ГГ:ХХ): ");
            s_time = scn.next();
            if (time.ifTimeOk(s_time)) {
                time.timeScan(s_time);
                break;
            } else System.out.println("Ви ввели не вірний формат, спробуйте знову...");
        }
        moviesLibrary.add(new Movie(name, time));
        System.out.println("Фільмі \"" + name + "\" був доданий до бібліотеки");
    }

    public void addSeance() {
        Scanner scn = new Scanner(System.in);
        String day, filmName, s_time;
        Time time = new Time();
        while (true) {
            System.out.print("Введіть день сеансу латинецею: ");
            day = scn.next();
            if (checkDay(day))
                break;
        }
        while (true) {
            System.out.print("Введіть назву фільму для сеансу: ");
            filmName = scn.next();
            if (isMovieReal(filmName))
                break;
            System.out.println("Схоже, що такого фільму немає у бібліотеці! Спробуйте ще раз...");
        }
        while (true) {
            System.out.print("Введіть початок сеансу (ГГ:ХХ): ");
            s_time = scn.next();
            if (time.ifTimeOk(s_time)) {
                time.timeScan(s_time);
                break;
            } else System.out.println("Ви ввели не вірний формат, спробуйте знову...");
        }
        schedules.get(Days.valueOf(day.toUpperCase())).addSeance(new Seance(findMovieByName(filmName), time));
    }

    public void removeMovie() {
        Scanner scn = new Scanner(System.in);
        String filmName;
        while (true) {
            System.out.print("Введіть назву фільму для видалення: ");
            filmName = scn.next();
            if (isMovieReal(filmName))
                break;
            System.out.println("Схоже, що такого фільму немає у бібліотеці! Спробуйте ще раз...");
        }
        try {
            try {
                var iterator = schedules.entrySet().iterator();
                while (iterator.hasNext())
                    iterator.next().getValue().removeSeance(findMovieByName(filmName));
            } catch (Exception e){
                System.out.println("Попробуйте ще раз, щось пішло не так! \n");
            }
            moviesLibrary.remove(findMovieByName(filmName));
            System.out.println("Фільм було успішно видалено з бібліотеки та сеансів!");
        } catch (Exception e) {
            System.out.println("\nЩось пішло не так, схоже що такого фільму не існує! Перевірте написання\n");
        }
    }

    public void removeSeance() {
        Scanner scn = new Scanner(System.in);
        String day, filmName, s_time;
        Time time = new Time();
        while (true) {
            System.out.print("Введіть день сеансу латинецею: ");
            day = scn.next();
            if (checkDay(day))
                break;
        }
        while (true) {
            System.out.print("Введіть назву фільму: ");
            filmName = scn.next();
            if (isMovieReal(filmName))
                break;
            System.out.println("Схоже, що такого фільму немає у бібліотеці! Спробуйте ще раз...");
        }
        while (true) {
            System.out.print("Введіть початок сеансу (ГГ:ХХ): ");
            s_time = scn.next();
            if (time.ifTimeOk(s_time)) {
                time.timeScan(s_time);
                break;
            } else System.out.println("Ви ввели не вірний формат, спробуйте знову...");
        }

        var iterator = schedules.entrySet().iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if (next.getKey().equals(Days.valueOf(day.toUpperCase()))) {
                try {
                    next.getValue().removeSeance(new Seance(findMovieByName(filmName), time));
                    System.out.println("\nСеанс було успішно видалено! \n");
                } catch (Exception e) {
                    System.out.println("Такого сеансу не було знайдено, прошу спробувати ще раз | " + e);
                }
            }
        }
    }

    public void showLibrary() {
        System.out.println("\nБібліотека фільмів кінозалу: ");
        for (Movie m : moviesLibrary)
            System.out.println(m);
    }

    public void showSeances() {
        System.out.println("\nВсі сеанси: ");
        var iterator = schedules.entrySet().iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            System.out.println(next.getKey().toString() + ":");
            next.getValue().outSeances();
        }
    }

    public void cinemaInfo() {
        int seanceCount = 0;
        System.out.println("Кінозал працює з " + open + " до " + close);
        System.out.println("Кількість фільмів у бібліотеці: " + moviesLibrary.size());
        var iterator = schedules.entrySet().iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            seanceCount += next.getValue().getSize();
        }
        System.out.println("Усіх сеансів: " + seanceCount);
    }

    public boolean checkDay(String str) {
        for (Days d : Days.values())
            if (d.toString().equalsIgnoreCase(str))
                return true;
        System.out.println("Немає такого дня, спробуйте ще раз...");
        return false;
    }

    public Movie findMovieByName(String movieName) {
        for (Movie m : moviesLibrary)
            if (m.getTitle().equalsIgnoreCase(movieName))
                return m;
        System.out.println("Method error");
        return null;
    }

    public boolean isMovieReal(String movieToCheck) {
        for (Movie m : moviesLibrary)
            if (m.getTitle().equalsIgnoreCase(movieToCheck))
                return true;
        return false;
    }
}
