package com.qualcomm.ftcrobotcontroller.opmodes;
import static java.lang.Thread.sleep;
public class Sleeper {

    public void Sleep(int s) {
        try {
        sleep(s);
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}

