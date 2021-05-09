package Controller;

import Model.LapCounter;
import Model.ScreenSettings;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ScreenShotController {

    private final static String trainData = "src/main/resources/data";
    private static boolean isRunning = false;

    public static void setUpTask(long refreshRate, ScreenSettings settings, boolean killExisting){
        if(isRunning && killExisting){
            //TODO: Kill Existing
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    //TODO: Get Settings from view
                    File file = takeAndWriteScreenshot(settings, "latestPic");
                    String output = readPicture(file);
                    System.out.println("Output at " + new Date(System.currentTimeMillis()).getTime() + " is: " + output);

                    //TODO: Replace with regex
                    /*String stripedOutput = output.substring(output.indexOf("RUNDE"), output.indexOf("RUNDE") + 7);
                    stripedOutput = stripedOutput.replace("RUNDE", "");*/
                    LapCounter.AddLap(output);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
        Timer timer = new Timer("Timer");

        //timer.schedule(task, refreshRate);
        timer.scheduleAtFixedRate(task, refreshRate, refreshRate);
    }

    public static BufferedImage takeScreenshot(int x, int y, int width, int height) throws AWTException{
        Robot robot = new Robot();
        return robot.createScreenCapture(new Rectangle(x, y, width, height));
    }

    public static File takeAndWriteScreenshot(ScreenSettings settings, String name) throws AWTException, IOException{
        File outputFile = new File(name + ".png");
        outputFile.delete();
        ImageIO.write(takeScreenshot(settings.getRaceSetings(0), settings.getRaceSetings(1), settings.getRaceSetings(2), settings.getRaceSetings(3)), "png", outputFile);
        return outputFile;
    }


    public static String readPicture(File dataPath) throws TesseractException {
        if(dataPath.exists()){
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath(trainData);
            return tesseract.doOCR(dataPath);
        }else{
            System.out.println(dataPath.getAbsolutePath());
        }
        return null;

    }


}
