package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        // set으로 값을 수정해주기보다, item엔티티에서 addStock과 같은 의미있는 메소드를 만들어서 사용해주자
        // findItem.change(name, price, stockQuantity);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        // itemRepository.save(findItem); 이거 호출해주지 않아도 된다!!! -> 영속성 상태인것을 찾아왔기 때문에
        // 값을 세팅해주면 @Transactional에 의해서 커밋이 되고, 자동으로 update가 된다.

    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
