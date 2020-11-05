package ru.link.todoix.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.link.todoix.Entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
