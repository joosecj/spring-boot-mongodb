package com.devsuperior.workshopmongo.services;

import com.devsuperior.workshopmongo.dto.UserDTO;
import com.devsuperior.workshopmongo.entities.User;
import com.devsuperior.workshopmongo.repositories.UserRepository;
import com.devsuperior.workshopmongo.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(String id) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserDTO(userEntity);
    }

    @Transactional(readOnly = false)
    public UserDTO insert(UserDTO userDTO) {
        User userEntity = new User();
        copyDtoToEntity(userDTO, userEntity);
        userRepository.save(userEntity);
        return new UserDTO(userEntity);
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(String id) {
            findById(id);
            userRepository.deleteById(id);
    }

    public UserDTO update(String id, UserDTO userDTO) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        copyDtoToEntity(userDTO, userEntity);
        return new UserDTO(userRepository.save(userEntity));
    }

    @Transactional(readOnly = true)
    public User findByPosts(String id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    private void copyDtoToEntity(UserDTO userDTO, User userEntity) {
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
    }
}
