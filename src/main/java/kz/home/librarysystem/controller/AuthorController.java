package kz.home.librarysystem.controller;

import kz.home.librarysystem.model.Author;
import kz.home.librarysystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("")
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable("id") Long id) {
        return authorRepository.findById(id)
                               .get();
    }


}
