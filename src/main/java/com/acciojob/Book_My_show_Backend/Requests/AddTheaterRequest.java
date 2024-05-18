package com.acciojob.Book_My_show_Backend.Requests;

import lombok.Data;

@Data
public class AddTheaterRequest {
    private String name;

    private String address;

    private Integer noOfScreens;
}
