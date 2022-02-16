package controller;

import modal.Step;

/*
 * Name: Mikkel Bentsen
 * Date: 2/16/2022
 */

public enum Controller
{
    /*Makes Controller Singleton*/
    INSTANCE;


    /*Start the program*/
    public void start()
    {
        assert Step.INSTANCE.isReady();
        Step.INSTANCE.afkDeny();
    }
    /*Stop the program*/
    public void stop()
    {
        assert !Step.INSTANCE.isReady();
        Step.INSTANCE.interrupt();
    }
}
