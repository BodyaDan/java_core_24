package CinemaProgram;

public class Seance implements Comparable<Seance>{
    private Movie movie;
    private Time startTime;
    private Time endTime;

    public Seance(Movie movie, Time startTime) {
        this.movie = movie;
        this.startTime = startTime;
        endTime = sumTimes(startTime, movie.getDuration());
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Time sumTimes(Time t1, Time t2) {
        int m, h;
        Time time = new Time();
        m = t1.getMin() + t2.getMin();
        h = t1.getHour() + t2.getHour();
        if (m >= 60) {
            m -= 60;
            h++;
        }
        if (h >= 24)
            h -= 24;
        time.setHour(h);
        time.setMin(m);
        return time;
    }

    @Override
    public String toString() {
        return "Сеанс: " + movie +
                ", початок - " + startTime +
                ", закінчується - " + endTime;
    }

    @Override
    public int compareTo(Seance o) {
        return this.movie.getTitle().compareTo(o.getMovie().getTitle());
    }
}
