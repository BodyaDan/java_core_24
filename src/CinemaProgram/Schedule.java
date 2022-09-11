package CinemaProgram;

import java.util.Set;
import java.util.TreeSet;

public class Schedule {
    private Time closeTime = new Time();
    private Time openTime = new Time();
    private Set<Seance> seances = new TreeSet<>();

    public Schedule(Time openTime ,Time closeTime) {
        this.closeTime = closeTime;
        this.openTime = openTime;
    }

    public void addSeance(Seance seance) {
        Time start = seance.getStartTime();
        Time end = seance.getEndTime();
        if ((end.getHour() < closeTime.getHour()) ||
                ((end.getHour() == closeTime.getHour()) && (end.getMin() <= closeTime.getMin()))) {
            if ((start.getHour() > openTime.getHour()) ||
                    ((start.getHour() == openTime.getHour()) && (start.getMin() >= openTime.getMin()))) {
                seances.add(seance);
                System.out.println("Сеанс успішно доданий! \n");
            } else System.out.println("Сеанс не був доданий! Кінозал ще буде закритий!");
        } else System.out.println("Сеанс не був доданий! Кінозал вже буде закритий!");
    }

    public void removeSeance(Seance seance) {
        try {
            seances.remove(seance);
        } catch (Exception e) {
            System.out.println("Попробуйте ще раз, щось пішло не так! \n");
        }
    }

    public void removeSeance(Movie movie) {
        try {
            var iterator = seances.iterator();
            while (iterator.hasNext())
                if (iterator.next().getMovie().equals(movie))
                    iterator.remove();
        } catch (Exception e) {
            System.out.println("В сеансі даного фільму не було =) \n");
        }
    }

    public int getSize() {
        return seances.size();
    }

    public void outSeances() {
        for (Seance s : seances)
            System.out.println("\t" + s);
    }
}
