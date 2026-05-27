import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
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
        System.out.println("=====================================\n");
    }
}