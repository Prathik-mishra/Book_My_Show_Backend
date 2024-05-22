package com.acciojob.Book_My_show_Backend.service;

import com.acciojob.Book_My_show_Backend.Enums.SeatType;
import com.acciojob.Book_My_show_Backend.Repository.ShowRepository;
import com.acciojob.Book_My_show_Backend.Repository.ShowSeatRepository;
import com.acciojob.Book_My_show_Backend.Repository.TicketRepository;
import com.acciojob.Book_My_show_Backend.Repository.UserRepository;
import com.acciojob.Book_My_show_Backend.Requests.BookTicketRequest;
import com.acciojob.Book_My_show_Backend.Responses.TicketResponse;
import com.acciojob.Book_My_show_Backend.model.Show;
import com.acciojob.Book_My_show_Backend.model.ShowSeat;
import com.acciojob.Book_My_show_Backend.model.Ticket;
import com.acciojob.Book_My_show_Backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;
    public String bookTicket(BookTicketRequest bookTicketRequest){

        //find the show Entity
        Show show = showRepository.findById(bookTicketRequest.getShowId()).get();

        //find the user Entity
        User user = userRepository.findById(bookTicketRequest.getUserId()).get();

        //Mark those seats as booked now and calculate total amount
        Integer totalAmount = 0;
        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList){

            String seatNo = showSeat.getSeatNo();
            if(bookTicketRequest.getRequestedSeats().contains(seatNo)){
                showSeat.setIsBooked(Boolean.TRUE);

                if(showSeat.getSeatType().equals(SeatType.CLASSIC)){
                    totalAmount = totalAmount + 100;
                }else{
                    totalAmount = totalAmount + 150;
                }
            }
        }

        // create the ticket entity and set the attributes:

        Ticket ticket = Ticket.builder().showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getName())
                .totalAmount(totalAmount)
                .bookedSeats(bookTicketRequest.getRequestedSeats().toString())
                .show(show)
                .user(user)
                .build();

        showSeatRepository.saveAll(showSeatList);
        ticket = ticketRepository.save(ticket);

        // save the ticket into db and return ticket entity (ticket response)
        return ticket.getTicketId();
    }

    public TicketResponse generateTicket(String ticketId){

        Ticket ticket = ticketRepository.findById(ticketId).get();

        TicketResponse ticketResponse = TicketResponse.builder().bookedSeats(ticket.getBookedSeats())
                .movieName(ticket.getMovieName())
                .showTime(ticket.getShowTime())
                .showDate(ticket.getShowDate())
                .theaterName(ticket.getTheaterName())
                .totalAmount(ticket.getTotalAmount())
                .build();

        return ticketResponse;
    }
}
