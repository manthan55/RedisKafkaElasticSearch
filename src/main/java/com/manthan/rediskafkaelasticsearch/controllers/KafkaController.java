package com.manthan.rediskafkaelasticsearch.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manthan.rediskafkaelasticsearch.clients.KafkaProducerClient;
import com.manthan.rediskafkaelasticsearch.dtos.AuthorDTO;
import com.manthan.rediskafkaelasticsearch.dtos.SendKafkaMessageRequestDTO;
import com.manthan.rediskafkaelasticsearch.dtos.SendKafkaMessageResponseDTO;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponse;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponseFailure;
import com.manthan.rediskafkaelasticsearch.dtos.api.APIResponseSuccess;
import com.manthan.rediskafkaelasticsearch.exceptions.AuthorDoesNotExistException;
import com.manthan.rediskafkaelasticsearch.models.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private KafkaProducerClient kafkaProducerClient;
    private ObjectMapper objectMapper;

    public KafkaController(KafkaProducerClient kafkaProducerClient, ObjectMapper objectMapper) {
        this.kafkaProducerClient = kafkaProducerClient;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/sendMessage")
    public ResponseEntity<APIResponse> sendMessage(@RequestBody SendKafkaMessageRequestDTO requestDTO){
        APIResponse response = null;
        HttpStatus httpStatus = HttpStatus.OK;
        SendKafkaMessageResponseDTO responseDTO = new SendKafkaMessageResponseDTO();

        try{
            kafkaProducerClient.sendMessage(requestDTO.getTopic(), objectMapper.writeValueAsString(requestDTO.getMessage()));
            responseDTO.setStatus(true);
            response = new APIResponseSuccess<>(responseDTO);
        }
        catch(Exception ex){
            responseDTO.setStatus(false);
            response = new APIResponseFailure(ex);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(httpStatus).body(response);
    }
}
