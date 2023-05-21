import Utils.StatusCode;
import org.testng.Assert;
import org.testng.annotations.Test;
import static Config.ReqClass.getBooks;
import static Utils.ResponseUtils.*;

public class MockyBooksTest {

    @Test
    public void getBooksTest() {
        Assert.assertEquals(getBooks().getStatus(), StatusCode.OK.getCode(),"Expected status code to be 200");
        Assert.assertTrue(checkType(getBooks()),"Excpected type is not xml!");
        Assert.assertTrue(checkIfBooksAreSorted(),"books are not sorted by id!");
        Assert.assertTrue(checkLowHighPriceBooks(),"lowest and highest price books must have different author and description!");
    }
}
