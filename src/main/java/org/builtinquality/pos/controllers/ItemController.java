package org.builtinquality.pos.controllers;

import org.builtinquality.pos.entity.ItemEntity;
import org.builtinquality.pos.repository.ItemsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {

    private ItemsRepository itemsRepository;

    public ItemController(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @GetMapping
    public List<ItemEntity> getAllItems() {
        return itemsRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemEntity addItem(@RequestBody ItemEntity itemEntity) {
        return itemsRepository.save(itemEntity);
    }

    @GetMapping("{code}")
    public ItemEntity getItem(@PathVariable("code") String code) {
        return itemsRepository.findById(code).orElseThrow(EntityNotFoundException::new);
    }
}
