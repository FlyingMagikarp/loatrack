package com.karp.loatrack.roster;

import com.karp.loatrack.roster.dto.CharacterDto;
import com.karp.loatrack.roster.model.Character;
import com.karp.loatrack.roster.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RosterService {
    private final RosterRepository rosterRepository;
    private final UserRepository userRepository;

    public List<CharacterDto> getAllCharacters(UUID userId) {
        List<Character> characters = rosterRepository.findAllByUserId(userId);
        return characters.stream().map(this::mapCharacterToDto).collect(Collectors.toList());
    }

    public CharacterDto getCharacterById(int id) {
        Optional<Character> character = rosterRepository.findById(id);
        if (character.isPresent()) {
            return mapCharacterToDto(character.get());
        }
        log.error("Character not found");
        return null;
    }

    public void saveCharacter(CharacterDto characterDto) {
        Optional<Character> oc = rosterRepository.findById(characterDto.id);
        Character c = new Character();
        if (oc.isPresent()) {
            c = oc.get();
        }
        c.setName(characterDto.name);
        c.setIlvl(characterDto.ilvl);
        c.setName(characterDto.name);
        c.setNotes(characterDto.notes);
        c.setNotesOverview(characterDto.notesOverview);
        c.setClassname(characterDto.classname);

        Optional<User> user = userRepository.findById(characterDto.userId);
        if (user.isPresent()) {
            c.setFkUser(user.get());
        } else {
            log.error("User not found");
        }

        rosterRepository.save(c);
    }

    public void deleteCharacter(int id) {
        Optional<Character> oc = rosterRepository.findById(id);
        oc.ifPresent(rosterRepository::delete);
    }

    private CharacterDto mapCharacterToDto(Character character) {
        CharacterDto c = new CharacterDto();
        c.id = character.getId();
        c.userId = character.getFkUser().getId();
        c.name = character.getName();
        c.ilvl = character.getIlvl();
        c.notes = character.getNotes();
        c.notesOverview = character.getNotesOverview();
        c.classname = character.getClassname();

        return c;
    }
}
