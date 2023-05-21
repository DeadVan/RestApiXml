package Utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class DataReader {
    static ISettingsFile environment = new JsonSettingsFile("config.json");

    public static String getBaseUrl() {
        return environment.getValue("/base_url").toString();
    }

    public static String getPostEndpoint() {
        return environment.getValue("/post_endpoint").toString();
    }
}
