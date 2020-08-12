### Alabastia Petclinic

The Alabastia Petclinic application is a tailored Petclinic towards the Alabastia Petclinic Inc.
which needs some adjustments over the standard Petclinic product.

In particular the following adjustments have been made:

### Functional Adjustments

#### Visit Creation

* a visit needs to be assigned to a treatment room
* the treatment room should be set to the default treatment room, that is assigned to the current user

#### Visit Management

* Visit Calendars default view is monthly, not weekly 

#### Workflow Adjustments

* at the Alabastia Petclinic there is a dedicated reception which deals with the process of onboarding new Owners and Pets

### Technical Adjustments

#### Data Model

In order to support those business requirements the data model has been adjusted accordingly:

* an entity `TreatmentRoom` has been added to model the rooms that are available within the Petclinic.
* an entity `DefaultTreatmentRoom` links a User to a Treatment Room

##### Visit Entity extension
The Visit entity has been extended to store a link to the Treatment Room:

```java
@Extends(Visit.class)
@Entity(name = "alabastiapetclinic_AlabastiaVisit")
@DiscriminatorValue("AlabastiaVisit")
public class AlabastiaVisit extends Visit {

    private static final long serialVersionUID = 430933193584847493L;

    @Lookup(type = LookupType.DROPDOWN, actions = {})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TREATMENT_ROOM_ID")
    private TreatmentRoom treatmentRoom;

    public TreatmentRoom getTreatmentRoom() {
        return treatmentRoom;
    }

    public void setTreatmentRoom(TreatmentRoom treatmentRoom) {
        this.treatmentRoom = treatmentRoom;
    }
}
```

#### Screen Extensions

the Visit Editor screen has been extend to place the `treatmentRoom` lookup field inside the form component:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<window xmlns="http://schemas.haulmont.com/cuba/screen/window.xsd"
  messagesPack="com.alabastia.petclinic.web.screens.visit"
  xmlns:ext="http://schemas.haulmont.com/cuba/window-ext.xsd"
  extends="com/haulmont/sample/petclinic/web/screens/visit/visit-edit.xml">
  <data>
    <collection id="treatmentRoomsDc" class="com.alabastia.petclinic.entity.TreatmentRoom"
      view="_minimal">
      <loader>
        <query>
          <![CDATA[select e from alabastiapetclinic_TreatmentRoom e]]>
        </query>
      </loader>
    </collection>
  </data>
  <layout>
    <form id="fieldGroup">
      <column id="column2">
        <lookupField ext:index="2" id="treatmentRoomField" property="treatmentRoom"
          optionsContainer="treatmentRoomsDc"/>
      </column>
    </form>
  </layout>
</window>
```

The lookup field is placed at the correct position within the form via the `ext:index="2"` XML attribute.

#### Security Extensions

In order to give the users access to the new Entities and screens, the design time roles have been extended in this project:

```java

@Role(name = NurseRole.NAME)
public class AlabastiaNurseRole extends NurseRole {

    @EntityAccess(entityClass = Visit.class, operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    // ...

    @EntityAccess(entityClass = TreatmentRoom.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = DefaultTreatmentRoom.class, operations = {EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }


    @EntityAttributeAccess(entityClass = Owner.class, modify = "*")
    // ...

    @EntityAttributeAccess(entityClass = TreatmentRoom.class, view = "*")
    @EntityAttributeAccess(entityClass = DefaultTreatmentRoom.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }


    @ScreenAccess(screenIds = {
        "petclinic_myVisits",
        // ...
        "petclinic_Specialty.edit",
        
        "application-alabastiapetclinic",
        "alabastiapetclinic_DefaultTreatmentRoom.browse",
        "alabastiapetclinic_DefaultTreatmentRoom.edit",
        "alabastiapetclinic_TreatmentRoom.browse",
        "alabastiapetclinic_TreatmentRoom.edit"
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

}
```


Instead of Nurses at Alabastia there is a new Role `Receptionist` that is responsible for managing Pets and Owners:

```java
package com.alabastia.petclinic.core.role;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.sample.petclinic.entity.owner.Owner;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.pet.PetType;
import com.haulmont.sample.petclinic.entity.veterinarian.Specialty;
import com.haulmont.sample.petclinic.entity.veterinarian.Veterinarian;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.alabastia.petclinic.entity.DefaultTreatmentRoom;
import com.alabastia.petclinic.entity.TreatmentRoom;

@Role(name = ReceptionistRole.NAME)
public class ReceptionistRole extends AnnotatedRoleDefinition {

    public final static String NAME = "Receptionist";

    @EntityAccess(entityClass = Pet.class, operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Owner.class, operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }


    @EntityAttributeAccess(entityClass = Owner.class, modify = "*")
    @EntityAttributeAccess(entityClass = Pet.class, modify = "*")
    @EntityAttributeAccess(entityClass = User.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }


    @ScreenAccess(screenIds = {
        "petclinic_Pet.browse",
        "petclinic_Pet.edit",
        "petclinic_Owner.browse",
        "petclinic_Owner.edit"
    })
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

}
```
