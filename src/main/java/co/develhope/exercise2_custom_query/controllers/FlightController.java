package co.develhope.exercise2_custom_query.controllers;

import co.develhope.exercise2_custom_query.entities.Flight;
import co.develhope.exercise2_custom_query.enums.Status;
import co.develhope.exercise2_custom_query.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/provision")
    public void createFlights(@RequestParam(defaultValue = "100") Integer n) {
        flightService.provision(n);
    }

    @GetMapping("/list")
    public List<Flight> listOfFlights() {
        return flightService.listOfFlights();
    }

    @GetMapping("/order/asc")
    public Page<Flight> listOfFlightsAsc(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return flightService.listOfFlightsAsc(page, size);
    }

    @GetMapping("/ontime")
    public List<Flight> listOfFlifhtOntime() {
        return flightService.listOfFlightsOntime();
    }

    @GetMapping("/status")
    public List<Flight> listOfFlightStatus(@RequestParam("p1") Status p1, @RequestParam("p2") Status p2) {
        return flightService.listOfFlightStatus(p1, p2);
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<Flight>> getAFlight(@RequestParam Integer id){
        Optional<Flight> optionalFlight = flightService.findFlightId(id);
        if(optionalFlight.isPresent()) {
            return new ResponseEntity<>(optionalFlight, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}