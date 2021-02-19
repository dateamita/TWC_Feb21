package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilities {

    /**Generate Random integer value between 1 and max value
     Used random method to generate random value*/
    public int randomNum(int maxVal){
        int randomNo = (int)(Math.random() * (maxVal - 1 + 1) + 1);
        return randomNo;
    }

    /**Read the config.properties file and return the property value*/
    public String getProperties(String property) throws IOException{
        Properties prop = new Properties();
        FileInputStream objFile = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
        prop.load(objFile);
        return prop.getProperty(property);
    }
}