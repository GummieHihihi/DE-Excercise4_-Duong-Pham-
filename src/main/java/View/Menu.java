/**
 * this class have the menu of the program
 */
package View;

import Model.transition.Author;
import Model.transition.Book;

import java.util.List;

/**
 * print out the menu
 */
public class Menu {
    public static void printMenu(){
        System.out.println("WELCOME TO OUR LIBRARY");
        System.out.println("please choose the section where you want to go :");
        System.out.println("=================================================");
        System.out.println("1. List all books written by an author:");
        System.out.println("2. Search author by book 's name");
        System.out.println("3 Find out how many book has an author written");
        System.out.println("4. Search book by publish date");
        System.out.println("5. .. who is the most prolific author ?");
        System.out.println("6. List all Book");
        System.out.println("7. Exit");
    }

    /**
     * print our the sub menu for section 1
     */
    public static void printMenuSection1(){
        System.out.println("1. Order by alphabetical");
        System.out.println("2. Order by publication date");
        System.out.println("3. Order by rating");
    }

    /**
     * print out the menu for section 2
     */
    public static void printMenuSection2() {
        System.out.println("Insert the name of the book you want to search for");
    }

    /**
     * print out a list of books
     * @param listOfBook
     */
    public static void printListOfBook(List<Book> listOfBook){
        for (Book book:listOfBook) {
            System.out.println(book.toString());
        }
    }

    /**
     * print out a list of authors
     * @param authors
     */
    public static void printListOfAuthors(List<Author> authors) {
        for (Author author:authors) {
            System.out.println(author.toString());
        }
    }

    /**
     * print our sub menu for section 3
     */
    public static void printMenuSection3() {
        System.out.println("Insert the name of the Author you want to search for");
    }

    /**
     * print out menu for section 4
     */
    public static void printMenuSection4() {
        System.out.println("What year you want to search for ?");
    }

}
