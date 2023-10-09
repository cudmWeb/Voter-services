package com.vs;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterImageService {

    @Autowired
    private VoterImageRepository voterImageRepository;

    public VoterImage downloadImage(Long id){

        return voterImageRepository.findVoterImageByVoterId(id);

    }

}
