package com.karp.loatrack.roster;

import com.karp.loatrack.roster.dto.CharacterDto;
import com.karp.loatrack.roster.model.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roster/v1")
@RequiredArgsConstructor
@Validated
public class RosterController {
    private final RosterService rosterService;

    @GetMapping("/")
    public ResponseEntity<List<CharacterDto>> getRoster(@RequestParam UUID userId){
        return ResponseEntity.ok().body(rosterService.getAllCharacters(userId));
    }

    @GetMapping("/character")
    public ResponseEntity<CharacterDto> getCharacterById(@RequestParam String charId){
        return ResponseEntity.ok().body(rosterService.getCharacterById(Integer.parseInt(charId)));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/updateCharacter")
    public void updateCharacter(@RequestBody CharacterDto characterDto){
        rosterService.saveCharacter(characterDto);
    }
}
