package com.app.cms.controller;

import com.app.cms.dto.UserDto;
import com.app.cms.dto.converter.UserConverter;
import com.app.cms.repository.UserRepository;
import com.app.cms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserRepository userRepository, UserService userService, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userConverter.toDto(userService.save(userConverter.toEntity(userDto)));
    }

    @PutMapping
    public UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        return userConverter.toDto(userService.save(userConverter.toEntity(userDto)));
    }

    @PatchMapping(value = "/{userId}")
    public void updateUserPassword(@PathVariable Long userId, UserDto userDto) {
        userService.saveUserPartially(userId, userConverter.toMap(userDto));
    }

    @GetMapping(value = "/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userConverter.toDto(userRepository.getOne(userId));
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

    // TODO patch options cache? pagination


}

