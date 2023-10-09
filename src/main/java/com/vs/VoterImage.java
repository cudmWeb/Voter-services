package com.vs;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class VoterImage {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Lob
    @Column(name = "IMAGE_DATA")
    private byte[] imageData;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VOTER_ID")
    private Long voterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOTER_ID", insertable=false, updatable=false)
    private Voter voter;

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] imageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Long voterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
