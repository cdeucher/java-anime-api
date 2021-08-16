package br.com.company.util;

import br.com.company.entity.Character;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {

    public static List<Character> importCharacterFromRestApi() throws JsonProcessingException {
        Client client = ClientBuilder.newBuilder().build();
        Response response = client.target("https://api.jikan.moe/v3/manga/1/characters")
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();

        //System.out.println("status: " + response.getStatus());
        //System.out.println("headers: " + response.getHeaders());
        //System.out.println("body:" + resp);

        String resp = response.readEntity(String.class);
        HashMap responseMap = new ObjectMapper().readValue(resp, HashMap.class);

        return (ArrayList)responseMap.get("characters");
    }



}
