package com.practice.kafka.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Greeting {

    private String msg;
    private String name;
}




