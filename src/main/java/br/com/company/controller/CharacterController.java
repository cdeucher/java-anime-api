package br.com.company.controller;

import br.com.company.entity.Character;
import br.com.company.service.CharacterService;
import br.com.company.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    CharacterService service;

    @ResponseBody
    @RequestMapping("/import")
    public String importFromApiRest(){

        List<Character> listOfCharacters = Util.importCharacterFromRestApi();
        service.processListOfCharacters(listOfCharacters);

        return "";
    }

}
