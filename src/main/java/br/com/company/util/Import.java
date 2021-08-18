package br.com.company.util;

import br.com.company.entity.Character;
import br.com.company.entity.Manga;
import br.com.company.entity.dto.CharacterDto;
import br.com.company.entity.dto.MangaDto;
import br.com.company.service.CharacterService;
import br.com.company.service.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class Import {

    @Autowired
    private MangaService mangaService;
    @Autowired
    private CharacterService charaService;

    public void importMangasToDatabse(){
        for(int page=1; page<2; page++)
            importMangasPerPage(page);

    }

    private void importMangasPerPage(int page){
        List<Manga> restOfMangas = Util.importCharacterFromRestApi("https://api.jikan.moe/v3/search/anime?q=&order_by=members&sort=desc&page="+page, "results");
        Set<Manga>  listOfMangas = new HashSet<>();

        for(Object manga : restOfMangas){
            listOfMangas.add( MangaDto.convertLinkedToManga(manga) );
        }
        mangaService.processListOfMangas(listOfMangas);
    }

    public void importCharacters(){
        List<Manga> mangas = mangaService.getMangas();
        for(Manga manga : mangas){
            importCharactersByManga(manga);
        }
    }


    public void importCharactersByManga(Manga manga){
        List<Character> restOfCharacters = Util.importCharacterFromRestApi("https://api.jikan.moe/v3/manga/"+manga.getId()+"/characters", "characters");
        Set<Character> listOfCharacters = new HashSet<>();
        try {
            for (Object character : restOfCharacters) {
                listOfCharacters.add(CharacterDto.convertLinkedToCharacter(character));
            }
            charaService.processListOfCharacters(listOfCharacters);
        }catch (NullPointerException e){
            System.out.println(String.format("Manga: %s, without characters", manga.getTitle()));
        }
    }

}

