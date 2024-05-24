package com.example.meens.repository;

import com.example.meens.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с BD
 */
@Repository
public interface MinesweeperGameRepository extends JpaRepository<GameEntity, String> {

}
