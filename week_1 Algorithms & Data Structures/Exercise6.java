package weekone;
import java.util.Arrays;
import java.util.Comparator;



class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book(" + bookId + ", " + title + ", " + author + ")";
    }
}

public class Exercise6 {
    private Book[] books;
    private int size;

    public Exercise6(int capacity) {
        books = new Book[capacity];
        size = 0;
    }

    // Add a book to the library
    public void addBook(Book book) {
        if (size < books.length) {
            books[size++] = book;
        } else {
            System.out.println("Library is full, cannot add more books.");
        }
    }

    // Linear search to find books by title
    public Book linearSearchByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i].title.equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null; // Book not found
    }

    // Binary search to find books by title (assuming the list is sorted)
    public Book binarySearchByTitle(String title) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);

            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Book not found
    }

    // Method to sort the books array by title
    public void sortBooksByTitle() {
        Arrays.sort(books, 0, size, Comparator.comparing(b -> b.title));
    }

    public static void main(String[] args) {
        Exercise6 library = new Exercise6(10);

        library.addBook(new Book(1, "The Catcher in the Rye", "J.D. Salinger"));
        library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(3, "1984", "George Orwell"));

        System.out.println("Linear Search for '1984':");
        System.out.println(library.linearSearchByTitle("1984"));

        library.sortBooksByTitle();

        System.out.println("\nBinary Search for '1984':");
        System.out.println(library.binarySearchByTitle("1984"));
    }
}
