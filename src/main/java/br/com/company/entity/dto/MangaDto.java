package br.com.company.entity.dto;

import br.com.company.entity.Manga;

import java.util.LinkedHashMap;

public class MangaDto {

    public static Manga convertLinkedToManga(Object manga){
        int mal_id = (Integer) ((LinkedHashMap)manga).get("mal_id");
        String url = (String) ((LinkedHashMap)manga).get("url");
        String image_url = (String) ((LinkedHashMap)manga).get("image_url");
        String name = (String) ((LinkedHashMap)manga).get("title");
        String role = (String) ((LinkedHashMap)manga).get("synopsis");
        String type = (String) ((LinkedHashMap)manga).get("type");
        Integer episodes = (Integer) ((LinkedHashMap)manga).get("episodes");

        return new Manga(mal_id, url, image_url, name, role, type, episodes);
    }

}