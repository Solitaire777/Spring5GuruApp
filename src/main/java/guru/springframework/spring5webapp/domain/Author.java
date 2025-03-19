package guru.springframework.spring5webapp.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    //properties
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    //relational
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();


    //constructors
    public Author() {
    }
    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        //this.books = books; might add back later rmd Set<Book> books in args
    }


    //getters & setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;}
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBooks(Set<Book> books) {
        this.books = books;
    }
    public Set<Book> getBooks() {
        return books;
    }


    //generated equals, hashCode, and toString
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", books=" + books +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
