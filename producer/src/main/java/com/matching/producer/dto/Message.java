package com.matching.producer.dto;


import java.util.List;

import lombok.Data;

@Data
public class Message{
    
    String id;
    String customerNumber;
    List<String> customerNumberList;
}
