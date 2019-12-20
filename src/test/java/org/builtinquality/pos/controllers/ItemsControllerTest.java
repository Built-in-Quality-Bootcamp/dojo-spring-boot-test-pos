package org.builtinquality.pos.controllers;

import org.builtinquality.pos.entity.ItemEntity;
import org.builtinquality.pos.repository.ItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemsControllerTest {

    @Mock
    private ItemsRepository itemsRepository;

    private ItemController itemsController;

    @BeforeEach
    public void setUp() {
        itemsController = new ItemController(itemsRepository);
    }

    @Test
    public void should_call_repository_save_when_add_items() {
        ItemEntity itemEntity = new ItemEntity("ITEM001", "可口可乐", "听", 2d);
        itemsController.addItem(itemEntity);

        verify(itemsRepository).save(itemEntity);
    }

    @Test
    public void should_call_repository_find_all_when_add_items() {
        List<ItemEntity> itemEntities = asList(new ItemEntity("ITEM001", "可口可乐", "听", 2d),
                new ItemEntity("ITEM002", "苹果", "斤", 3.5));
        when(itemsRepository.findAll()).thenReturn(itemEntities);

        assertThat(itemsController.getAllItems(), is(itemEntities));

    }
}
