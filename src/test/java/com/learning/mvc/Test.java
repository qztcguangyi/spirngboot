/*
 * Copyright (c) 2005, 2016, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.learning.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * desc
 *
 * @author Gavin Zhang
 * @created 2020/11/5
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        tesetPost();
        tesetGet();

    }

    public static void tesetGet() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/json";
        String result = restTemplate.getForObject(url,String.class);
        log.info("**********:"+result);
    }

    public static void tesetPost() {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/ps";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", "844072586@qq.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
        System.out.println(response.getBody());

    }

}
