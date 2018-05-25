package code.components;

import code.utilities.ColorSpaceConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.net.*;
import java.util.Arrays;
import java.util.Iterator;

public class Bridge implements Iterable<Light> {

    private URL url;
    private Light[] lights;
    ColorSpaceConverter converter;

    public Bridge(String ip, String username, int numberOfLights) {
        url = null;
        try {
            url = new URL("http://" + ip + "/api/" + username + "/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        lights = new Light[numberOfLights];
        for(int i=0;i<numberOfLights;i++){
            Light light = new Light(i+1,this);
            lights[i] = light;
        }
        converter = new ColorSpaceConverter();
    }

    public void setAll(JSONObject...args){
        for(Light l:this){
            l.set(args);
        }
    }

    public void set(int light,JSONObject...args){
        lights[light-1].set(args);
    }

    public JSONObject power(boolean on){
        try {
            return new JSONObject().put("on",on);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public JSONObject brightness(int b) {
        try {
            return new JSONObject().put("bri",b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public JSONObject color(Color color){
        JSONArray xy = new JSONArray();
        JSONObject retVal = new JSONObject();
        double[] xyY = converter.XYZtoxyY(converter.RGBtoXYZ(color.getRed(),color.getGreen(),color.getBlue()));
        try {
            retVal.put("xy",xy.put(xyY[0]).put(xyY[1]));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    public JSONObject colorMode(String mode){
        try {
            return new JSONObject().put("colormode",mode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public JSONObject effect(String effect){
        try {
            return new JSONObject().put("effect",effect);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public JSONObject alert(boolean on){
        String msg = on ? "select" : "none";
        try {
            return new JSONObject().put("alert",msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public URL getURL() {
        return url;
    }

    @Override
    public Iterator<Light> iterator() {
        return Arrays.asList(lights).iterator();
    }
}