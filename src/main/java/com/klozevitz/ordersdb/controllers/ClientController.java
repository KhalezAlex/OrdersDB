package com.klozevitz.ordersdb.controllers;

import com.klozevitz.ordersdb.model.dao.services.client.IDaoClient;
import com.klozevitz.ordersdb.model.entities.client.Client;
import com.klozevitz.ordersdb.model.entities.client.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/client")
public class ClientController {
    @Autowired
    IDaoClient daoClient;

    @GetMapping("/all")
    public List<ClientDTO> all() {
        List<ClientDTO> dto = new LinkedList<>();
        for (Client client: daoClient.findAll())
            dto.add(new ClientDTO(client));
        return dto;
    }

    @GetMapping("/findById")
    public ClientDTO findById(@RequestParam int id) {
        return new ClientDTO(daoClient.findById(id));
    }

    @PostMapping("/save")
    public ClientDTO save(@ModelAttribute Client client) {
        return new ClientDTO(daoClient.save(client));
    }

    @PostMapping("/update")
    public ClientDTO update(@RequestParam Integer id, @RequestParam String clientName) {
        return new ClientDTO(daoClient.update(new Client(id, clientName)));
    }

    @GetMapping("/delete")
    public ClientDTO delete(@RequestParam Integer id) {
        return new ClientDTO(daoClient.delete(id));
    }
}
