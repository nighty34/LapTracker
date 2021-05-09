import Controller.ScreenShotController;
import Model.ScreenSettings;

import java.io.File;

public class Main{
    private static String pic = "src/main/resources/testPic2.PNG";

    public static void main(String[] args) {
        ScreenSettings settings = new ScreenSettings();
        settings.setRaceSetings(new int[]{
                46, 122, 279, 259
        });
        try{
            ScreenShotController.setUpTask(200, settings, false);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}