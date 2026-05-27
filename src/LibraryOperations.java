public interface LibraryOperations {
    void addBook(Book book);
    void displayAllBooks();
    void issueBook(int bookId, int studentId) throws BookNotFoundException, BookAlreadyIssuedException, StudentBorrowLimitExceededException, InvalidStudentException;
    void returnBook(int bookId, int studentId) throws BookNotFoundException, InvalidStudentException;
}