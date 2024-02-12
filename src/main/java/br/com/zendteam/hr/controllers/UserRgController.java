package br.com.zendteam.hr.controllers;

import br.com.zendteam.hr.domain.UserRg.UserRgDto;
import br.com.zendteam.hr.domain.dtos.MessageDto;
import br.com.zendteam.hr.services.UserRgService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doc/rg")
public class UserRgController {
    private final UserRgService userRgService;

    public UserRgController(UserRgService userRgService) {
        this.userRgService = userRgService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> insert(@RequestBody @Valid UserRgDto userRgDto) {
        return this.userRgService.insert(userRgDto);
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<?> finOne(@PathVariable(value = "user_id") String user_id) {
        return this.userRgService.findOne(user_id);
    }

    @PatchMapping("/{user_id}")
    public ResponseEntity<?> update(@RequestBody @Valid UserRgDto userRgDto, @PathVariable(value = "user_id") @Valid String user_id) {
        return this.userRgService.update(userRgDto, user_id);
    }
}
