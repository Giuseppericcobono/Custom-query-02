package co.develhope.exercise2_custom_query.repositories;

import co.develhope.exercise2_custom_query.entities.Flight;
import co.develhope.exercise2_custom_query.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query("SELECT fromAirport FROM Flight fromAirport ORDER BY fromAirport ASC")
    Page<Flight> findAllASC(Pageable pageable);

    @Query("SELECT status FROM Flight status WHERE status.status = 'ONTIME'")
    List<Flight> findStatusOntime();

    @Query("SELECT status FROM Flight status WHERE status.status IN (:status1, :status2)")
    List<Flight> findByStatus(@Param("status1") Status status1, @Param("status2") Status status2);

    @Query("SELECT id FROM Flight id WHERE id.id = n")
    Optional<Flight> findFlightId(@Param("id") Integer id);
}