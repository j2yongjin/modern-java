package basic.generictype;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by yjlee on 2018-10-14.
 */
public class SuperTypeTokenExample {

    public static void main(String[] args){

        RestTemplate restTemplate = new RestTemplate();

        // LinkedHashMap convert Error
//        List<SuperTypeTokenControllerExample.User> users = restTemplate.getForObject("http://localhost:8080",List.class);


        // Super Type Token 주요 예제
        List<SuperTypeTokenControllerExample.User> users = restTemplate.exchange("http://localhost:8080", HttpMethod.GET, null
                , new ParameterizedTypeReference<List<SuperTypeTokenControllerExample.User>>(){}).getBody();


        System.out.println(users);



    }
}
