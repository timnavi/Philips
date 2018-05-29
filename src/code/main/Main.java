package code.main;

import code.components.*;
import code.utilities.BridgeConfigReader;

import java.awt.*;

public class Main {

    static Bridge bridge;

    public static void main(String[] args){
        BridgeConfigReader bCR = new BridgeConfigReader("config\\config.csv");
        bCR.getDataForHub(0);
        bridge = new Bridge(bCR.getIP(),bCR.getUsername(),4);
        reset(Color.RED,255);
        try {
            Thread.sleep(2000);
            cycle(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void reset(Color color, int brightness){
        bridge.setAll(bridge.power(true),bridge.brightness(brightness),bridge.color(color));
        bridge.setAll(bridge.power(false));
    }

    private static void cycle(int millis) throws InterruptedException{
        for (int i = 0; i < bridge.getLightAmt(); i++) {
            bridge.set(i+1,bridge.power(true));
            Thread.sleep(millis);
            bridge.set(i+1,bridge.power(false));
        }
    }

}
