package com.alabastia.petclinic.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.core.entity.annotation.Lookup;
import com.haulmont.cuba.core.entity.annotation.LookupType;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Extends(Visit.class)
@Entity(name = "alabastiapetclinic_AlabastiaVisit")
@DiscriminatorValue("AlabastiaVisit")
public class AlabastiaVisit extends Visit {

    private static final long serialVersionUID = 430933193584847493L;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TREATMENT_ROOM_ID")
    private TreatmentRoom treatmentRoom;

    public TreatmentRoom getTreatmentRoom() {
        return treatmentRoom;
    }

    public void setTreatmentRoom(TreatmentRoom treatmentRoom) {
        this.treatmentRoom = treatmentRoom;
    }
}