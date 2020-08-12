package com.alabastia.petclinic.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.sample.petclinic.entity.NamedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NamePattern("%s|roomNumber")
@Table(name = "ALABASTIAPETCLINIC_TREATMENT_ROOM")
@Entity(name = "alabastiapetclinic_TreatmentRoom")
public class TreatmentRoom extends NamedEntity {

    private static final long serialVersionUID = 7329334080829581211L;


    @Column(name = "ROOM_NUMBER", nullable = false)
    private String roomNumber;

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}