package com.danielfreitassc.backend.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.danielfreitassc.backend.controllers.CardapioController;
import com.danielfreitassc.backend.dtos.CardapioRecordDTO;
import com.danielfreitassc.backend.models.CardapioEntity;
import com.danielfreitassc.backend.repositories.CardapioRepository;

@Service
public class CardapioService {
    @Autowired
    private CardapioRepository cardapioRepository;

    public ResponseEntity<CardapioEntity> saveCardapio(CardapioRecordDTO cardapioRecordDTO) {
        CardapioEntity cardapioEntity = new CardapioEntity();
        BeanUtils.copyProperties(cardapioRecordDTO, cardapioEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardapioRepository.save(cardapioEntity));
    }

    public ResponseEntity<List<CardapioEntity>> getAllCardapios() {
        List<CardapioEntity> cardapioList = cardapioRepository.findAll();
        if(!cardapioList.isEmpty()) {
            for(CardapioEntity cardapioEntity : cardapioList) {
                Long id = cardapioEntity.getId();
                cardapioEntity.add(linkTo(methodOn(CardapioController.class).getOneCardapio(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(cardapioList);
    }

    public ResponseEntity<Object> getOneCardapio(Long id) {
        Optional<CardapioEntity> cardapioOpt = cardapioRepository.findById(id);
        if(cardapioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cardapio encontrado");
        }
        cardapioOpt.get().add(linkTo(methodOn(CardapioController.class).getAllCardapios()).withRel("Lista de cardapio"));
        return ResponseEntity.status(HttpStatus.OK).body(cardapioOpt);
    }

    public ResponseEntity<Object> updateCardapio(Long id, CardapioRecordDTO cardapioRecordDTO) {
        Optional<CardapioEntity> cardapioOpt = cardapioRepository.findById(id);
        if(cardapioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cardapio encontrado");
        }
        CardapioEntity cardapioEntity = cardapioOpt.get();
        BeanUtils.copyProperties(cardapioRecordDTO, cardapioEntity);
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.save(cardapioEntity));
    }

    public ResponseEntity<Object> deleteCardapio(Long id) {
        Optional<CardapioEntity> cardapioOpt = cardapioRepository.findById(id);
        if(cardapioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum cardapio encontrado");
        }
        cardapioRepository.delete(cardapioOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cardapio removido com sucesso");
    }
}
