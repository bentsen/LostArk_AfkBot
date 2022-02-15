package modal;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum Step
{
    INSTANCE;


    private final Computer computer = Computer.INSTANCE;

    private ExecutorService exec = null;

    private boolean right = true;

    private boolean left = false;

    private Point rightPoint = goRight();

    private Point leftPoint = goLeft();

    public void afkDeny()
    {
        exec = Executors.newSingleThreadExecutor();
        exec.submit(() ->{

            while(!isInterrupted())
            {
                Alarm alarm = new Alarm(15000, () -> move());
                alarm.start();

                while(alarm.active)
                {
                    try {
                        exec.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void move()
    {
        if(right && !left)
        {
            computer.getBot().mouseMove(rightPoint.x, rightPoint.y);
            computer.getBot().delay(2000);
            computer.getBot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
        }

        else if (left && !right)
        {
            computer.getBot().mouseMove(leftPoint.x, leftPoint.y);
            computer.getBot().delay(2000);
            computer.getBot().mousePress(InputEvent.BUTTON1_DOWN_MASK);
        }
    }

    public Point goLeft()
    {
        int width = (int) (computer.getDisplay().getWidth() / 2);
        int height = (int) (computer.getDisplay().getHeight() / 2);

        return new Point(width - 100,height);
    }

    public Point goRight()
    {
        int width = (int) (computer.getDisplay().getWidth() / 2);
        int height = (int) (computer.getDisplay().getHeight() / 2);

        return new Point(width + 100,height);
    }

    public boolean isReady()
    {
        return exec == null || exec.isTerminated();
    }

    public boolean isInterrupted()
    {
        assert exec != null;
        return exec.isShutdown();
    }

    public void interrupt()
    {
        assert !isInterrupted();
        exec.shutdownNow();
    }
}
