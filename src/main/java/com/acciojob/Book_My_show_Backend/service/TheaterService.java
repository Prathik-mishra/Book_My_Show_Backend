package com.acciojob.Book_My_show_Backend.service;

import com.acciojob.Book_My_show_Backend.Enums.SeatType;
import com.acciojob.Book_My_show_Backend.Repository.TheaterRepository;
import com.acciojob.Book_My_show_Backend.Repository.TheaterSeatsRepository;
import com.acciojob.Book_My_show_Backend.Requests.AddTheaterRequest;
import com.acciojob.Book_My_show_Backend.Requests.AddTheaterSeatsRequest;
import com.acciojob.Book_My_show_Backend.model.Theater;
import com.acciojob.Book_My_show_Backend.model.TheaterSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatsRepository theaterSeatsRepository;
    public String addTheater(AddTheaterRequest theaterRequest){
        Theater theater = Theater.builder()
                .noOfScreens(theaterRequest.getNoOfScreens())
                .name(theaterRequest.getName())
                .address(theaterRequest.getAddress())
                .build();

        theater = theaterRepository.save(theater);
        return "Theater has been saved to the DB with theaterId "+theater.getTheaterId();
    }

    public String associateSeats(AddTheaterSeatsRequest theaterSeatsRequest){

        int theaterId = theaterSeatsRequest.getTheaterId();
        int noOfClassicSeats = theaterSeatsRequest.getNoOfClassicSeats();
        int noOfPremiumSeats = theaterSeatsRequest.getNoOfPremiumSeats();
        List<TheaterSeat> theaterSeatList = new ArrayList<>();

        Theater theater = theaterRepository.findById(theaterId).get();

        //assuming we only have 5 columns in our theater

        //for classic seats:
        int noOfRowsOfClassicSeats = noOfClassicSeats/5;
        int noOfSeatsInLastRow = noOfClassicSeats%5;
        int row;
        for(row=1; row<=noOfClassicSeats; row++){
            for(int j=0; j<5; j++){
                char ch = (char)('A' + j);

                String seatNo = "" + row + ch;

                TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                        .seatType(SeatType.CLASSIC)
                        .theater(theater)
                        .build();

                theaterSeatList.add(theaterSeat);
            }
        }

        //for last row:
        for(int j=0; j<noOfSeatsInLastRow; j++){
            char ch = (char)('A' + j);

            String seatNo = "" + row + ch;

            TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        //for premium seats:
        int noOfRowsOfPremiumSeats = noOfClassicSeats/5;
        noOfSeatsInLastRow = noOfPremiumSeats%5;
        int currentRow = row+1;
        for(row = currentRow; row<=noOfRowsOfPremiumSeats+currentRow-1; row++){
            for(int j=0; j<5; j++){
                char ch = (char)('A' + j);

                String seatNo = "" + row + ch;

                TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                        .seatType(SeatType.PREMIUM)
                        .theater(theater)
                        .build();

                theaterSeatList.add(theaterSeat);
            }
        }

        //for last row:
        for(int j=0; j<noOfSeatsInLastRow; j++){
            char ch = (char)('A' + j);

            String seatNo = "" + row + ch;

            TheaterSeat theaterSeat = TheaterSeat.builder().seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        theaterSeatsRepository.saveAll(theaterSeatList);
        return "The theater seats have been associated";
    }
}
