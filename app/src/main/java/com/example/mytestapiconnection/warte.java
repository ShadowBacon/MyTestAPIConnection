package com.example.mytestapiconnection;

import java.util.concurrent.TimeUnit;

public class warte {

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
