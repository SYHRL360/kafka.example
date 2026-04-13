package com.practice.kafka.example.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Farewell {

    private String message;
    private Integer remainingMinutes;


}
