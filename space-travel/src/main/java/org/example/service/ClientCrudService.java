package org.example.service;

import org.example.HibernateUtil;
import org.example.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService {

    public Client create(Client client) {
        validateClient(client);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(client);

            transaction.commit();
            return client;
        }
    }

    public Client getById(Long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Client.class, id);
        }
    }

    public List<Client> getAll() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

    public Client update(Client client) {
        if (client == null || client.getId() == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }

        validateClient(client);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            Client existingClient = session.get(Client.class, client.getId());

            if (existingClient == null) {
                throw new IllegalArgumentException("Client not found");
            }

            existingClient.setName(client.getName());

            session.merge(existingClient);

            transaction.commit();

            return existingClient;
        }
    }

    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            Client client = session.get(Client.class, id);

            if (client == null) {
                throw new IllegalArgumentException("Client not found");
            }

            session.remove(client);

            transaction.commit();
        }
    }

    private void validateClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        String name = client.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be empty");
        }

        if (name.length() < 3 || name.length() > 200) {
            throw new IllegalArgumentException(
                    "Client name must be between 3 and 200 characters"
            );
        }
    }
}