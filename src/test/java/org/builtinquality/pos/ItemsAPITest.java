package org.builtinquality.pos;

import org.builtinquality.pos.controllers.ItemController;
import org.builtinquality.pos.entity.ItemEntity;
import org.builtinquality.pos.repository.ItemsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ItemController.class)
public class ItemsAPITest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ItemsRepository itemsRepository;

    @Test
    public void should_get_all_items() throws Exception {
        when(itemsRepository.findAll())
                .thenReturn(asList(new ItemEntity("ITEM001", "可口可乐", "听", 2d),
                        new ItemEntity("ITEM002", "苹果", "斤", 3.5)));

        mockMvc.perform(get("/items"))
                .andExpect(content().json("[" +
                        "{\"code\": \"ITEM001\", \"name\": \"可口可乐\", \"unit\": \"听\", \"price\": 2}," +
                        "{\"code\": \"ITEM002\", \"name\": \"苹果\", \"unit\": \"斤\", \"price\": 3.5}" +
                        "]"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void should_get_correct_item_when_query_by_code() throws Exception {
        when(itemsRepository.getOne("ITEM001"))
                .thenReturn(new ItemEntity("ITEM001", "可口可乐", "听", 2d));

        mockMvc.perform(get("/items/ITEM001"))
                .andExpect(content().json("{\"code\": \"ITEM001\", \"name\": \"可口可乐\", \"unit\": \"听\", \"price\": 2}"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void should_add_item_when_post_new_item() throws Exception {
        when(itemsRepository.save(new ItemEntity("ITEM001", "可口可乐", "听", 2d)))
                .thenReturn(new ItemEntity("ITEM001", "可口可乐", "听", 2d));

        mockMvc.perform(post("/items").contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"ITEM001\", \"name\": \"可口可乐\", \"unit\": \"听\", \"price\": 2}"))
                .andExpect(content().json("{\"code\": \"ITEM001\", \"name\": \"可口可乐\", \"unit\": \"听\", \"price\": 2}"))
                .andExpect(status().isCreated());
    }
}
