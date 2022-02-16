package modal;

import java.awt.*;

/*
 * Name: Mikkel Bentsen
 * Date: 2/16/2022
 */

public enum Computer
{
    /*Makes Computer singleton*/
    INSTANCE;


    /*Robot to communicate with mouse and keyboard*/
    private final Robot bot = createRobot();
    /*Toolkit to get operating system settings*/
    private final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
    /*Get the screen size of main monitor*/
    private final Dimension MAIN_DISPLAY = TOOLKIT.getScreenSize();


    /*create robot to access mouse and keyboard*/
    private Robot createRobot()
    {
        Robot bot = null;
        try
        {
            bot = new Robot();
        }
        catch (AWTException e)
        {
            e.printStackTrace();
        }
        return bot;
    }
    /*return Robot*/
    public Robot getBot()
    {
        return bot;
    }
    /*return the Dimension*/
    public Dimension getDisplay()
    {
        return MAIN_DISPLAY;
    }
}