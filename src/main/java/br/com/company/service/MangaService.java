package br.com.company.service;

import br.com.company.entity.Manga;
import br.com.company.repository.MangaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class MangaService {

    @Autowired
    private MangaDao dao;

    public MangaService() {}

    public MangaService(MangaDao dao) {
        this.dao = dao;
    }

    public void processListOfMangas(Set<Manga> listOfMangas){
        for(Manga manga : listOfMangas){
            dao.save(manga);
        }
    }

    public List<Manga> getMangas(){
        return Arrays.asList(dao.findAll().get(0));
    }


}
