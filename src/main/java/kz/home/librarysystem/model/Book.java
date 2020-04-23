package kz.home.librarysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"issuedBooks"})
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    public enum BookStatus {
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


    public Book(String title, BookStatus bookStatus, Genre genre, List<Author> authorList) {
        this.title = title;
        this.bookStatus = bookStatus;
        this.genre = genre;
        this.authorList = authorList;
    }


}
