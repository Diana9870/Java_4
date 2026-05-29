package org.example.service;

import org.example.HibernateUtil;
import org.example.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {

    public Planet create(String id, String name) {

        Planet planet = new Planet(id, name);

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            session.persist(planet);

            tx.commit();
        }

        return planet;
    }

    public Planet getById(String id) {

        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(Planet.class, id);
        }
    }

    public List<Planet> getAll() {

        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from Planet", Planet.class).list();
        }
    }

    public void update(Planet planet) {

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            session.merge(planet);

            tx.commit();
        }
    }

    public void deleteById(String id) {

        try (Session session = HibernateUtil.getInstance().openSession()) {

            Transaction tx = session.beginTransaction();

            Planet planet = session.get(Planet.class, id);

            if (planet != null) {
                session.remove(planet);
            }

            tx.commit();
        }
    }
}
