package com.alabastia.petclinic.web.screens.visit;

import com.alabastia.petclinic.entity.AlabastiaVisit;
import com.alabastia.petclinic.entity.DefaultTreatmentRoom;
import com.alabastia.petclinic.entity.TreatmentRoom;
import com.haulmont.cuba.core.global.EntityAccessException;
import com.haulmont.cuba.core.global.EntityStates;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Notifications.NotificationType;
import com.haulmont.cuba.gui.Route;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.EditedEntityContainer;
import com.haulmont.cuba.gui.screen.LoadDataBeforeShow;
import com.haulmont.cuba.gui.screen.StandardEditor;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.haulmont.sample.petclinic.web.screens.visit.VisitEdit;
import javax.inject.Inject;

@UiController("petclinic_Visit.edit")
@UiDescriptor("alabastia-visit-edit.xml")
@EditedEntityContainer("visitDc")
@Route(value = "visits/edit", parentPrefix = "visits")
public class AlabastiaVisitEdit extends StandardEditor<AlabastiaVisit> {

    @Inject
    protected UserSession userSession;
    @Inject
    protected EntityStates entityStates;
    @Inject
    protected InstanceContainer<DefaultTreatmentRoom> defaultTreatmentRoomDc;
    @Inject
    protected InstanceLoader<DefaultTreatmentRoom> defaultTreatmentRoomLc;
    @Inject
    protected CollectionLoader<TreatmentRoom> treatmentRoomsDl;
    @Inject
    protected Notifications notifications;

    @Subscribe
    protected void onInit(InitEvent event) {
        // provide the declarative dataContainer with required parameter
        defaultTreatmentRoomLc.setParameter("currentUser", currentUser());
    }

    @Subscribe
    protected void onBeforeShow(BeforeShowEvent event) {
        treatmentRoomsDl.load();
    }


    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {

        if (entityStates.isNew(getEditedEntity())) {

            getEditedEntity().setAssignedNurse(
                currentUser()
            );
            try {
                defaultTreatmentRoomLc.load();

                // retrieve a possible configured default treatment room through declarative dataContainer
                final TreatmentRoom defaultTreatmentRoom =
                    defaultTreatmentRoomDc.getItem().getTreatmentRoom();

                initTreatmentRoom(defaultTreatmentRoom);
            }
            catch (EntityAccessException e) {
                notifications.create(NotificationType.TRAY)
                    .withCaption("Default Treatment Room not configured...")
                    .show();
            }
        }
    }

    private void initTreatmentRoom(TreatmentRoom treatmentRoom) {
        final AlabastiaVisit visit = (AlabastiaVisit) getEditedEntity();
        visit.setTreatmentRoom(treatmentRoom);
    }

    private User currentUser() {
        return userSession.getCurrentOrSubstitutedUser();
    }

}