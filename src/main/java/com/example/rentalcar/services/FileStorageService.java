package com.example.rentalcar.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;


@Service
public class FileStorageService {

    @Autowired
    private Base64 encoder;

    @Value("${imgbb.api.key}")
    private String apiKey;

    public String uploadImage(MultipartFile file){

        try {

            sendToServer(file);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    };

    private String encodeFileToBase64Binary(MultipartFile file)
            throws IOException {

        byte[] encoded = encoder.encode(file.getBytes());
        return new String(encoded);
    }

    private void sendToServer(MultipartFile file) throws IOException {
        String uri ="https://api.imgbb.com/1/upload";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", file);
        body.add("key", apiKey);


        HttpEntity<?> entity = new HttpEntity<>(body, headers);


        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        System.out.println("Response code: " + response.getStatusCode());
    }

}
