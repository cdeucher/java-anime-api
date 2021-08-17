package br.com.company.service;

import br.com.company.entity.Character;
import br.com.company.entity.dto.CharacterDto;
import br.com.company.repository.CharacterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterDao dao;

    public void processListOfCharacters(List<Character> listOfCharacters){
        for (Object character : listOfCharacters) {
            dao.save( CharacterDto.convertLinkedToCharacter(character) );
        }
    }
}
