package com.devmind.slack;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 27/01/16.
 */
@RestController
public class SlackMessageSender {

    @Value("${slack.services.incoming}")
    private String slackServiceIncomingUrl;

    @Autowired
    private ObjectMapper objectMapper;


    @RequestMapping(value = "/slack/{message}")
    public ResponseEntity<String> hello(@PathVariable(value = "message") String message) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();

        SlackMessage slackMessage = new SlackMessage()
                .setChannel("random")
                .setText(message)
                .setUsername("guillaume")
                .setIcon_url("http://dev-mind.fr/logo/logo_48.png");

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(slackMessage), headers);
            restTemplate.exchange(slackServiceIncomingUrl, HttpMethod.POST, request, String.class);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body("Message sent");
    }
}
