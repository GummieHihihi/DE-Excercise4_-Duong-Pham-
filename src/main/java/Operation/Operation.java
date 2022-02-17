/**
 * this class contain all the logic of the application
 */
package Operation;

import Model.transition.Author;
import Model.transition.Book;
import Model.transition.LibraryData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Operation {
    /**
     * this method get all the books written by an author
     * @param userChoice : user's choice for listing : after alphabet or by publication date or by rating
     * @param libraryData : the data of our library
     * @param authorName : the name of the author customer wish to search for
     * @return : book[] written by that author
     */
    public List<Book> listOfBookByAuthor(int userChoice, LibraryData libraryData, String authorName){
        List<Book> returnBook = new ArrayList<Book>();
        libraryData.getAuthorsList().stream()
                .filter(author -> author.getName().equals(authorName))
                .map(author -> author.getBooks())
                .forEach(books -> books.stream().forEach(book -> returnBook.add(book)));
        switch (userChoice) {
            case 1:
                //sort after the book title
                returnBook.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
                break;
            case 2:
                //sort after year publish
                returnBook.sort((o1, o2) -> (int) (o1.getYearPublish() - o2.getYearPublish()));
                break;
            case 3:
                //sort after rating
                returnBook.sort((o1, o2) -> (int)(o1.getRating()-o2.getRating()));
                break;
        }
        return returnBook;
    }

    /**
     * return a list of book written by an author follow by no order
     * @param libraryData : data of our library
     * @param authorName : the name of the author user wish to search for
     * @return : book[] written by that author
     */
    public List<Book> listOfBookByAuthorName(LibraryData libraryData, String authorName){
        List<Book> returnList = new ArrayList<Book>();
        libraryData.getAuthorsList().stream()
                .filter(author -> author.getName().equals(authorName))
                .map(author -> author.getBooks())
                .forEach(books -> books.stream().forEach(book -> returnList.add(book)));
        return returnList;
    }

    /**
     * list all the book in the library
     * @param userChoice : order by which criteria
     * @param libraryData : data of the library
     * @return : all books of the library
     */
    public static List<Book> listAllBook(int userChoice, LibraryData libraryData){
        List<Book> returnBook = new ArrayList<Book>();
        switch (userChoice) {
            case 1:
                //listing by the order of author name
                returnBook = libraryData.getBookList();
                //sort after the book title
                returnBook.sort((o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
                break;
            case 2:
                returnBook = libraryData.getBookList();
                //sort after publish year
                returnBook.sort((o1, o2) -> (int) (o1.getYearPublish() - o2.getYearPublish()));
                break;
            case 3:
                //sort after the book's rating
                returnBook = libraryData.getBookList();
                returnBook.sort((o1, o2) -> (int)(o1.getRating()-o2.getRating()));
                break;
        }
        return returnBook;
    }

    /**
     * find author by book name
     * @param bookName
     * @param data
     * @return list of authors written the book
     */
    public List<Author> findAuthorByBookName(String bookName, LibraryData data) {
        List<Author> authors = data.getAuthorsList().stream()
                .filter(author -> author.getBooks().stream().anyMatch(book -> book.getTitle().equals(bookName)))
                .collect(Collectors.toList());
        return authors;
    }

    /**
     * find the book with the current date
     * @param findDate
     * @param libraryData
     * @return list of book written in that year
     */
    public List<Book> findBookWithDate(Float findDate, LibraryData libraryData) {
        List<Book> returnList = new ArrayList<Book>();
        returnList = libraryData.getBookList().stream().filter(book -> book.getYearPublish() == findDate)
                .collect(Collectors.toList());
        return returnList;
    }

    /**
     * find out who's the most polific author
     * @param libraryData
     */
    public void findMostPolificAuthor(LibraryData libraryData) {
        Author returnAuthor = libraryData.getAuthorsList().stream()
                .max((author1, author2) -> (int)author1.getBooks().size()-author2.getBooks().size())
                .get();
        System.out.println(returnAuthor.toString());
    }
}

