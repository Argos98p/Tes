package com.bezkoder.spring.security.mongodb.serviceTriplestore;

import com.bezkoder.spring.security.mongodb.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Triplestore {

    static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public static String generateString( User user){
        return "prefix : <http://turis-ucuenca/>\n"
                + "prefix foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "prefix myusers: <http://turis-ucuenca/user/>\n"
                + "base  <http://turis-ucuenca/>\n"
                + "INSERT {\n"
                + "myusers:"+user.getId() +"  a  foaf:Person;\n"
                +  "foaf:name \""+  user.getUsername()+"\" ;\n"
                + "  foaf:mbox <" + user.getEmail()+"> ;\n"
                +  "foaf:nick \""+  user.getUsername()+"\" ;\n"
                + "foaf:depiction  <adfadsf.com/user.png>.\n"
                + " } WHERE { }";
    }

    public static ResponseEntity<?>  saveInTripleStore(User user){
        String url = "https://sd-e3dfa127.stardog.cloud:5820/Turismo2";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/sparql-update");
        map.put("Authorization", getBasicAuthenticationHeader("ricardo.jarro98@ucuenca.edu.ec", "Chocolate619@"));

        headers.setAll(map);

        String query = generateString(user);



        HttpEntity<?> request = new HttpEntity<>(query, headers);

        return new RestTemplate().postForEntity(url+"/update", request, String.class);

    }
}
