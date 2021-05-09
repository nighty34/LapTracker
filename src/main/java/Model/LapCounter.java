package Model;

import java.util.ArrayList;
import java.util.List;

public class LapCounter {
    static List<String> laps = new ArrayList<>();

    public static void AddLap(String time){
        laps.add(time);
    }
}
