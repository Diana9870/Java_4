package org.example.service;

import org.example.HibernateUtil;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketCrudService {

    public Ticket create(Ticket ticket) {

        validate(ticket);

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

    public List<Ticket> getAll() {

        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }

    public void update(Ticket ticket) {

        validate(ticket);

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            session.merge(ticket);

            tx.commit();
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

    private void validate(Ticket ticket) {

        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null");
        }

        if (ticket.getClient() == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }

        if (ticket.getFromPlanet() == null) {
            throw new IllegalArgumentException("From planet cannot be null");
        }

        if (ticket.getToPlanet() == null) {
            throw new IllegalArgumentException("To planet cannot be null");
        }
    }
}