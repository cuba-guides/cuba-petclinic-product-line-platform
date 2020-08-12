package com.kanto.petclinic.service;

import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitType;

public interface VisitCreationService {

    String NAME = "kantopetclinic_VisitCreationService";

    Visit createVisitForPet(Pet pet, VisitType visitType);
}