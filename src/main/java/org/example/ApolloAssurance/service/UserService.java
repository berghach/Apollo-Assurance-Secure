package org.example.ApolloAssurance.service;

import org.example.ApolloAssurance.dto.UserDTO;
import org.example.ApolloAssurance.entities.User;
import org.example.ApolloAssurance.mappers.GenericMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.ApolloAssurance.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GenericMapper genericMapper;

    public List<UserDTO> findAll(){
        return genericMapper.toDTOList(userRepository.findAll(), UserDTO.class);
    }
    public Optional<UserDTO> findById(UUID uuid) throws IllegalArgumentException {
        Optional<User> userOptional = userRepository.findById(uuid);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDTO userDTO = genericMapper.toDTO(user, UserDTO.class);
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
    }
    @Transactional
    public void create(UserDTO userDTO){
        User user = genericMapper.fromDTO(userDTO, User.class);
        userRepository.save(user);
    }
    @Transactional
    public boolean update(UUID uuid, UserDTO userUpdateDTO) throws IllegalArgumentException{
        User existingUser = userRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No user corresponding to this identity."));

        User oldUser = existingUser;

        existingUser = genericMapper.fromDTO(userUpdateDTO, User.class);

        User newUser = userRepository.save(existingUser);

        return !oldUser.equals(newUser);
    }
    @Transactional
    public boolean delete(UUID uuid) throws IllegalArgumentException{
        User existingUser = userRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("No user corresponding to this identity."));

        userRepository.delete(existingUser);

        return !userRepository.existsById(uuid);
    }
}
