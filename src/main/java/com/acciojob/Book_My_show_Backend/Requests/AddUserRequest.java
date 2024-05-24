package com.acciojob.Book_My_show_Backend.Requests;

import lombok.Data;

@Data
public class AddUserRequest {
    private String name;

    private Integer age;

    private String emailId;

    private String mobNo;
}
