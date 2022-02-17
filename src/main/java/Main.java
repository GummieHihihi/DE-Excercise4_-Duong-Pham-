import Model.raw.CSVDataHandling;
import Model.raw.CSVPath;
import Model.transition.Author;
import Model.transition.Book;
import Model.transition.LibraryData;
import Operation.Operation;
import View.Menu;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CsvValidationException, IOException {
        CSVDataHandling data = new CSVDataHandling();
        List<String[]> rawData = data.readDataCSV(CSVPath.getPath());
        LibraryData libraryData = new LibraryData();

        //create our library data
        libraryData.createLibraryData(rawData);

        //print out the menu and start the program
        int choice = 0;
        while(choice!= 7){
            Menu.printMenu();
            Scanner sc = new Scanner(System.in);
            choice = Integer.parseInt(sc.nextLine());
            Operation operation = new Operation();
            switch (choice){
                case 1:
                    System.out.println("what is the author you want to search for ?");
                    String authorName = sc.nextLine();
                    Menu.printMenuSection1();
                    int subChoiceSec1 = Integer.parseInt(sc.nextLine());
                    List<Book> bookListSec1= operation.listOfBookByAuthor(subChoiceSec1, libraryData, authorName);
                    if(bookListSec1.size()!=0){
                        Menu.printListOfBook(bookListSec1);
                    }
                    else System.out.println("no such author in our library \n");
                    break;
                case 2:
                    Menu.printMenuSection2();
                    String bookName = sc.nextLine();
                    List<Author> listOfAuthorSec2 = operation.findAuthorByBookName(bookName, libraryData);
                    if(listOfAuthorSec2.size()!=0){
                        Menu.printListOfAuthors(listOfAuthorSec2);
                    }
                    else System.out.println("no such book in our library \n");
                    break;
                case 3:
                    //list the number of book written by an Author
                    Menu.printMenuSection2();
                    String findAuthorName = sc.nextLine();
                    List<Book> listOfBookSec3 = operation.listOfBookByAuthorName(libraryData, findAuthorName);
                    if(listOfBookSec3.size()!=0){
                        Menu.printListOfBook(listOfBookSec3);
                    }
                    else System.out.println("no such book in our library \n");
                    break;
                case 4:
                    //search book by publish date
                    Menu.printMenuSection4();
                    Float findDate = Float.parseFloat(sc.nextLine());
                    List<Book> listOfBookSec4 = operation.findBookWithDate(findDate, libraryData);
                    if(listOfBookSec4.size()!=0){
                        Menu.printListOfBook(listOfBookSec4);
                    }
                    else System.out.println("no such book in our library \n");

                break;
                case 5:
                    operation.findMostPolificAuthor(libraryData);
                    break;
                case 6:
                    Menu.printMenuSection1();
                    int subChoice6 = Integer.parseInt(sc.nextLine());
                    List<Book> sec6BookList= Operation.listAllBook(subChoice6, libraryData);
                    if(sec6BookList.size()!=0){
                        Menu.printListOfBook(sec6BookList);
                    }
                    else System.out.println("no such author in our library \n");
                    break;
                case 7:
                    break;
            }
        }

    }
}
