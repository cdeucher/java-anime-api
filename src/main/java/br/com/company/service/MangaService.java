package br.com.company.service;

import br.com.company.entity.Manga;
import br.com.company.entity.dto.MangaDto;
import br.com.company.repository.MangaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {

    @Autowired
    private MangaDao dao;

    public void processListOfMangas(List<Manga> listOfMangas){
        for(Object marga : listOfMangas){
            dao.save( MangaDto.convertLinkedToManga(marga) );
        }
    }
}
