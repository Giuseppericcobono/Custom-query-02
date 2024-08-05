package co.develhope.exercise2_custom_query.services;

import co.develhope.exercise2_custom_query.entities.Flight;
import co.develhope.exercise2_custom_query.enums.Status;
import co.develhope.exercise2_custom_query.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    Random random = new Random();

    public String randomString(Integer length) {
        Integer leftLimit = 97; // letter 'a'
        Integer rightLimit = 122; // letter 'z'
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public Status getRandomStatus(Class<Status> status){
        Integer x = random.nextInt(status.getEnumConstants().length);
        return status.getEnumConstants()[x];
    }

    public void provision(Integer n) {
        for (int i = 0; i < n; i++) {
            Flight newFlight = new Flight();
            newFlight.setDescription(randomString(10));
            newFlight.setFromAirport(randomString(3));
            newFlight.setToAirport(randomString(3));
            newFlight.setStatus(getRandomStatus(Status.class));
            flightRepository.save(newFlight);
        }
    }

    public List<Flight> listOfFlights () {
        return flightRepository.findAll();
    }

    public Page<Flight> listOfFlightsAsc(Integer page, Integer size) {
        Pageable pageableRequest = PageRequest.of(page, size);
        return flightRepository.findAllASC(pageableRequest);
    }

    public List<Flight> listOfFlightsOntime() {
        return flightRepository.findStatusOntime();
    }

    public List<Flight> listOfFlightStatus(Status p1, Status p2) {
        return flightRepository.findByStatus(p1, p2);
    }

    public Optional<Flight> findFlightId(Integer id){
        return findFlightId(id);
    }
}
