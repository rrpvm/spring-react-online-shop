package com.rrpvm.server.controller;

import com.rrpvm.server.dao.ItemSellRepository;
import com.rrpvm.server.dto.ItemRarityResponse;
import com.rrpvm.server.model.ItemRarity;
import com.rrpvm.server.model.ItemSell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class ItemViewController {
    @Autowired
    private ItemSellRepository itemRepository;
    private static final int ITEMS_IN_COLUMN = 3;

    @GetMapping("/get_items/{dataPosition}")
    public ResponseEntity<List<ItemSell>> getItems(@RequestParam(required = false) String params, @PathVariable(required = true) int dataPosition) {
        final List<ItemSell> items = itemRepository.findAll();
        int avaliableItems = Math.abs(items.size() - dataPosition);
        return ResponseEntity.ok(items.subList(dataPosition, dataPosition + Math.min(ITEMS_IN_COLUMN, avaliableItems)));
    }

    @GetMapping("/items_length")
    public ResponseEntity<Long> sendItemsLength() {
        return ResponseEntity.ok(itemRepository.count());
    }

    @GetMapping("/items_rarity")
    public ResponseEntity<List<ItemRarityResponse>> sendItemsRarity() {
        return ResponseEntity.ok(Arrays.stream(ItemRarity.values()).map((item) -> new ItemRarityResponse(item)).collect(Collectors.toList()));
    }
}
