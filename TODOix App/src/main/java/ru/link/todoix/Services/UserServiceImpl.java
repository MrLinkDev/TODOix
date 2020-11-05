package ru.link.todoix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.link.todoix.Entities.UserEntity;
import ru.link.todoix.Enums.Role;
import ru.link.todoix.Repositories.UserRepository;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void createUser(String login, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(UUID.randomUUID());
        userEntity.setName(login);
        userEntity.setPassword(bCryptPasswordEncoder.encode(password));
        userEntity.setRole(Role.USER);

        userRepository.save(userEntity);
    }
}
