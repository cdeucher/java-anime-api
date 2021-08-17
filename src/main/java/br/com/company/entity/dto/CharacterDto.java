package br.com.company.entity.dto;

import br.com.company.entity.Character;

import java.util.LinkedHashMap;

public class CharacterDto {

    public static Character convertLinkedToCharacter(Object character) {
        int mal_id = (Integer) ((LinkedHashMap)character).get("mal_id");
        String url = (String) ((LinkedHashMap)character).get("url");
        String image_url = (String) ((LinkedHashMap)character).get("image_url");
        String name = (String) ((LinkedHashMap)character).get("name");
        String role = (String) ((LinkedHashMap)character).get("role");

        return new Character(mal_id, url, image_url, name, role);
    }
}
