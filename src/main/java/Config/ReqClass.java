package Config;

import jakarta.xml.bind.JAXBException;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import static Utils.DataReader.getBaseUrl;
import static Utils.DataReader.getPostEndpoint;
import static aquality.selenium.browser.AqualityServices.getLogger;

public class ReqClass {
    public static HttpResponse<String> getBooks() {
        try {
            getLogger().info("Sending Get request for books");
            HttpResponse<String> response = Unirest.get(getBaseUrl()+getPostEndpoint()).asString();
            return response;
        } catch (UnirestException e) {
            getLogger().info(e.getMessage());
            throw new RuntimeException("Error sending request to get books list from web", e);

        }
    }
}
