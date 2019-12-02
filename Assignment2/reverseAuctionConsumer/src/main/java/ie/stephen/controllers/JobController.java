package ie.stephen.controllers;

import ie.stephen.model.Job;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
public class JobController {

    @GetMapping("/activeJobs")
    public String viewJobs(Model model)
    {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/activeJobs";
        HttpHeaders headers = createHeaders("admin@admin.com", "admin");

        ParameterizedTypeReference<List<Job>> listOfJobs = new ParameterizedTypeReference<>(){};
        ResponseEntity<List<Job>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<>(headers), listOfJobs );
        List<Job> activeJobs = responseEntity.getBody();

        model.addAttribute("activeJobs", activeJobs);
        return "activeJobs";
    }

    private HttpHeaders createHeaders(String email, String password) {
        return new HttpHeaders() { {
            String auth = email + ":" + password;

            byte[] encodeStringIntoBytes = auth.getBytes(StandardCharsets.UTF_8);
            byte[] encodedAuth = Base64.encodeBase64(encodeStringIntoBytes);

            String authHeaders = "Basic " + new String( encodedAuth);
            set(HttpHeaders.AUTHORIZATION, authHeaders);
        }};
    }
}
