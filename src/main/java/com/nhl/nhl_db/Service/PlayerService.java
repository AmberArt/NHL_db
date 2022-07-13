package com.nhl.nhl_db.Service;

import com.nhl.nhl_db.controllers.dto.PlayerDTO;
import com.nhl.nhl_db.repositories.entities.Player;

import java.util.List;

public interface PlayerService {

    Player save(PlayerDTO playerDTO);
    Player update(PlayerDTO playerDTO, Integer id);
    void delete(Integer playerId);
    Iterable<Player> findAll();
    Player findById(Integer playerId);
    List<Player> findByName(String name);
}
