package Model;

public class ScreenSettings {
    //46, 122, 279, 259
    private int[] raceSetings = new int[3];


    public int getRaceSetings(int index) {
        return raceSetings[index];
    }

    public void setRaceSetings(int[] raceSetings) {
        this.raceSetings = raceSetings;
    }
}
