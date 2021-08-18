package br.com.company.controller;

import br.com.company.entity.Manga;
import br.com.company.service.MangaService;
import br.com.company.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manga")
public class MangaController {

    @Autowired
    private MangaService service;

    @RequestMapping("/import")
    @ResponseBody
    public String importFromApiRest(){
        for(int i=1; i<5; i++)
            processImportPages(i);

        return "";
    }

    private void processImportPages(int page){
        List<Manga> listOfMangas = Util.importCharacterFromRestApi("https://api.jikan.moe/v3/search/anime?q=&order_by=members&sort=desc&page="+page, "results");
        service.processListOfMangas(listOfMangas);
    }
}
