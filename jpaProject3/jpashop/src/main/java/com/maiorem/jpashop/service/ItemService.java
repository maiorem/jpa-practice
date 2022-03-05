package com.maiorem.jpashop.service;

import com.maiorem.jpashop.domain.item.Book;
import com.maiorem.jpashop.domain.item.Item;
import com.maiorem.jpashop.repository.ItemRepository;
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
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    //변경감지 기능 사용(영속성 컨텍스트에서 엔티티를 조회한 후 데이터 수정)
    //==> 트랜잭션 커밋 시점에 변경감지 동작하여 update sql 실행
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        //findItem.change(price, name, stockQuantity); => 주로 쓰는 방식. 하나의 기능으로 추적할 수 있어야 함.
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }

    //병합merge => (실무X) : 일부 수정을 못함. 전체를 갈아내야 해서 실무에서는 위험.
    @Transactional
    public Item updateItemMerge(Long itemId, Book bookParam) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(bookParam.getPrice());
        findItem.setName(bookParam.getName());
        findItem.setStockQuantity(bookParam.getStockQuantity());
        return findItem;
    }


    //전체 조회 //읽기전용
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    //단건 조회 //읽기전용
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


}
