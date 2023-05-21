package Utils;

import Dto.Book;
import jakarta.xml.bind.JAXBException;
import kong.unirest.HttpResponse;

import java.util.ArrayList;
import java.util.Collections;

import static Utils.ParseUtil.getBooksObj;
import static aquality.selenium.browser.AqualityServices.getLogger;

public class ResponseUtils {
    public static boolean checkType(HttpResponse<String> response) {
        getLogger().info("check if info is valid type");
        String contentType = response.getHeaders().get("Content-Type").get(0);
        if (contentType.toLowerCase().contains("xml")) {
            return true;
        } else {
            getLogger().error("Response body is not a JSON type");
            return false;
        }
    }
    public static ArrayList<Integer> getIdNumbs() throws JAXBException {
        ArrayList<Integer> idNumbs = new ArrayList<>();
        for (Book book : getBooksObj()){
            String strings = book.getId();
            String numStr = strings.replaceAll("\\D+", "");
            int num = Integer.parseInt(numStr);
            idNumbs.add(num);
        }
        return idNumbs;

    }
    public static boolean checkIfBooksAreSorted(){
        try {
            getLogger().info("Chceking books are sorted by id");
            ArrayList<Integer> sortedIdNumbers = new ArrayList<>(getIdNumbs());
            Collections.sort(sortedIdNumbers);
            return sortedIdNumbers.equals(getIdNumbs());
        } catch (JAXBException e) {
            getLogger().error("error while checking books sorting");
            throw new RuntimeException("error while checking books sorting " + e);
        }
    }
    public static boolean checkLowHighPriceBooks(){
        getLogger().info("Chceking if books with lowest and highest price have different information");
        ArrayList<Book> books = new ArrayList<>(getBooksObj());
        Book highestPrice = books.get(0);
        Book lowestPrice = books.get(0);
        for (int i = 0;i<books.size();i++) {
            if (highestPrice.getPrice() < books.get(i).getPrice()) {
                highestPrice = books.get(i);
            } else if (lowestPrice.getPrice() > books.get(i).getPrice()) {
                lowestPrice = books.get(i);
            }
        }if (highestPrice.getAuthor().equals(lowestPrice.getAuthor())
                && highestPrice.getDescription().equals(lowestPrice.getDescription())){
            return false;
        }
        return true;
    }
}
