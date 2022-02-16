package modal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Alarm
{

    /*time for when alarm trigger*/
    private final int time;
    /*method that should fire when alarm trigger*/
    private final Listener listener;
    /*Thread for alarm*/
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    /*boolean for state of alarm*/
    public boolean active = true;


    public Alarm(final int time, final Listener listener)
    {
        this.time = time;
        this.listener = listener;
    }
    /*Countdown method*/
    public void run()
    {
        System.out.println("alarmen er her");
        int timet = time;
        long delay = timet * 1000L;

        do
        {
            int minutes = timet / 60;
            int seconds = timet % 60;
            System.out.println(minutes + " minute(s), " + seconds + " Second(s)");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().stop();
            }
            timet = timet - 1;
            delay = delay - 1000;
        }
        while (delay != 0);
    }
    /*Cancel countdown*/
    public void cancel()
    {
        System.out.println("Alarm got cancel");
        exec.shutdownNow();
    }
    /*Start countdown*/
    public void start()
    {
        assert !exec.isShutdown();

        exec.submit(() -> {
            System.out.println("så starter run");
            active = true;
            run();
           listener.fire();
           active = false;
        });
    }
}
