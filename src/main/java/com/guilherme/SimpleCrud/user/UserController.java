package com.guilherme.SimpleCrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody UserCreateRequestDTO payload) throws Exception {
        var response = this.userService.createUser(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> details(@PathVariable UUID id) throws Exception{
        var response = this.userService.details(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> listAll(){
        var response = this.userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody User payload) throws Exception{
        var response = this.userService.updateUser(id, payload);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) throws Exception {
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
