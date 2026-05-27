public class Librarian extends Person {
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