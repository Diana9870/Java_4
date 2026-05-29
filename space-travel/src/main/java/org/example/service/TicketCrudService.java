package org.example.service;

import org.example.HibernateUtil;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class TicketCrudService {

    public Ticket create(Ticket ticket) {
        validateTicket(ticket);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            validateReferences(session, ticket);

            ticket.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));

            session.persist(ticket);

            transaction.commit();

            return ticket;
        }
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

    public Ticket update(Ticket ticket) {
        if (ticket == null || ticket.getId() == null) {
            throw new IllegalArgumentException("Ticket ID cannot be null");
        }

        validateTicket(ticket);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            Ticket existingTicket = session.get(Ticket.class, ticket.getId());

            if (existingTicket == null) {
                throw new IllegalArgumentException("Ticket not found");
            }

            validateReferences(session, ticket);

            existingTicket.setClient(ticket.getClient());
            existingTicket.setFromPlanet(ticket.getFromPlanet());
            existingTicket.setToPlanet(ticket.getToPlanet());

            session.merge(existingTicket);

            transaction.commit();

            return existingTicket;
        }
    }

    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            Ticket ticket = session.get(Ticket.class, id);

            if (ticket == null) {
                throw new IllegalArgumentException("Ticket not found");
            }

            session.remove(ticket);

            transaction.commit();
        }
    }

    private void validateTicket(Ticket ticket) {
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

    private void validateReferences(Session session, Ticket ticket) {

        Client client = session.get(
                Client.class,
                ticket.getClient().getId()
        );

        if (client == null) {
            throw new IllegalArgumentException("Client does not exist");
        }

        Planet fromPlanet = session.get(
                Planet.class,
                ticket.getFromPlanet().getId()
        );

        if (fromPlanet == null) {
            throw new IllegalArgumentException("From planet does not exist");
        }

        Planet toPlanet = session.get(
                Planet.class,
                ticket.getToPlanet().getId()
        );

        if (toPlanet == null) {
            throw new IllegalArgumentException("To planet does not exist");
        }
    }
}