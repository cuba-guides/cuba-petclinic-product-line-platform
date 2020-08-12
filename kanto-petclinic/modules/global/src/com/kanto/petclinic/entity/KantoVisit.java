package com.kanto.petclinic.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Extends(Visit.class)
@Entity(name = "kantopetclinic_KantoVisit")
@DiscriminatorValue("KantoVisit")
public class KantoVisit extends Visit {

    @Column(name = "PREVIOUS_ILLNESSES", length = 4000)
    private String previousIllnesses;

    public String getPreviousIllnesses() {
        return previousIllnesses;
    }

    public void setPreviousIllnesses(String previousIllnesses) {
        this.previousIllnesses = previousIllnesses;
    }
}