package com.manthan.rediskafkaelasticsearch.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendKafkaMessageRequestDTO {
    private String topic;
    private String message;
}
