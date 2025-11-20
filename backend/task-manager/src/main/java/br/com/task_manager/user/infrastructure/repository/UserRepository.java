package br.com.task_manager.user.infrastructure.repository;

import br.com.task_manager.user.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    private UserJpaRepository userJpaRepository;
}