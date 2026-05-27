/**
 * Complete Library Management System (All-in-One File)
 * Demonstrates OOP Concepts:
 * - Inheritance (Student, Librarian extends Person)
 * - Polymorphism (displayDetails() method overriding)
 * - Encapsulation (private data members, public getters/setters)
 * - Abstraction (LibraryOperations interface, Person abstract class)
 * - Exception Handling (custom exceptions)
 * - Constructors (initialization)
 */

import java.util.*;

// ==================== EXCEPTION CLASSES ====================
class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super(message);
    }
}

class BookAlreadyIssuedException extends Exception {
    public BookAlreadyIssuedException(String message) {
        super(message);
    }
}

class StudentBorrowLimitExceededException extends Exception {
    public StudentBorrowLimitExceededException(String message) {
        super(message);
    }
}

class InvalidStudentException extends Exception {
    public InvalidStudentException(String message) {
        super(message);
    }
}

// ==================== BOOK CLASS ====================
class Book {
    private int bookId;
    private String bookName;
    private String authorName;
    private String availabilityStatus;
    
    public Book(int bookId, String bookName, String authorName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.availabilityStatus = "Available";
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public String getBookName() {
        return bookName;
    }
    
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
    public String getAuthorName() {
        return authorName;
    }
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }
    
    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }
    
    public void displayBookDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Book Name: " + bookName);
        System.out.println("Author Name: " + authorName);
        System.out.println("Availability Status: " + availabilityStatus);
        System.out.println("-----------------------------------");
    }
}

// ==================== PERSON ABSTRACT CLASS ====================
abstract class Person {
    private int id;
    private String name;
    
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public abstract void displayDetails();
}

// ==================== STUDENT CLASS ====================
class Student extends Person {
    private String department;
    private List<Book> borrowedBooks;
    
    public Student(int id, String name, String department) {
        super(id, name);
        this.department = department;
        this.borrowedBooks = new ArrayList<>();
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public void addBorrowedBook(Book book) {
        borrowedBooks.add(book);
    }
    
    public void removeBorrowedBook(Book book) {
        borrowedBooks.remove(book);
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n========== STUDENT DETAILS ==========");
        System.out.println("Student ID: " + getId());
        System.out.println("Student Name: " + getName());
        System.out.println("Department: " + department);
        System.out.println("Books Borrowed: " + borrowedBooks.size());
        if (borrowedBooks.size() > 0) {
            System.out.println("Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println("  - " + book.getBookName() + " by " + book.getAuthorName());
            }
        }
        System.out.println("====================================\n");
    }
}

// ==================== LIBRARIAN CLASS ====================
class Librarian extends Person {
    private String designation;
    
    public Librarian(int id, String name, String designation) {
        super(id, name);
        this.designation = designation;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    @Override
    public void displayDetails() {
        System.out.println("\n========== LIBRARIAN DETAILS ==========");
        System.out.println("Librarian ID: " + getId());
        System.out.println("Librarian Name: " + getName());
        System.out.println("Designation: " + designation);
        System.out.println("======================================\n");
    }
}

// ==================== LIBRARY OPERATIONS INTERFACE ====================
interface LibraryOperations {
    void addBook(Book book);
    void displayAllBooks();
    void issueBook(int bookId, int studentId) throws BookNotFoundException, BookAlreadyIssuedException, StudentBorrowLimitExceededException, InvalidStudentException;
    void returnBook(int bookId, int studentId) throws BookNotFoundException, InvalidStudentException;
}

// ==================== LIBRARY CLASS ====================
class Library implements LibraryOperations {
    private List<Book> books;
    private List<Student> students;
    private Librarian librarian;
    private final int MAX_BOOKS_PER_STUDENT = 3;
    
    public Library(Librarian librarian) {
        this.books = new ArrayList<>();
        this.students = new ArrayList<>();
        this.librarian = librarian;
    }
    
    @Override
    public void addBook(Book book) {
        books.add(book);
        System.out.println("✓ Book added successfully: " + book.getBookName());
    }
    
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("✓ Student registered successfully: " + student.getName());
    }
    
    @Override
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("\n*** No books in the library ***\n");
            return;
        }
        System.out.println("\n========== ALL BOOKS IN LIBRARY ==========");
        for (Book book : books) {
            book.displayBookDetails();
        }
    }
    
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\n*** No students registered ***\n");
            return;
        }
        System.out.println("\n========== ALL REGISTERED STUDENTS ==========");
        for (Student student : students) {
            System.out.println("ID: " + student.getId() + " | Name: " + student.getName() + " | Dept: " + student.getDepartment());
        }
        System.out.println("==========================================\n");
    }
    
    private Book findBookById(int bookId) throws BookNotFoundException {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with ID " + bookId + " not found!");
    }
    
    private Student findStudentById(int studentId) throws InvalidStudentException {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        throw new InvalidStudentException("Student with ID " + studentId + " not found!");
    }
    
    @Override
    public void issueBook(int bookId, int studentId) 
            throws BookNotFoundException, BookAlreadyIssuedException, 
                   StudentBorrowLimitExceededException, InvalidStudentException {
        
        Book book = findBookById(bookId);
        Student student = findStudentById(studentId);
        
        if (!book.getAvailabilityStatus().equals("Available")) {
            throw new BookAlreadyIssuedException("Book '" + book.getBookName() + "' is already issued!");
        }
        
        if (student.getBorrowedBooks().size() >= MAX_BOOKS_PER_STUDENT) {
            throw new StudentBorrowLimitExceededException("Student has already borrowed maximum " + MAX_BOOKS_PER_STUDENT + " books!");
        }
        
        book.setAvailabilityStatus("Issued");
        student.addBorrowedBook(book);
        System.out.println("✓ Book '" + book.getBookName() + "' issued to student " + student.getName());
    }
    
    @Override
    public void returnBook(int bookId, int studentId) 
            throws BookNotFoundException, InvalidStudentException {
        
        Book book = findBookById(bookId);
        Student student = findStudentById(studentId);
        
        if (!student.getBorrowedBooks().contains(book)) {
            throw new BookNotFoundException("Student " + student.getName() + " did not borrow this book!");
        }
        
        book.setAvailabilityStatus("Available");
        student.removeBorrowedBook(book);
        System.out.println("✓ Book '" + book.getBookName() + "' returned by student " + student.getName());
    }
    
    public void searchBookByAuthor(String authorName) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthorName().equalsIgnoreCase(authorName)) {
                foundBooks.add(book);
            }
        }
        
        if (foundBooks.isEmpty()) {
            System.out.println("\n*** No books found by author: " + authorName + " ***\n");
        } else {
            System.out.println("\n========== BOOKS BY " + authorName.toUpperCase() + " ==========");
            for (Book book : foundBooks) {
                book.displayBookDetails();
            }
        }
    }
    
    public void getLibrarianInfo() {
        librarian.displayDetails();
    }
    
    public Librarian getLibrarian() {
        return librarian;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public List<Book> getBooks() {
        return books;
    }
}

// ==================== MAIN CLASS ====================
public class LibraryManagementSystemAll {
    static Scanner scanner = new Scanner(System.in);
    static Library library;
    static Librarian librarian;
    
    public static void main(String[] args) {
        initializeSystem();
        displayMainMenu();
    }
    
    private static void initializeSystem() {
        librarian = new Librarian(1001, "Mr. Rajesh Kumar", "Senior Librarian");
        library = new Library(librarian);
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║  Welcome to College Library Management System║");
        System.out.println("╚════════════════════════════════════════════╝\n");
    }
    
    private static void displayMainMenu() {
        while (true) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Book Management");
            System.out.println("2. Student Management");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Library Info");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    bookManagementMenu();
                    break;
                case 2:
                    studentManagementMenu();
                    break;
                case 3:
                    issueBookMenu();
                    break;
                case 4:
                    returnBookMenu();
                    break;
                case 5:
                    viewLibraryInfo();
                    break;
                case 6:
                    System.out.println("\nThank you for using Library Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("✗ Invalid choice! Please try again.");
            }
        }
    }
    
    private static void bookManagementMenu() {
        while (true) {
            System.out.println("\n========== BOOK MANAGEMENT ==========");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Books by Author");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    library.displayAllBooks();
                    break;
                case 3:
                    searchBookByAuthor();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("✗ Invalid choice! Please try again.");
            }
        }
    }
    
    private static void addBook() {
        System.out.print("\nEnter Book ID: ");
        int bookId = getIntInput();
        
        scanner.nextLine();
        System.out.print("Enter Book Name: ");
        String bookName = scanner.nextLine().trim();
        
        System.out.print("Enter Author Name: ");
        String authorName = scanner.nextLine().trim();
        
        if (bookName.isEmpty() || authorName.isEmpty()) {
            System.out.println("✗ Book name and author cannot be empty!");
            return;
        }
        
        Book book = new Book(bookId, bookName, authorName);
        library.addBook(book);
    }
    
    private static void searchBookByAuthor() {
        scanner.nextLine();
        System.out.print("\nEnter Author Name: ");
        String authorName = scanner.nextLine().trim();
        library.searchBookByAuthor(authorName);
    }
    
    private static void studentManagementMenu() {
        while (true) {
            System.out.println("\n========== STUDENT MANAGEMENT ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student Details");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    library.displayAllStudents();
                    break;
                case 3:
                    viewStudentDetails();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("✗ Invalid choice! Please try again.");
            }
        }
    }
    
    private static void addStudent() {
        System.out.print("\nEnter Student ID: ");
        int studentId = getIntInput();
        
        scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine().trim();
        
        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();
        
        if (studentName.isEmpty() || department.isEmpty()) {
            System.out.println("✗ Student name and department cannot be empty!");
            return;
        }
        
        Student student = new Student(studentId, studentName, department);
        library.addStudent(student);
    }
    
    private static void viewStudentDetails() {
        System.out.print("\nEnter Student ID: ");
        int studentId = getIntInput();
        
        try {
            Student student = findStudentById(studentId);
            student.displayDetails();
        } catch (InvalidStudentException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }
    
    private static void issueBookMenu() {
        System.out.print("\nEnter Student ID: ");
        int studentId = getIntInput();
        
        System.out.print("Enter Book ID to Issue: ");
        int bookId = getIntInput();
        
        try {
            library.issueBook(bookId, studentId);
        } catch (BookNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (BookAlreadyIssuedException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (StudentBorrowLimitExceededException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (InvalidStudentException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }
    
    private static void returnBookMenu() {
        System.out.print("\nEnter Student ID: ");
        int studentId = getIntInput();
        
        System.out.print("Enter Book ID to Return: ");
        int bookId = getIntInput();
        
        try {
            library.returnBook(bookId, studentId);
        } catch (BookNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        } catch (InvalidStudentException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }
    
    private static void viewLibraryInfo() {
        System.out.println("\n========== LIBRARY INFORMATION ==========");
        library.getLibrarianInfo();
        System.out.println("Librarian is managing the library operations.");
    }
    
    private static Student findStudentById(int studentId) throws InvalidStudentException {
        for (Student student : library.getStudents()) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        throw new InvalidStudentException("Student with ID " + studentId + " not found!");
    }
    
    private static int getIntInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}