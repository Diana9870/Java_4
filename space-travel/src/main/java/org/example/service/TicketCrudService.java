package org.example.service;

import org.example.HibernateUtil;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TicketCrudService {

    public Ticket create(Ticket ticket) {

        if (ticket.getClient() == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        if (ticket.getFromPlanet() == null ||
                ticket.getToPlanet() == null) {

            throw new IllegalArgumentException("Planet cannot be null");
        }

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            session.persist(ticket);

            tx.commit();
        }

        return ticket;
    }

    public Ticket getById(Long id) {

        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    public void deleteById(Long id) {

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            Ticket ticket = session.get(Ticket.class, id);

            if (ticket != null) {
                session.remove(ticket);
            }

            tx.commit();
        }
    }
}