package controller;

import modal.Step;

public enum Controller
{
    /*Makes Controller Singleton*/
    INSTANCE;

    public void start()
    {
        assert Step.INSTANCE.isReady();
        Step.INSTANCE.afkDeny();
    }

    public void stop()
    {
        assert !Step.INSTANCE.isReady();
        Step.INSTANCE.interrupt();
    }
}
