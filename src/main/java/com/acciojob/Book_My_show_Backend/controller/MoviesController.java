package com.acciojob.Book_My_show_Backend.controller;

import com.acciojob.Book_My_show_Backend.Requests.AddMovieRequest;
import com.acciojob.Book_My_show_Backend.Requests.UpdateMovieRequest;
import com.acciojob.Book_My_show_Backend.service.MovieService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movie")
public class MoviesController {

    @Autowired
    private MovieService movieService;
    @PostMapping("addMovie")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest addMovieRequest){

        String response = movieService.addMovie(addMovieRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("updateMovie")
    public ResponseEntity updateMovie(@RequestBody UpdateMovieRequest updateMovieRequest){
        String response = movieService.updateMovie(updateMovieRequest);
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
