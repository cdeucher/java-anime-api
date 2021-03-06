package br.com.company.util;

import br.com.company.entity.Character;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util {

    public static ArrayList importCharacterFromRestApi(String urlEndPoint, String apiIndex){

        Client client = ClientBuilder.newBuilder().build();
        Response response = client.target(urlEndPoint)
                .request(MediaType.TEXT_PLAIN_TYPE)
                .get();

        System.out.println(String.format("Import Status: %s, %s", response.getStatus(), apiIndex ));
        //System.out.println("headers: " + response.getHeaders());
        //System.out.println("body:" + resp);

        try {
            String resp = response.readEntity(String.class);
            HashMap responseMap = new ObjectMapper().readValue(resp, HashMap.class);
            return (ArrayList)responseMap.get(apiIndex);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        importCharacterFromRestApi("https://api.jikan.moe/v3/search/anime?q=&order_by=members&sort=desc&page=1", "results");
    }

}
