package com.practice.kafka.example.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Greeting {

    private String msg;
    private String name;
}




