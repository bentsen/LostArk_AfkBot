package modal;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Name: Mikkel Bentsen
 * Date: 2/16/2022
 */

public enum Step
{
    /*Makes Angle Singleton*/
    INSTANCE;

    /*Computer to communicate with hardware*/
    private final Computer computer = Computer.INSTANCE;
    /*Thread for Step*/
    private ExecutorService exec = null;
    /*Boolean for state of right*/
    private boolean right = true;
    /*Boolean for state of left*/
    private boolean left = false;
    /*Point for right location*/

    /*Sequence of bot*/
    public void afkDeny()
    {
        exec = Executors.newSingleThreadExecutor();
        exec.submit(() ->{
            while(!isInterrupted())
            {
                System.out.println("forfra");
                Alarm alarm = new Alarm(() -> move());
                System.out.println("alarm startet");
                alarm.start();

                try {
                    Thread.sleep(900000);
                } catch (InterruptedException e) {
                    alarm.cancel();
                    e.printStackTrace();
                }
            }
        });
    }
    /**/
    public void move()
    {
        /*Place the center of rectangle in the center of screen*/
        int height = (int) computer.getDisplay().getHeight() + 100/2;
        int width = (int) computer.getDisplay().getWidth() - 100/2;

        /*Create rectangle with size and location*/
        new Rectangle(100,100).setLocation(width,height);

        /*Getting random numbers 0 - 100 and convert to a screen location inside rectangle*/
        int x = (int) ((Math.random() * (100 - 0)) + 0) + width;
        int y = (int) ((Math.random() * (100 - 0)) + 0) + height;

        computer.getBot().mouseMove(x,y);
        computer.getBot().delay(2000);
        computer.getBot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
        computer.getBot().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
    /*Check if Step is ready to begin session*/
    public boolean isReady()
    {
        return exec == null || exec.isTerminated();
    }
    /*check if session is interrupted*/
    public boolean isInterrupted()
    {
        assert exec != null;
        return exec.isShutdown();
    }
    /*Interrupts the session*/
    public void interrupt()
    {
        assert !isInterrupted();
        exec.shutdownNow();
    }
}
