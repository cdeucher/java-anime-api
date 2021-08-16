package br.com.company.service;

import br.com.company.entity.Character;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {


    public void processImportCharatcters(List<Character> Characters){
        List<Character> listOfCharacters = new ArrayList<>();
        for (Object character : Characters) {
            listOfCharacters.add( new Character(character) );
        }
    }
}
