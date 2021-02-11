package utility;

import java.io.IOException;
import java.io.InputStream;
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
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("configurations/config.properties");
        prop.load(inputStream);
        return prop.getProperty(property);
    }
}