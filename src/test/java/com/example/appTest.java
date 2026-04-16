package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryManagementSystemTest {
    private LibraryManagementSystem library;
    private Book book1, book2;
    private Member member1, member2;

    @BeforeEach
    public void setUp() {
        library = new LibraryManagementSystem();
        book1 = new Book("B001", "Java Programming", "John Doe");
        book2 = new Book("B002", "Design Patterns", "Gang of Four");
        member1 = new Member("M001", "Alice");
        member2 = new Member("M002", "Bob");

        library.addBook(book1);
        library.addBook(book2);
        library.addMember(member1);
        library.addMember(member2);
    }

    @Test
    public void testBorrowBookSuccess() {
        String result = library.borrowBook("M001", "B001");
        assertEquals("Book borrowed successfully", result);
        assertFalse(book1.isAvailable());
    }

    @Test
    public void testBorrowBookNotAvailable() {
        library.borrowBook("M001", "B001");
        String result = library.borrowBook("M002", "B001");
        assertEquals("Book is not available", result);
    }

    @Test
    public void testBorrowBookMemberNotFound() {
        String result = library.borrowBook("M999", "B001");
        assertEquals("Member not found", result);
    }

    @Test
    public void testBorrowBookNotFound() {
        String result = library.borrowBook("M001", "B999");
        assertEquals("Book not found", result);
    }

    @Test
    public void testReturnBook() {
        library.borrowBook("M001", "B001");
        assertFalse(book1.isAvailable());

        String result = library.returnBook("M001", "B001");
        assertEquals("Book returned successfully", result);
        assertTrue(book1.isAvailable());
    }

    @Test
    public void testBorrowingStatusActive() {
        library.borrowBook("M001", "B001");
        BorrowRecord record = member1.getBorrowHistory().get(0);
        String status = record.getBorrowingStatus();
        assertTrue(status.contains("ACTIVE"));
        assertTrue(status.contains("days remaining"));
    }

    @Test
    public void testMultipleBorrows() {
        library.borrowBook("M001", "B001");
        library.borrowBook("M001", "B002");
        assertEquals(2, member1.getBorrowHistory().size());
    }

    @Test
    public void testMemberBorrowHistory() {
        library.borrowBook("M001", "B001");
        library.borrowBook("M001", "B002");
        library.borrowBook("M002", "B002");

        assertEquals(2, member1.getBorrowHistory().size());
        assertEquals(1, member2.getBorrowHistory().size());
    }
}
