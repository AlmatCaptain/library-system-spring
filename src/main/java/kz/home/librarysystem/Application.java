package kz.home.librarysystem;

import kz.home.librarysystem.controller.AuthorController;
import kz.home.librarysystem.controller.BookController;
import kz.home.librarysystem.controller.GenreController;
import kz.home.librarysystem.controller.MemberController;
import kz.home.librarysystem.model.Author;
import kz.home.librarysystem.model.Book;
import kz.home.librarysystem.model.Genre;
import kz.home.librarysystem.model.Member;
import kz.home.librarysystem.repository.GenreRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Application {
    private Scanner scanner = new Scanner(System.in);

    void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        BookController bookController = context.getBean("bookController", BookController.class);
        AuthorController authorController = context.getBean("authorController", AuthorController.class);
        GenreController genreController = context.getBean("genreController", GenreController.class);
        MemberController memberController = context.getBean("memberController", MemberController.class);

        while (true) {
            System.out.println("1. Show all books\n" +
                    "2. Show available books\n" +
                    "3. Show requested books\n" +
                    "4. Show overdue books\n" +
                    "5. Search Books\n" +
                    "6. Add new Books\n" +
                    "7. Log in ( member )\n" +
                    "8. Add new member\n" +
                    "9. Issue Book");

            int key = scanner.nextInt();

            switch (key) {
                case 1:
                    System.out.println(bookController.getAllBooks());
                    break;
                case 2:
                    System.out.println(bookController.getBooksByStatus(Book.BookStatus.AVAILABLE));
                    break;
                case 3:
                    System.out.println(bookController.getBooksByStatus(Book.BookStatus.REQUESTED));
                    break;
                case 4:
                    System.out.println(bookController.getBooksByStatus(Book.BookStatus.OVERDUE));
                    break;
                case 5:
                    System.out.println("1. Search by name\n" +
                            "2. Search by author");
                    int x = scanner.nextInt();

                    if (x == 1) {
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        System.out.println(bookController.findByTitle(name));
                        break;
                    } else if (x == 2) {
                        List<Author> authors = authorController.findAll();
                        for (Author a :
                                authors) {
                            System.out.println(a);
                        }

                        System.out.println(bookController.findByAuthor(authors.get(scanner.nextInt())));
                        break;
                    }
                case 6:
                    List<Book> books = new ArrayList<>();
                    while (true) {
                        System.out.println("If you done adding books press 0 another to \n" +
                                "Add book: ");
                        if (scanner.nextInt() == 0)
                            break;
                        System.out.println("Title: ");
                        scanner.nextLine();
                        String title = scanner.nextLine();
                        System.out.println(genreController.findAll());
                        System.out.println("Genre id: ");
                        Genre genre = genreController.findById(scanner.nextLong());
                        System.out.println(authorController.findAll());
                        System.out.println("Authors list find by id: ");
                        List<Author> authors = new ArrayList<>();
                        while (true) {
                            System.out.println("if done enter 0 else Enter author id: ");
                            Long s = scanner.nextLong();
                            if (s == 0)
                                break;
                            authors.add(authorController.findById(s));
                        }
                        books.add(new Book(title, Book.BookStatus.AVAILABLE, genre, authors));

                    }
                    bookController.addBooks(books);
                    break;
                case 7:
                    System.out.println(memberController.findAll());
                    Member m = memberController.getMemberById(scanner.nextLong());
                    while (true) {
                        System.out.println("1. Show borrowed books\n" +
                                "2. Return book\n" +
                                "3. Exit");
                        int k = scanner.nextInt();
                        if(k==3) break;
                        switch (k) {
                            case 1:
                                System.out.println(m.getBorrowedBooks());
                                break;
                            case 2:
                                System.out.println(m.getBorrowedBooks());
                                bookController.returnBookToLib(m.getBorrowedBooks().get(scanner.nextInt()));
                                break;
                        }
                    }
                case 8:
                    System.out.println("Member name:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    memberController.addMember(new Member(name));
                    break;
                case 9:
                    Map<Long,Long> map = bookController.getRequestedBooks();
                    System.out.println(map);
                    Long mapKey = scanner.nextLong();
                    System.out.println("Enter due date like(yyyy-mm-dd)");
                    String s = scanner.nextLine();
                    bookController.issueBook(mapKey,map.get(mapKey),LocalDate.parse(s));
                    break;
            }


        }
    }
}
