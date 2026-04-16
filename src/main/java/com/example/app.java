package com.example;

import java.time.LocalDate;
import java.util.*;


class Book {
    private String id;
    private String title;
    private String author;
    private boolean available;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}


class BorrowRecord {
    private String bookId;
    private String memberId;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private static final int BORROW_DAYS = 14;

    public BorrowRecord(String bookId, String memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = LocalDate.now();
        this.returnDate = borrowDate.plusDays(BORROW_DAYS);
    }

    public String getBookId() { return bookId; }
    public String getMemberId() { return memberId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public String getBorrowingStatus() {
        LocalDate today = LocalDate.now();
        if (today.isAfter(returnDate)) {
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(returnDate, today);
            return "OVERDUE by " + daysOverdue + " days";
        } else {
            long daysRemaining = java.time.temporal.ChronoUnit.DAYS.between(today, returnDate);
            return "ACTIVE - " + daysRemaining + " days remaining";
        }
    }
}


class Member {
    private String memberId;
    private String name;
    private List<BorrowRecord> borrowHistory;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowHistory = new ArrayList<>();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<BorrowRecord> getBorrowHistory() { return borrowHistory; }

    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }
}


public class LibraryManagementSystem {
    private Map<String, Book> books;
    private Map<String, Member> members;
}
