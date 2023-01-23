package com.klozevitz.ordersdb.model.dao.services.client;

import com.klozevitz.ordersdb.model.entities.client.Client;
import com.klozevitz.ordersdb.model.repositories.IClientRepository;
import com.klozevitz.ordersdb.model.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService implements IDaoClient {
    @Autowired
    private IClientRepository clientRep;
    @Autowired
    private IOrderRepository orderRep;

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRep.findAll();
    }

    @Override
    public Client findById(Integer id) {
        return clientRep.findById(id).orElse(new Client());
    }

    @Override
    public Client save(Client client) {
        return clientRep.save(client);
    }

    @Override
    @Transactional
    public Client update(Client client) {
        if (clientRep.findById(client.getId()).isEmpty())
            return new Client();
        return updateClient(client);
    }

    private Client updateClient(Client client) {
        Client clientToUpdate = clientRep.findById(client.getId()).orElse(null);
        if (clientToUpdate == null)
            return new Client();
        clientToUpdate.setClientName(client.getClientName());
        return clientToUpdate;
    }

    @Override
    public Client delete(Integer id) {
        Client client = clientRep.findById(id).orElse(null);
        if (client == null)
            return new Client();
        deleteOrders(client);
        clientRep.deleteById(id);
        return client;
    }

    private void deleteOrders(Client client) {
        orderRep.deleteAll(orderRep.findAllByClient(client));
    }
}
