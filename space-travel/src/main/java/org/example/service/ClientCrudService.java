package org.example.service;

import org.example.HibernateUtil;
import org.example.entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService {

    public Client create(String name) {

        Client client = new Client(name);

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            session.persist(client);

            tx.commit();
        }

        return client;
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

    public void update(Client client) {

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            session.merge(client);

            tx.commit();
        }
    }

    public void deleteById(Long id) {

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            Client client = session.get(Client.class, id);

            if (client != null) {
                session.remove(client);
            }

            tx.commit();
        }
    }
}
