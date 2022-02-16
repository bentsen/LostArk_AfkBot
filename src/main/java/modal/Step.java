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
    private Point rightPoint = goRight();
    /*Point for left location*/
    private Point leftPoint = goLeft();

    /*Sequence of bot*/
    public void afkDeny()
    {
        exec = Executors.newSingleThreadExecutor();
        exec.submit(() ->{
            while(!isInterrupted())
            {
                System.out.println("forfra");
                Alarm alarm = new Alarm(20, () -> move());
                System.out.println("alarm startet");
                alarm.start();

                try {
                    Thread.sleep(25000);
                } catch (InterruptedException e) {
                    alarm.cancel();
                    e.printStackTrace();
                }
            }
        });
    }
    /*Check and move to right or left*/
    public void move()
    {
        if(right && !left)
        {
            computer.getBot().mouseMove(rightPoint.x, rightPoint.y);
            computer.getBot().delay(2000);
            computer.getBot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
            computer.getBot().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            right = false;
            left = true;
        }

        else if (left && !right)
        {
            computer.getBot().mouseMove(leftPoint.x, leftPoint.y);
            computer.getBot().delay(2000);
            computer.getBot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
            computer.getBot().mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            right = true;
            left = false;
        }
    }
    /*Point of left position*/
    public Point goLeft()
    {
        int width = (int) (computer.getDisplay().getWidth() / 2);
        int height = (int) (computer.getDisplay().getHeight() / 2);

        return new Point(width - 100,height);
    }
    /*Point of right position*/
    public Point goRight()
    {
        int width = (int) (computer.getDisplay().getWidth() / 2);
        int height = (int) (computer.getDisplay().getHeight() / 2);

        return new Point(width + 100,height);
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
