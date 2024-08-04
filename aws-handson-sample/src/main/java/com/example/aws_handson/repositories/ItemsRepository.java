package com.example.aws_handson.repositories;

import com.example.aws_handson.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Item,Integer> {
}
