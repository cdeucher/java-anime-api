package br.com.company.service;

import br.com.company.entity.Character;
import br.com.company.repository.CharacterDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@SpringBootTest
public class CharacterServiceTest {

    @Test
    void whenProcessListOfCharacters_ShouldSaveTheListOfCharacters() {
         given()
         .characterProvider( character -> {
             character.mal_id = 1;
             character.url = "localhost";
             character.image_url = "localhost";
             character.name = "Ayu";
             character.role = "main";
         })
         .characterProvider( character -> {
             character.mal_id = 2;
             character.url = "localhost";
             character.image_url = "localhost";
             character.name = "Uyo";
             character.role = "main";
         })
         .whenPerformSaveCharacter()
         .then()
            .saveTheListOfMangas();
    }
    @Test
    void whenGetMangas_ShouldListMangas(){
         given()
         .characterProvider( character -> {
             character.mal_id = 1;
             character.url = "localhost";
             character.image_url = "localhost";
             character.name = "Ayu";
             character.role = "main";
         })
         .whenPerformListOfCharacter()
         .then()
            .getListOfCharacter();
    }

    private DSL given(){
        return new DSL();
    }
    private static class DSL{

        Set<Character> characterProvider;
        CharacterService service;
        CharacterDao characterDao;

        public DSL() {
            this.characterProvider = new HashSet<>();
            this.characterDao = Mockito.mock(CharacterDao.class);
            this.service = new CharacterService(characterDao);
        }

        public DSL characterProvider(Consumer <CharacterProviderData> character){
            final CharacterProviderData provider = new CharacterProviderData();
            character.accept(provider);
            characterProvider.add(provider);
            return this;
        }

        public ThenDSL whenPerformSaveCharacter() {
            service.processListOfCharacters(characterProvider);
            return new ThenDSL();
        }

        public ThenDSL whenPerformListOfCharacter() {
            List<Character> list = new ArrayList<>(characterProvider);
            Mockito.when(characterDao.findAll()).thenReturn(list);
            int numberOfCharacter = service.getCharacter().size();
            return new ThenDSL(numberOfCharacter);
        }

        public class ThenDSL{

            int numberOfCharacter;

            public ThenDSL() {
            }

            public ThenDSL(int numberOfCharacter){
                this.numberOfCharacter = numberOfCharacter;
            }

            public ThenDSL then(){
                return this;
            }

            public void saveTheListOfMangas(){
                Mockito.verify(characterDao, Mockito.times(2)).save(Mockito.any());
            }

            public void getListOfCharacter() {
                assertEquals( characterProvider.size(),numberOfCharacter);
            }
        }

    }

    private static class CharacterProviderData extends Character {
        private int mal_id;
        private String url;
        private String image_url;
        private String name;
        private String role;
    }


}
