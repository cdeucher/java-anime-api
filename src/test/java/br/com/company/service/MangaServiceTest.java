package br.com.company.service;


import br.com.company.entity.Manga;
import br.com.company.repository.MangaDao;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Consumer;

@SpringBootTest
public class MangaServiceTest {

    @Test
    void whenProcessListOfMangas_ShouldSaveTheListOfMangas() {
            given()
                .mangaProvider(manga -> {
                    manga.mal_id = 1;
                    manga.url = "localhost";
                    manga.image_url = "localhost";
                    manga.title = "Naruto";
                    manga.synopsis = "Naruto é burro";
                    manga.type = "shonnem";
                    manga.episodes = 1;
                })
                .mangaProvider(manga -> {
                    manga.mal_id = 2;
                    manga.url = "localhost" ;
                    manga.image_url = "localhost" ;
                    manga.title = "Death Note";
                    manga.synopsis = "Naruto é burro";
                    manga.type = "shonnem";
                    manga.episodes = 1;
                })
            .whenPerformSaveMangaList()
            .then()
                .saveTheListOfMangas();
    }
    @Test
    void whenGetMangas_ShouldListMangas(){
            given()
                .mangaProvider(manga -> {
                    manga.mal_id = 1;
                    manga.url = "localhost";
                    manga.image_url = "localhost";
                    manga.title = "Naruto";
                    manga.synopsis = "Naruto é burro";
                    manga.type = "shonnem";
                    manga.episodes = 1;
                })
            .whenGetListOfMangas()
            .then()
                .getListOfMangas();
    }

    private DSL given() {
        return new DSL();
    }
    private static class DSL {

        MangaService service;
        Set<Manga> mangaProvider;
        MangaDao mangaDao;

        public DSL() {
            this.mangaProvider = new HashSet<>();
            this.mangaDao = Mockito.mock(MangaDao.class);
            this.service = new MangaService(mangaDao);
        }

        private DSL mangaProvider(Consumer <MangaProviderData> manga) {
            final MangaProviderData provider = new MangaProviderData();
            manga.accept(provider);
            mangaProvider.add(provider);
            return this;
        }

        public ThenDSL whenPerformSaveMangaList() {
            service.processListOfMangas(mangaProvider);
            return new ThenDSL();
        }

        public ThenDSL whenGetListOfMangas() {
            List<Manga> list = new ArrayList<>(mangaProvider);

            Mockito.when(mangaDao.findAll()).thenReturn(list);
            int numberOfMangas = service.getMangas().size();

            return new ThenDSL(numberOfMangas);
        }

        public class ThenDSL {

            private int numberOfMangas;

            public ThenDSL() {}

            public ThenDSL(int numberOfMangas) {
                this.numberOfMangas = numberOfMangas;
            }

            public ThenDSL then() {
                return this;
            }

            public void saveTheListOfMangas() {
                Mockito.verify(mangaDao, Mockito.times(2)).save(Mockito.any());
            }

            public void getListOfMangas(){
                assertEquals(mangaProvider.size(), numberOfMangas);
            }
        }
    }

    private static class MangaProviderData extends Manga {
        public  int mal_id;
        public  String url;
        public  String image_url;
        public  String title;
        public  String synopsis;
        public  String type;
        public  int episodes;
    }

}
