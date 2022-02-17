/**
 * this class creating the data for the whole library
 * bookList : the list of all the books in the library
 * authorsList : the list of all authors in the library
 * headList : this hashmap store the data about the position of the column with the name of that column
 * for eg : the id is the number 1, we can scroll down from there to get the appropriate attribute
 */
package Model.transition;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryData {
    private List<Book> bookList;
    private List<Author> authorsList;
    //this hashmap store the data about the position of the column with the name of that column
//    for eg : the id is the number 1, we can scroll down from there to get the appropriate attribute
    private HashMap<String, Integer> headList;

    public HashMap<String, Integer> getHeadList() {
        return headList;
    }

    /**
     * initiate with all empty list
     */
    public LibraryData() {
        this.bookList = new ArrayList<Book>();
        this.authorsList = new ArrayList<Author>();
        headList = new HashMap<>();
    }

    /**
     * store the head of the csv file in a HashMap, which will let us know the position of each component
     * later on when we want to call for the position of the content we want, we just call the content itself
     * instead of the position number
     * @param lineData
     */
    //push the data content with the position of it so that we can call the position of it later on with the content of that column
    public void storeHeadData(String[] lineData) {
        for (int i = 0; i < lineData.length; i++) {
            headList.put(lineData[i], i);
        }
    }

    /**
     * add the author into our list in the library
     * @param author
     */
    public void createAuthorFromData(Author author) {

        if (authorsList.stream().anyMatch(authorInList -> authorInList.getName().equals(author.getName()))) {
            return;
        }
        //add this new book to our list
        authorsList.add(author);
    }

    /**
     * add the book into our library list of books
     * @param newBook
     */
    public void createBookFromData(Book newBook) {
//            check if book already exist or not in the list
        if (bookList.stream().anyMatch(b -> b.getTitle().equals(newBook.getTitle()))) {
            return;
        }
        //add this new book to our list
        bookList.add(newBook);
    }

    /**
     * add the book to the author who wrote it
     * @param book
     * @param author
     */
    public void addBookToAuthor(Book book, Author author) {
        //book does appear in the author -> not adding
        if (author.getBooks().stream().anyMatch(b1 -> b1.equals(book))) {
            return;
        }
        //add book to author
        else {
            author.addBook(book);
        }
    }

    /**
     *
     * @return list of book in the library
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     *
     * @return list of authors in the library
     */
    public List<Author> getAuthorsList() {
        return authorsList;
    }

    /**
     * create the data for the library
     * @param rawData : line[] which we create in the csv file
     */
    public void createLibraryData(List<String[]> rawData) {
        boolean firstLine = true;
        for (String[] line : rawData) {
            //read first line

            if (!firstLine) {
                String bookName = line[this.getHeadList().get("original_title")].trim().replaceAll("[^\\p{L}\\p{Z}]","");
                float date = Float.parseFloat(line[this.getHeadList().get("original_publication_year")]);
                float rating = Float.parseFloat(line[this.getHeadList().get("average_rating")]);
                if (date > 0 && !bookName.equals("")) {
                    Book newBook = new Book(bookName, date, rating);
                    this.createBookFromData(newBook);
                    //create new author, we need to split author in case there's more than 1 author writting the book
                    String[] authorNames = line[this.getHeadList().get("authors")].split(",");
                    //add book to author
                    for (String authorName : authorNames) {
                        //create new author, remove all the special character in his/her name
                        Author newAuthor = new Author(authorName.trim().replaceAll("[^\\p{L}\\p{Z}]",""));
                        //add book to author
                        this.addBookToAuthor(newBook, newAuthor);
                        //add author to our library data list
                        this.createAuthorFromData(newAuthor);
                }
            }
        }
        while (firstLine) {
            this.storeHeadData(line);
            firstLine = false;
        }
    }

}
}
