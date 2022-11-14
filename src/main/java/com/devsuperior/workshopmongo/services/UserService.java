package com.devsuperior.workshopmongo.services;

import com.devsuperior.workshopmongo.dto.UserDTO;
import com.devsuperior.workshopmongo.entities.User;
import com.devsuperior.workshopmongo.repositories.UserRepository;
import com.devsuperior.workshopmongo.services.exeptions.DataBaseException;
import com.devsuperior.workshopmongo.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade refencial");
        }
    }

    private void copyDtoToEntity(UserDTO userDTO, User userEntity) {
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
    }
}
