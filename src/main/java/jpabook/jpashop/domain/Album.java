package jpabook.jpashop.domain;

import jakarta.persistence.Entity;

@Entity
public class Album extends Item {

    private String artiest;

    public String getArtiest() {
        return artiest;
    }

    public void setArtiest(String artiest) {
        this.artiest = artiest;
    }
}
