/**
 * this class define the Book
 * title : the title of the book
 * yearPublish : the year this book publish
 * rating : the rating of this book
 */
package Model.transition;

import java.util.Date;

public class Book {
    private String title;
    private float yearPublish;
    private float rating;

    public Book(String title, Float yearPublish, float rating){
        this.title= title;
        this.yearPublish = yearPublish;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public float getYearPublish() {
        return yearPublish;
    }

    public float getRating() {
        return rating;
    }

    /**
     * this method comparing 2 books, this and another one
     * @param anotherBook
     * @return true if they have the same title name
     */
    public boolean equals(Book anotherBook){
        if(this.getTitle().equals(anotherBook.getTitle())){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", yearPublish=" + yearPublish +
                ", rating=" + rating +
                '}';
    }
}
