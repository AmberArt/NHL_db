package com.nhl.nhl_db.controllers;

import com.nhl.nhl_db.Service.PlayerService;
import com.nhl.nhl_db.controllers.dto.PlayerDTO;
import com.nhl.nhl_db.repositories.entities.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/player")
public class PlayerController {


    private final PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    @PostMapping("/add")
    public Player addNewPlayer(@RequestBody PlayerDTO newPlayerDTO){
        return playerService.save(newPlayerDTO);
    }

    @GetMapping
    public Iterable<Player> getAllPlayers(){ return playerService.findAll(); }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Integer id){ return playerService.findById(id); }

////    curl localhost:4001/player/find?name=John
    @GetMapping("/find")
    public List<Player> findByName(@RequestParam String name){
        return playerService.findByName(name);
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@RequestParam PlayerDTO playerDTO, @PathVariable("id") Integer playerId){
        return playerService.update(playerDTO, playerId);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Integer playerId){
        playerService.delete(playerId);

    }


}
