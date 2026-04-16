import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

public class AppTest {
    private Library library;
    
    @BeforeEach
    public void setUp() {
        library = new Library();
    }
    
    @Test
    public void testAddBook() {
        Book book = new Book("To Kill a Mockingbird", "Harper Lee");
        library.addBook(book);
        assertEquals(1, library.getBookCount());
    }
    
    @Test
    public void testRemoveBook() {
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.removeBook("To Kill a Mockingbird");
        assertEquals(0, library.getBookCount());
    }
    
    @Test
    public void testSearchBookFound() {
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger"));
        Optional<Book> result = library.searchBook("The Catcher in the Rye");
        assertTrue(result.isPresent());
        assertEquals("The Catcher in the Rye", result.get().getTitle());
    }
    
    @Test
    public void testSearchBookNotFound() {
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger"));
        Optional<Book> result = library.searchBook("Non-existent Book");
        assertFalse(result.isPresent());
    }
    
    @Test
    public void testEmptyLibrary() {
        assertEquals(0, library.getBookCount());
    }
    
    @Test
    public void testMultipleBooks() {
        library.addBook(new Book("Book 1", "Author 1"));
        library.addBook(new Book("Book 2", "Author 2"));
        library.addBook(new Book("Book 3", "Author 3"));
        assertEquals(3, library.getBookCount());
    }
    
    @Test
    public void testCaseInsensitiveSearch() {
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        Optional<Book> result = library.searchBook("the great gatsby");
        assertTrue(result.isPresent());
    }
    
    @Test
    public void testRemoveNonExistentBook() {
        library.addBook(new Book("Book 1", "Author 1"));
        library.removeBook("Non-existent");
        assertEquals(1, library.getBookCount());
    }
}
