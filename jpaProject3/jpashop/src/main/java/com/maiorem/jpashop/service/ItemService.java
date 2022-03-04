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
    public void updateItem(Long itemId, Book bookParam) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(bookParam.getPrice());
        findItem.setName(bookParam.getName());
        findItem.setStockQuantity(bookParam.getStockQuantity());
    }

    //병합
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
