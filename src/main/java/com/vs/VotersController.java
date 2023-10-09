package com.vs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/voter")
public class VotersController {

    private static final Logger LOG = LoggerFactory.getLogger(VotersController.class);

    @Autowired
    private VoterService voterService;

    @Autowired
    private VoterImageService voterImageService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createVoterDetails(@RequestPart("data") VoterData payload,
                                                              @RequestPart("file") MultipartFile file) {
//        UserData request = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            LOG.info("request pre read: {}", payload);
//            request = objectMapper.readValue(payload, UserData.class);
//            LOG.info("request post read: {}", request.toString());
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }

        voterService.processUserData(file, payload);
        return ResponseEntity.ok().body("success");
    }

    @GetMapping(value = "/downloadImage/{id}")
    public ResponseEntity<?> downloadVoterImage(@PathVariable(value = "id") Long id){

        VoterImage image = voterImageService.downloadImage(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.name() + "\"")
                .body(image.imageData());

    }
}
