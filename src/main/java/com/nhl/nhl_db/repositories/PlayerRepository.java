package com.nhl.nhl_db.repositories;

import com.nhl.nhl_db.repositories.entities.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    List<Player> findByName(String name);

}
