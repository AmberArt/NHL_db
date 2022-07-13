package com.nhl.nhl_db.Service;

import com.nhl.nhl_db.controllers.dto.PlayerDTO;
import com.nhl.nhl_db.repositories.PlayerRepository;
import com.nhl.nhl_db.repositories.entities.Player;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public Player save(PlayerDTO playerDTO) {
        Player player = new Player(playerDTO);
       return playerRepository.save(player);
    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player findById(Integer playerId) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if(optionalPlayer.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player with that ID not found.");
        }
        return optionalPlayer.get();
    }

    @Override
    public Player update(PlayerDTO playerDTO, Integer id) {
        Optional<Player> playerToUpdateOptional = playerRepository.findById(id);
        if(playerToUpdateOptional.isEmpty()){
            return null;
        }

        Player playerToUpdate = playerToUpdateOptional.get();

        if(playerDTO.getName() == null){
            playerToUpdate.setName(playerToUpdate.getName());
        } else if(playerDTO.getName().isEmpty()) {
            playerToUpdate.setName(playerToUpdate.getName());
        } else {
            playerToUpdate.setName(playerDTO.getName());
        }

//       TODO consider adding extra validation:
        playerToUpdate.setAge(playerDTO.getAge() != null ? playerDTO.getAge() : playerToUpdate.getAge());
        playerToUpdate.setRating(playerDTO.getRating() != null ? playerDTO.getRating() : playerToUpdate.getRating());
        playerToUpdate.setYearsOfExperience(playerDTO.getYearsOfExperience() != null ? playerDTO.getYearsOfExperience() : playerToUpdate.getYearsOfExperience());

        return playerRepository.save(playerToUpdate);
    }

    @Override
    public void delete(Integer playerId) {
        Optional<Player> playerToDeleteOptional = playerRepository.findById(playerId);
        if(playerToDeleteOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player with that ID was not found.");
        }

        Player playerToDelete = playerToDeleteOptional.get();
        playerRepository.delete(playerToDelete);
    }

    @Override
    public List<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }



}
