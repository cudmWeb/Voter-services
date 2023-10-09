package com.vs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoterImageRepository extends JpaRepository<VoterImage, Long> {

    @Query(value = "select v from VoterImage v where v.voterId = ?1")
    VoterImage findVoterImageByVoterId(Long voterId);

}
