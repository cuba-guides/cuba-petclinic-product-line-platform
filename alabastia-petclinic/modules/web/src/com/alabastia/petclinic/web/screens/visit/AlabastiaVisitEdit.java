package com.alabastia.petclinic.web.screens.visit;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EntityStates;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.sample.petclinic.web.screens.visit.VisitEdit;
import com.alabastia.petclinic.entity.DefaultTreatmentRoom;
import com.alabastia.petclinic.entity.AlabastiaVisit;
import com.alabastia.petclinic.entity.TreatmentRoom;
import java.util.Optional;
import javax.inject.Inject;

@UiController("petclinic_Visit.edit")
@UiDescriptor("alabastia-visit-edit.xml")
public class AlabastiaVisitEdit extends VisitEdit {

    @Inject
    protected DataManager dataManager;
    @Inject
    protected UserSession userSession;
    @Inject
    protected EntityStates entityStates;

    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {

        if (entityStates.isNew(getEditedEntity())) {
            getEditedEntity().setAssignedNurse(
                currentUser()
            );

            defaultTreatmentRoomFor(
                currentUser()
            ).ifPresent(defaultTreatmentRoom ->
                initTreatmentRoom(defaultTreatmentRoom.getTreatmentRoom())
            );
        }
    }

    private void initTreatmentRoom(TreatmentRoom treatmentRoom) {
        final AlabastiaVisit visit = (AlabastiaVisit) getEditedEntity();
        visit.setTreatmentRoom(treatmentRoom);
    }

    private User currentUser() {
        return userSession.getCurrentOrSubstitutedUser();
    }

    private Optional<DefaultTreatmentRoom> defaultTreatmentRoomFor(User user) {
        return dataManager
            .load(DefaultTreatmentRoom.class)
            .query("e.user = ?1", user)
            .view(viewBuilder ->
                viewBuilder.add("treatmentRoom", View.MINIMAL)
            )
            .optional();
    }


}