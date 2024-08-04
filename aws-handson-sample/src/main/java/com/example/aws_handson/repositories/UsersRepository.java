package com.example.aws_handson.repositories;

import com.example.aws_handson.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,String> {
}
