package org.example;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.service.ClientCrudService;
import org.example.service.PlanetCrudService;
import org.example.service.TicketCrudService;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        ClientCrudService clientService =
                new ClientCrudService();

        PlanetCrudService planetService =
                new PlanetCrudService();

        TicketCrudService ticketService =
                new TicketCrudService();

        Client client =
                clientService.create("New Client");

        Planet earth =
                planetService.getById("EARTH");

        Planet mars =
                planetService.getById("MARS");

        Ticket ticket = new Ticket();

        ticket.setCreatedAt(LocalDateTime.now());

        ticket.setClient(client);

        ticket.setFromPlanet(earth);

        ticket.setToPlanet(mars);

        ticketService.create(ticket);

        System.out.println("Ticket saved!");
    }
}