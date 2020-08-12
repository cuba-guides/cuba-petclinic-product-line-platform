package com.alabastia.petclinic.web.screens.defaulttreatmentroom;

import com.haulmont.cuba.gui.screen.*;
import com.alabastia.petclinic.entity.DefaultTreatmentRoom;

@UiController("alabastiapetclinic_DefaultTreatmentRoom.browse")
@UiDescriptor("default-treatment-room-browse.xml")
@LookupComponent("defaultTreatmentRoomsTable")
@LoadDataBeforeShow
public class DefaultTreatmentRoomBrowse extends StandardLookup<DefaultTreatmentRoom> {
}