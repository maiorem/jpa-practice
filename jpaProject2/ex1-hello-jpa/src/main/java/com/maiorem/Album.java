package com.maiorem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") //안쓰면 디폴트로 엔티티명
public class Album extends Item {

    private String artist;

}
