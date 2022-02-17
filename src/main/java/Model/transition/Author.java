/**
 * this class define the Author of the library
 * @param name : name of the author
 * @param books : List of book this author has
 */
package Model.transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Author {
    private String name;
    private List<Book> books = new ArrayList<Book>();

    public Author(String name){
        this.name = name;
//        this.books=books;
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", books=" + books + "\n number of books : " + books.size() +
                '}';
    }
}
