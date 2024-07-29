package com.karp.loatrack.content;

import com.karp.loatrack.content.dto.ContentDto;
import com.karp.loatrack.content.dto.ContentSettingsDto;
import com.karp.loatrack.content.dto.WeeklyClearDto;
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

@RestController
@RequestMapping("/content/v1")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Validated
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/raids")
    public ResponseEntity<List<ContentDto>> getClearableRaids(@RequestParam int iLvl){
        return ResponseEntity.ok().body(contentService.getClearableRaids(iLvl));
    }

    @GetMapping("/weeklyClears")
    public ResponseEntity<List<List<WeeklyClearDto>>> getWeeklyClearStatus(){
        return ResponseEntity.ok().body(contentService.getWeeklyClearStatus());
    }

    @PostMapping("/weeklyClears")
    public void updateWeeklyClearStatus(@RequestBody WeeklyClearDto weeklyClearDto){
        contentService.updateWeeklyClearStatus(weeklyClearDto);
    }
    //get, reset weekly clear status
    @GetMapping("/weeklyClears/reset")
    public void resetWeeklyClearStatus(){
        contentService.resetWeeklyClearStatus();
    }


    @GetMapping("/settings")
    public ResponseEntity<ContentSettingsDto> getContentSettings(@RequestParam int charId){
        return ResponseEntity.ok().body(contentService.getContentSettings(charId));
    }

    //post update content settings
    @PostMapping("/settings/update")
    public void updateContentSettings(@RequestBody ContentSettingsDto contentSettingsDto){
        contentService.updateContentSettings(contentSettingsDto);
    }

}
