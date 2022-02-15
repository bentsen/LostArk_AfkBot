package modal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public enum Step
{
    INSTANCE;

    private ExecutorService exec = null;

    public void afkDeny()
    {
        exec = Executors.newSingleThreadExecutor();
        exec.submit(() ->{



        });
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
