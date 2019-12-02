package ie.stephen.controllers;

import ie.stephen.forms.FindBidsForm;
import ie.stephen.model.Bid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
public class BidController {

    @GetMapping(value= {"/findBids"})
    public String bid(Model model)
    {
        model.addAttribute("findBidsForm", new FindBidsForm());
        return "findBids";
    }

    @PostMapping("/findBids")
    public String bid(@Valid FindBidsForm findBidsForm, BindingResult binding, RedirectAttributes redirectAttributes)
    {
        if (binding.hasErrors())
            return "findBids";

        return "redirect:/viewBids/" + findBidsForm.getEmail();
    }

    @GetMapping("/viewBids/{email}")
    public String viewJobs(Model model, @PathVariable("email") String email)
    {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/viewBids/" + email;
        HttpHeaders headers = createHeaders("admin@admin.com", "admin");

        ParameterizedTypeReference<List<Bid>> listOfBids = new ParameterizedTypeReference<>(){};
        ResponseEntity<List<Bid>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<>(headers), listOfBids );

        List<Bid> bids = responseEntity.getBody();
        model.addAttribute("bids", bids);
        return "viewBids";
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
