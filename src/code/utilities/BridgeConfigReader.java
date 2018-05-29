package code.utilities;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class BridgeConfigReader {

    String filename;
    String username;
    String ip;

    public BridgeConfigReader(String filename){
        this.filename = filename;
    }

    public void getDataForHub(int hubIndex){
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        lines.remove(0);
        String[] info = lines.get(hubIndex).split(",");
        ip = info[1];
        username = info[2];
    }

    public String getIP() {
        return ip;
    }

    public String getUsername() {
        return username;
    }


}
