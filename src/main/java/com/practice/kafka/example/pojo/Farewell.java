package com.practice.kafka.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Farewell {

    private String message;
    private Integer remainingMinutes;


}
