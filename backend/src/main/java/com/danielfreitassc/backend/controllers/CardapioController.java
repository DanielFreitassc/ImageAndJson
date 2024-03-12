package com.danielfreitassc.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.CardapioRecordDTO;
import com.danielfreitassc.backend.models.CardapioEntity;
import com.danielfreitassc.backend.services.CardapioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cardapio")
public class CardapioController {
    @Autowired
    private CardapioService cardapioService;
    
    @PostMapping
    public ResponseEntity<CardapioEntity> saveCardapio(@RequestBody @Valid CardapioRecordDTO cardapioRecordDTO) {
        return cardapioService.saveCardapio(cardapioRecordDTO);
    }

    @GetMapping
    public ResponseEntity<List<CardapioEntity>> getAllCardapios() {
        return cardapioService.getAllCardapios();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOneCardapio(@PathVariable(value = "id")Long id) {
        return cardapioService.getOneCardapio(id);
    }
    

    @PutMapping("{id}")
    public ResponseEntity<Object> updateCardapio(@PathVariable(value = "id")Long id, @RequestBody @Valid CardapioRecordDTO cardapioRecordDTO) {
        return cardapioService.updateCardapio(id, cardapioRecordDTO);
    } 

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCardapio(@PathVariable(value = "id")Long id) {
        return cardapioService.deleteCardapio(id);
    }
}
