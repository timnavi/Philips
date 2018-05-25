package code.components;

import code.utilities.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class Light {

    private URL url;

    public Light(int lightNumber, Bridge bridge){
        try {
            this.url = new URL(bridge.getURL().toString() + "lights/" + lightNumber + "/");
        } catch (MalformedURLException e) {
            url = null;
            e.printStackTrace();
        }
    }

    public boolean isOn(){
        boolean retVal;
        JSONObject json;
        try {
            json = new JSONObject(CLIPUtilities.getHTML(url));
            retVal = json.getJSONObject("state").getBoolean("on");
        } catch (Exception e) {
            retVal = false;
        }
        return retVal;
    }

//    public JSONObject togglePower(){
//        return bridge.power(!isOn());
//    }

    public void set(JSONObject...args) {
        JSONObject retVal = new JSONObject();
        powerFor:
        for (JSONObject j : args) {
            for (Iterator it = j.keys(); it.hasNext(); ) {
                String key = (String) it.next();
                if (key.equals("on")) {
                    try {
                        retVal.put(key, j.get(key));
                    } catch (JSONException e) {

                    }
                    break powerFor;
                }
            }
        }
        for (JSONObject j : args) {
            for (Iterator it = j.keys(); it.hasNext(); ) {
                String key = (String) it.next();
                try {
                    retVal.put(key, j.get(key));
                } catch (JSONException e) {

                }
            }
        }
        try {
            CLIPUtilities.putHTML(statusURL(), retVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private URL statusURL(){
        try {
            return new URL(url.toString() + "state/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
