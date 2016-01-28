package com.devmind.slack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 27/01/16.
 */
@RestController
public class SlackMessageSender {

    @Value("${slack.services.incoming}")
    private String slackServiceIncomingUrl;

    @RequestMapping(value = "/slack/{message}")
    public ResponseEntity<String> hello(@PathVariable(value = "message") String message) {

        RestTemplate restTemplate = new RestTemplate();

        SlackMessage slackMessage = new SlackMessage()
                .setChannel("general")
                .setContent(message)
                .setUsername("Dev-Mind")
                .setEmoji("ghost");

        try{
            restTemplate.postForLocation(slackServiceIncomingUrl, slackMessage);
        }
        catch (RuntimeException e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body("Message sent");
    }
}
