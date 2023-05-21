package Utils;

import Dto.Book;
import Dto.Catalog;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

import static Config.ReqClass.getBooks;
import static aquality.selenium.browser.AqualityServices.getLogger;

public class ParseUtil {

    public static List<Book> getBooksObj() {
        try {
            getLogger().info("Creating Objects from xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(getBooks().getBody());
            Catalog catalog = (Catalog) unmarshaller.unmarshal(reader);
            List<Book> bookList = catalog.getBooksList();
            return bookList;
        }catch (JAXBException e){
            throw new RuntimeException("Error creating book list from XML", e);
        }
    }
}
