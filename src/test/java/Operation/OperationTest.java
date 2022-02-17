package Operation;

import Model.raw.CSVDataHandling;
import Model.raw.CSVPath;
import Model.transition.Author;
import Model.transition.Book;
import Model.transition.LibraryData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class OperationTest {

    Operation operation;
    LibraryData libraryData;
    CSVDataHandling data;

    /**
     * setting up credentials need for the test
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        CSVDataHandling data = new CSVDataHandling();
        List<String[]> rawData = data.readDataCSV(CSVPath.getPath());
        libraryData = new LibraryData();
        libraryData.createLibraryData(rawData);
        operation = new Operation();

    }

    /**
     * test method to find list of book given author name
     * @throws NullPointerException
     */
    @Test
    public void listOfBookByAuthor()throws NullPointerException{
        Book expectedBook = new Book("The Hunger Games", 2008.0F, 4.34f);
        Book actualBook = operation.listOfBookByAuthor(1, libraryData, "Suzanne Collins").get(0);
        assertTrue(compareBook(expectedBook,actualBook));
        System.out.println("test done, list of books by author works");
    }

    /**
     * test method to find author by a book name
     */
    @Test
    public void findAuthorByBookName() {
        Author expectedAuthor = libraryData.getAuthorsList().stream()
                .filter(author -> author.getBooks().stream().anyMatch(book -> book.getTitle().equals("Twilight")))
                .collect(Collectors.toList()).get(0);
        assertTrue(compareAuthor(expectedAuthor, "Stephenie Meyer"));
        System.out.println("test find author by book name pass successfully");
    }

    /**
     * this function test to find the book with given input date
     */
    @Test
    public void findBookWithDate() {
        Book expectedBook = new Book("Northern Lights", 1995.0f, 3.94f);
        Book actualBook = operation.findBookWithDate(1995F, libraryData).get(0);
        assertTrue(compareBook(expectedBook, actualBook));
        System.out.println("this test pass");
    }

    /**
     * this method compare to see if 2 books are the same
     * @param book1
     * @param book2
     * @return : true if book1 is the same to book2
     */
    private boolean compareBook(Book book1, Book book2) {
        if (book1.getTitle().equals(book2.getTitle())
                && book1.getYearPublish() == book2.getYearPublish()
                && book1.getRating() == book2.getRating()) {
            return true;
        }
        return false;
    }

    /**
     * compare to see if 2 authors are the same
     * @param author1
     * @param author2
     * @return true if author 1 is the same to author 2
     */
    private boolean compareAuthor(Author author1, String author2) {
        if (author1.getName().equals(author2)){
            return true;
        }
        return false;
    }
}