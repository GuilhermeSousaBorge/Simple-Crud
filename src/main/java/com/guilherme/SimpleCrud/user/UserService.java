package com.guilherme.SimpleCrud.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO createUser(UserCreateRequestDTO payload) throws Exception {

        if(payload.name().isEmpty() || payload.name().isBlank()){
            throw new Exception("Nome nao pode ser vazio");
        }if(payload.email().isEmpty() || payload.email().isBlank()){
            throw new Exception("Email nao pode ser vazio");
        }if(payload.password().isEmpty() || payload.password().isBlank()){
            throw new Exception("Senha nao pode ser vazia");
        }

        var entity = this.userMapper.toEntity(payload);
        return this.userMapper.toUserResponseDTO(this.userRepository.save(entity));
    }

    public User details(UUID id) throws Exception{

        var user = this.userRepository.findById(id).orElseThrow(() -> new Exception("Usurio nao encontrado"));
        return user;

    }

    public List<User> findAll(){

        var userList = this.userRepository.findAll().stream().map(user -> new User(user.getId(), user.getName(),
                user.getEmail(), user.getPassword(), user.getCreatedAt(), user.getUpdatedAt())).toList();
        return userList;
    }

    public UUID updateUser(UUID id, User payload) throws Exception {
        var user = this.userRepository.findById(id).orElseThrow(() -> new Exception("Usurio nao encontrado"));

        user.setName(payload.getName());
        user.setEmail(payload.getEmail());
        user.setPassword(payload.getPassword());
        user.setCreatedAt(payload.getCreatedAt());
        user.setUpdatedAt(payload.getUpdatedAt());
        var response = this.userRepository.save(user);

        return response.getId();
    }
    public void deleteUser(UUID id) throws Exception{

        var user = this.userRepository.findById(id).orElseThrow(() -> new Exception("Usurio nao encontrado"));
        this.userRepository.delete(user);
    }
}
