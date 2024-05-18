package com.acciojob.Book_My_show_Backend.Requests;

import lombok.Data;

@Data
public class AddTheaterSeatsRequest {
    //DTO (data transfer objects) are custom classes helping you take inputs from client
    private Integer theaterId;
    private Integer noOfClassicSeats;
    private Integer noOfPremiumSeats;
}
