package code.main;

import code.components.*;

import java.awt.*;

public class Main {

    public static void main(String[] args){
        String ip,un;
        Bridge bridge = new Bridge(ip,un,4);
        bridge.setAll(bridge.power(false));
    }
}
