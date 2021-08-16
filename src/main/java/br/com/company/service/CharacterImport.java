package br.com.company.service;

import br.com.company.entity.Character;
import br.com.company.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class CharacterImport {

    public static void main(String[] args) throws JsonProcessingException {
        CharacterService service = new CharacterService();

        List<Character> listOfCharacters = Util.importCharacterFromRestApi();
        service.processImportCharatcters(listOfCharacters);
    }
}
