package com.example.rentalcar.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
public class FileStorageService {

    @Autowired
    private Base64 encoder;

    @Value("${imgbb.api.key}")
    private String apiKey;

    public String uploadImage(MultipartFile file){

        try {
            String image = encodeFileToBase64Binary(file);

            return sendToServer(image);
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

    private String sendToServer(String file){
        String uri ="https://api.imgbb.com/1/upload";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", file);
        body.add("key", apiKey);


        HttpEntity<?> entity = new HttpEntity<>(body, headers);


        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        if(response.getStatusCode().value() == 200){
            String jsonBody = response.getBody();

            return (String) convertFromJson(jsonBody).get("display_url");
        }
        return null;
    }

    private JSONObject convertFromJson(String json){
        try {
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(json);
            JSONArray array = new JSONArray();
            array.add(obj);

            JSONObject jsonObj = (JSONObject)array.get(0);
            return (JSONObject)jsonObj.get("data");
        }catch (ParseException pe){
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }

        return null;
    }

}
