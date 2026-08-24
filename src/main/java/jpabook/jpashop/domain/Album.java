package jpabook.jpashop.domain;

import jakarta.persistence.Entity;

@Entity
public class Album extends Item {

    private String artiest;
    private String etc;

    public String getArtiest() {
        return artiest;
    }

    public void setArtiest(String artiest) {
        this.artiest = artiest;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
