package com.alabastia.petclinic.web.screens.defaulttreatmentroom;

import com.haulmont.cuba.gui.screen.*;
import com.alabastia.petclinic.entity.DefaultTreatmentRoom;

@UiController("alabastiapetclinic_DefaultTreatmentRoom.edit")
@UiDescriptor("default-treatment-room-edit.xml")
@EditedEntityContainer("defaultTreatmentRoomDc")
@LoadDataBeforeShow
public class DefaultTreatmentRoomEdit extends StandardEditor<DefaultTreatmentRoom> {
}