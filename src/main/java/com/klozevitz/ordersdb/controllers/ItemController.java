package com.klozevitz.ordersdb.controllers;

import com.klozevitz.ordersdb.model.dao.services.item.IDaoItem;
import com.klozevitz.ordersdb.model.entities.item.Item;
import com.klozevitz.ordersdb.model.entities.item.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    IDaoItem daoItem;

    @GetMapping("/all")
    public List<ItemDTO> all() {
        List<ItemDTO> dto = new LinkedList<>();
        for (Item item: daoItem.findAll())
            dto.add(new ItemDTO(item));
        return dto;
    }

    @GetMapping("/findById")
    public ItemDTO findById(@RequestParam int id) {
        return new ItemDTO(daoItem.findById(id));
    }

    @PostMapping("/save")
    public ItemDTO save(@ModelAttribute Item item) {
        return new ItemDTO(daoItem.save(item));
    }

    @PostMapping("/update")
    public ItemDTO update(@RequestParam Integer id, @RequestParam(required = false) String itemName,
                       @RequestParam(required = false) Long itemArticle, @RequestParam(required = false) int price) {
        return new ItemDTO(daoItem.update(new Item(id, itemName, itemArticle, price)));
    }

    @GetMapping("/delete")
    public ItemDTO delete(@RequestParam Integer id) {
        return new ItemDTO(daoItem.delete(id));
    }
}
