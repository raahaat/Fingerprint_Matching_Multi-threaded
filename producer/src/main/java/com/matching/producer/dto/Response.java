package com.matching.producer.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Response {
    
    String message;
    int status;
    Message data;
}
