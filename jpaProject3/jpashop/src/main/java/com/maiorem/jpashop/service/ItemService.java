package com.maiorem.jpashop.service;

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

    //전체 조회 //읽기전용
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    //단건 조회 //읽기전용
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


}
