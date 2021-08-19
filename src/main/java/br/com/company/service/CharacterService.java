package br.com.company.service;

import br.com.company.entity.Character;
import br.com.company.repository.CharacterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CharacterService {

    @Autowired
    private CharacterDao dao;

    public void processListOfCharacters(Set<Character> listOfCharacters){
        for(Character character : listOfCharacters){
            dao.save(character);
        }
    }

}
