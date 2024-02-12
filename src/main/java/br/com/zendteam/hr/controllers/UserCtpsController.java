package br.com.zendteam.hr.controllers;

import br.com.zendteam.hr.domain.UserCtps.UserCtpsDto;
import br.com.zendteam.hr.domain.UserRg.UserRgDto;
import br.com.zendteam.hr.domain.dtos.MessageDto;
import br.com.zendteam.hr.services.UserCtpsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doc/ctps")
public class UserCtpsController {
    private final UserCtpsService userCtpsService;

    public UserCtpsController(UserCtpsService userCtpsService) {
        this.userCtpsService = userCtpsService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> insert(@RequestBody @Valid UserCtpsDto userCtpsDto) {
        return this.userCtpsService.insert(userCtpsDto);
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<?> finOne(@PathVariable(value = "user_id") String user_id) {
        return this.userCtpsService.findOne(user_id);
    }

    @PatchMapping("/{user_id}")
    public ResponseEntity<?> update(@RequestBody @Valid UserCtpsDto userCtpsDto, @PathVariable(value = "user_id") @Valid String user_id) {
        return this.userCtpsService.update(userCtpsDto, user_id);
    }
}
