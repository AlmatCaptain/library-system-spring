package kz.home.librarysystem.controller;

import kz.home.librarysystem.model.Genre;
import kz.home.librarysystem.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("")
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @GetMapping("/{id}")
    public Genre findById(@PathVariable("id") Long id) {
        return genreRepository.findById(id)
                              .get();
    }
}
