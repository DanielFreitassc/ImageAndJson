package com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.CardapioEntity;

public interface CardapioRepository extends JpaRepository<CardapioEntity,Long>{
    
}
