package com.vs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VoterService {

    private static final Logger LOG = LoggerFactory.getLogger(VoterService.class);

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VoterImageRepository voterImageRepository;

    public void processUserData(MultipartFile file, VoterData request) {
        LOG.info("in service request: {}", request.getFirstName());
        LOG.info("file: {}", file.getOriginalFilename());

        try {

            Voter voter = new Voter();
            voter.setFirstName(request.getFirstName());
            voter.setLastName(request.getLastName());
            voter = voterRepository.save(voter);


            VoterImage image = new VoterImage();
            byte[] imageBytes = file.getBytes();
            image.setImageData(imageBytes);
            image.setName(file.getOriginalFilename());
            image.setVoterId(voter.id());
            voterImageRepository.save(image);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    private UserData getJson(String request, MultipartFile file) {
//    }
}
