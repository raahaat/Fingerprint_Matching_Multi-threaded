package com.matching.producer.controller;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matching.producer.dto.Message;
import com.matching.producer.dto.Response;
import com.matching.producer.service.RabbitMQProducer;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    RabbitMQProducer producer;

    @GetMapping("test")
    public String test() {
        return "Test successful";
    }

    @PostMapping("send")
    public ResponseEntity<?> sendMessage(@RequestBody Message msg) {
        Response res = new Response();
        JSONObject obj = new JSONObject();
        UUID id = UUID.randomUUID();

        obj.put("id", id.toString());
        obj.put("customerNumber", msg.getCustomerNumber());
        obj.put("customerNumberList", msg.getCustomerNumberList());
        res.setMessage("Successfully sent.");
        res.setStatus(200);
        msg.setId(id.toString());
        res.setData(msg);
        producer.sendMessage(obj.toString());

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
