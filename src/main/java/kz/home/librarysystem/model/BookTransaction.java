package kz.home.librarysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class BookTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "due_date")
    private LocalDate dueDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    public BookTransaction() {
    }

    public BookTransaction(Long bookId, Long memberId, LocalDate issueDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookTransaction{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
