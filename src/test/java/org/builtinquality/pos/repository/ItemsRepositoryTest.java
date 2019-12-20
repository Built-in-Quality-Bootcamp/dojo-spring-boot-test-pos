package org.builtinquality.pos.repository;

import org.builtinquality.pos.entity.ItemEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class ItemsRepositoryTest {
    @Autowired
    private ItemsRepository itemsRepository;

    @BeforeEach
    public void setup() {
        itemsRepository.save(new ItemEntity("ITEM001", "可口可乐", "听", 2d));
        itemsRepository.save(new ItemEntity("ITEM002", "苹果", "斤", 3.5));
    }

    @Test
    public void should_save_item_correctly() {
        assertThat(itemsRepository.findAll().size(), is(2));
    }

    @Test
    public void should_get_item_correctly() {
        assertThat(itemsRepository.getOne("ITEM001"), equalTo(new ItemEntity("ITEM001", "可口可乐", "听", 2d)));
    }

    @AfterEach
    public void tearDown() {
        itemsRepository.deleteAll();
    }
}
