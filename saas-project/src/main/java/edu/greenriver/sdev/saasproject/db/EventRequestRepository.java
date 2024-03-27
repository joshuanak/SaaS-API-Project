package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.domain.EventRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRequestRepository extends JpaRepository<EventRequest, Integer> {
    // we have all the CRUD methods we need
}




