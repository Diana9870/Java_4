package org.example.service;

import org.example.HibernateUtil;
import org.example.entity.Planet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {

    public Planet create(Planet planet) {
        validatePlanet(planet);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(planet);

            transaction.commit();

            return planet;
        }
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

    public Planet update(Planet planet) {
        validatePlanet(planet);

        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            Planet existingPlanet = session.get(Planet.class, planet.getId());

            if (existingPlanet == null) {
                throw new IllegalArgumentException("Planet not found");
            }

            existingPlanet.setName(planet.getName());

            session.merge(existingPlanet);

            transaction.commit();

            return existingPlanet;
        }
    }

    public void deleteById(String id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();

            Planet planet = session.get(Planet.class, id);

            if (planet == null) {
                throw new IllegalArgumentException("Planet not found");
            }

            session.remove(planet);

            transaction.commit();
        }
    }

    private void validatePlanet(Planet planet) {
        if (planet == null) {
            throw new IllegalArgumentException("Planet cannot be null");
        }

        if (planet.getId() == null ||
                !planet.getId().matches("^[A-Z0-9]+$")) {

            throw new IllegalArgumentException(
                    "Planet ID must contain only uppercase letters and digits"
            );
        }

        String name = planet.getName();

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Planet name cannot be empty");
        }

        if (name.length() < 1 || name.length() > 500) {
            throw new IllegalArgumentException(
                    "Planet name must be between 1 and 500 characters"
            );
        }
    }
}