package com.qualcomm.ftcrobotcontroller.opmodes;
//package
import java.io.IOException;

import static java.lang.Thread.sleep;
//imports
public class Sleeper {
//class sleeper extends linearOpMode
    //the constructer
    public void sleeper (int sleep) {
        Sleep(sleep);
    }
    //method
    public void Sleep(int s) {
    try {
        sleep(s);
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}

