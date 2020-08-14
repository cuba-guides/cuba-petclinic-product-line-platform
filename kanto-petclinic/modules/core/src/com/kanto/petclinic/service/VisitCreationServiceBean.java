package com.kanto.petclinic.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitType;
import java.time.LocalDateTime;
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
        visitForPet.setType(visitType);

        final LocalDateTime now = timeSource.now().toLocalDateTime();
        visitForPet.setVisitStart(now);
        visitForPet.setVisitEnd(now.plusHours(1));

        return dataManager.commit(visitForPet);
    }
}