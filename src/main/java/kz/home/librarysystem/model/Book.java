package kz.home.librarysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    public enum BookStatus{
        REQUESTED,
        ISSUED,
        AVAILABLE,
        OVERDUE
    }

    @Column(name = "genre_id")
    private Long genreId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private Genre genre;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")}
    )
    private List<Author> authorList;

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookTransaction> issuedBooks;

    public Book() {
    }

    public Book(String title, BookStatus bookStatus, Genre genre, List<Author> authorList) {
        this.title = title;
        this.bookStatus = bookStatus;
        this.genre = genre;
        this.authorList = authorList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Genre getGenre() {
        return genre;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<BookTransaction> getIssuedBooks() {
        return issuedBooks;
    }

    public void setIssuedBooks(List<BookTransaction> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    @Override
    public String toString() {
        return "\nBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bookStatus=" + bookStatus +
                ", genre=" + genre +
                ", authorList=" + authorList +
                "}";
    }
}
