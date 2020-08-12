package com.kanto.petclinic.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitType;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(VisitCreationService.NAME)
public class VisitCreationServiceBean implements VisitCreationService {

    @Inject
    protected DataManager dataManager;

    @Inject
    protected TimeSource timeSource;

    @Override
    public Visit createVisitForPet(Pet pet, VisitType visitType) {

        final Visit visitForPet = dataManager.create(Visit.class);

        visitForPet.setPet(pet);
        visitForPet.setVisitStart(timeSource.now().toLocalDateTime());
        visitForPet.setVisitEnd(timeSource.now().toLocalDateTime().plusHours(1));

        visitForPet.setType(visitType);

        return dataManager.commit(visitForPet);
    }
}