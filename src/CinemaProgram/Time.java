package CinemaProgram;

import java.util.function.Function;

public class Time {
    int min;
    int hour;

    Function<String, Integer> toInt = Integer::parseInt;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void timeScan(String string) {
        String [] hm = string.split(":");
        setHour(toInt.apply(hm[0]));
        setMin(toInt.apply(hm[1]));
    }

    public boolean ifTimeOk(String string) {
        int m, h;
        try {
            String[] hm = string.split(":");
            h = toInt.apply(hm[0]);
            m = toInt.apply(hm[1]);
            //System.out.println(h + " | " + m);
            if ((h <= 23) && (h >= 0) && (m >= 0) && (m <= 59))
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + hour + ":" + min + ")";
    }
}
