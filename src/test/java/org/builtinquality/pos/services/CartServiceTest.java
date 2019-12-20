package org.builtinquality.pos.services;

//import org.builtinquality.pos.entity.ItemsEntity;
//import org.builtinquality.pos.repository.ItemsRepository;
//import org.builtinquality.pos.repository.PromotionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import static java.util.Arrays.asList;
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

//    private CartService cartService;
//
//    @Mock
//    private ItemsRepository itemsRepository;
//
//    @BeforeEach
//    public void setUp() {
//        cartService = new CartService(itemsRepository);
//        //given
//        when(itemsRepository.getOne("ITEM000001")).thenReturn(new ItemsEntity("ITEM000001", "橙汁", "瓶", 2));
//    }
//
//    @Test
//    public void should_print_receipt_when_buy_one_items_with_no_promotion() {
//
//        //when
//        cartService.addItems(asList("ITEM000001"));
//
//        //then
//        assertThat(cartService.printReceipt(), is(
//                "********<收据>********\n" +
//                        "名称：橙汁，数量：1瓶，单价：2.00(元)，小计：2.00(元)\n" +
//                        "----------------------\n" +
//                        "总计：2.00(元)\n" +
//                        "节省：0.00(元)\n" +
//                        "**********************"));
//    }
}
