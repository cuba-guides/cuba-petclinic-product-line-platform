package com.alabastia.petclinic.web.screens.treatmentroom;

import com.haulmont.cuba.gui.screen.*;
import com.alabastia.petclinic.entity.TreatmentRoom;

@UiController("alabastiapetclinic_TreatmentRoom.browse")
@UiDescriptor("treatment-room-browse.xml")
@LookupComponent("treatmentRoomsTable")
@LoadDataBeforeShow
public class TreatmentRoomBrowse extends StandardLookup<TreatmentRoom> {
}