package com.acciojob.Book_My_show_Backend.service;

import com.acciojob.Book_My_show_Backend.Repository.MovieRepository;
import com.acciojob.Book_My_show_Backend.Repository.ShowRepository;
import com.acciojob.Book_My_show_Backend.Repository.ShowSeatRepository;
import com.acciojob.Book_My_show_Backend.Repository.TheaterRepository;
import com.acciojob.Book_My_show_Backend.Requests.AddShowRequest;
import com.acciojob.Book_My_show_Backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShow(AddShowRequest showRequest){

        Movie movie = movieRepository.findMovieByMovieName(showRequest.getMovieName());
        Theater theater = theaterRepository.findById(showRequest.getTheaterId()).get();

        //we can add messages for validations:

        Show show = Show.builder()
                .showDate(showRequest.getShowDate())
                .showTime(showRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();

        show = showRepository.save(show);

        //Associate the corresponding show seats
        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheaterSeat theaterSeat : theaterSeatList){
            ShowSeat showSeat = ShowSeat.builder().seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .isBooked(Boolean.FALSE)
                    .isFoodAttached(Boolean.FALSE)
                    .show(show).build();

            showSeatList.add(showSeat);
        }

        showSeatRepository.saveAll(showSeatList);

        return "The show has been saved to the db with the showId "+show.getShowId();
    }
}
