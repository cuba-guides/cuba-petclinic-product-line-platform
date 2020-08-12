package com.kanto.petclinic.web.screens.pet;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Notifications.NotificationType;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.app.core.inputdialog.InputDialog;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.InputDialogFacet.CloseEvent;
import com.haulmont.cuba.gui.screen.MessageBundle;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.entity.visit.VisitType;
import com.haulmont.sample.petclinic.web.pet.pet.PetBrowse;
import com.kanto.petclinic.service.VisitCreationService;
import javax.inject.Inject;

@UiController("petclinic_Pet.browse")
@UiDescriptor("kanto-pet-browse.xml")
public class KantoPetBrowse extends PetBrowse {

    @Inject
    protected GroupTable<Pet> petsTable;
    @Inject
    protected Notifications notifications;
    @Inject
    protected VisitCreationService visitCreationService;

    @Inject
    protected ScreenBuilders screenBuilders;
    @Inject
    protected MessageBundle messageBundle;

    @Subscribe("createVisitForPetDialog")
    protected void onCreateVisitForPetDialogClose(CloseEvent event) {
        if (event.getCloseAction().equals(InputDialog.INPUT_DIALOG_OK_ACTION)) {
            final VisitType visitType = (VisitType) event.getValues().get("visitType");

            final Visit createdVisit = visitCreationService
                .createVisitForPet(petsTable.getSingleSelected(), visitType);

            notifications.create(NotificationType.TRAY)
                            .withCaption(messageBundle.getMessage("visitCreated"))
                            .show();

            screenBuilders.editor(Visit.class, this)
                .editEntity(createdVisit)
                .withOpenMode(OpenMode.DIALOG)
                .show();

        }
    }
}