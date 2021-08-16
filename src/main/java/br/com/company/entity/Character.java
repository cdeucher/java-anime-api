package br.com.company.entity;

import java.util.LinkedHashMap;

public class Character {
    private int mal_id;
    private String url;
    private String image_url;
    private String name;
    private String role;

    public Character(Object character) {
        this.mal_id = (Integer) ((LinkedHashMap)character).get("mal_id");
        this.url = (String) ((LinkedHashMap)character).get("url");
        this.image_url = (String) ((LinkedHashMap)character).get("image_url");
        this.name = (String) ((LinkedHashMap)character).get("name");
        this.role = (String) ((LinkedHashMap)character).get("role");
    }
}
