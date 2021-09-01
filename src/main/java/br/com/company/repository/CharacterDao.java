package br.com.company.repository;

import br.com.company.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterDao extends JpaRepository<Character, Long> {


}
