package edu.greenriver.sdev.saasproject.db;

import edu.greenriver.sdev.saasproject.domain.SoundCloudMix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundCloudMixRepository extends JpaRepository<SoundCloudMix, Integer> {
    // we have all the CRUD methods we need
}




