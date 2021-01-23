package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 아이템 추가
     * @param item
     */
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * 아이템 조회(모두)
     * @return 아이템 리스트
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 아이템 조회(단건)
     * @param itemId
     * @return 아이템
     */
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
    
}
