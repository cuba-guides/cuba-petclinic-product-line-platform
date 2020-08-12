package com.alabastia.petclinic.web.screens.treatmentroom;

import com.haulmont.cuba.gui.screen.*;
import com.alabastia.petclinic.entity.TreatmentRoom;

@UiController("alabastiapetclinic_TreatmentRoom.edit")
@UiDescriptor("treatment-room-edit.xml")
@EditedEntityContainer("treatmentRoomDc")
@LoadDataBeforeShow
public class TreatmentRoomEdit extends StandardEditor<TreatmentRoom> {
}