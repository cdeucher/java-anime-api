package br.com.company.repository;

import br.com.company.entity.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MangaDao extends JpaRepository<Manga, Long> {

}
