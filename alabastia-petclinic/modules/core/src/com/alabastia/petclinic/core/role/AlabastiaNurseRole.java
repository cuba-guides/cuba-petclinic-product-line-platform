package com.alabastia.petclinic.core.role;

import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.sample.petclinic.core.role.NurseRole;
import com.haulmont.sample.petclinic.entity.owner.Owner;
import com.haulmont.sample.petclinic.entity.pet.Pet;
import com.haulmont.sample.petclinic.entity.pet.PetType;
import com.haulmont.sample.petclinic.entity.veterinarian.Specialty;
import com.haulmont.sample.petclinic.entity.veterinarian.Veterinarian;
import com.haulmont.sample.petclinic.entity.visit.Visit;
import com.alabastia.petclinic.entity.DefaultTreatmentRoom;
import com.alabastia.petclinic.entity.TreatmentRoom;

@Role(name = NurseRole.NAME)
public class AlabastiaNurseRole extends NurseRole {

    @EntityAccess(entityClass = Visit.class, operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Pet.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Owner.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = PetType.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Specialty.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = Veterinarian.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = User.class, operations = {EntityOp.READ})

    @EntityAccess(entityClass = TreatmentRoom.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = DefaultTreatmentRoom.class, operations = {EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }


    @EntityAttributeAccess(entityClass = Owner.class, view = "*")
    @EntityAttributeAccess(entityClass = Pet.class, view = "*")
    @EntityAttributeAccess(entityClass = Visit.class, modify = "*")
    @EntityAttributeAccess(entityClass = PetType.class, view = "*")
    @EntityAttributeAccess(entityClass = Specialty.class, view = "*")
    @EntityAttributeAccess(entityClass = Veterinarian.class, view = "*")
    @EntityAttributeAccess(entityClass = User.class, view = "*")
    @EntityAttributeAccess(entityClass = Visit.class, modify = "pet") // TODO: remove when https://github.com/cuba-platform/cuba/issues/2869 is solved

    @EntityAttributeAccess(entityClass = TreatmentRoom.class, view = "*")
    @EntityAttributeAccess(entityClass = DefaultTreatmentRoom.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }


    @ScreenAccess(screenIds = {
        "petclinic_myVisits",
        "petclinic_Visit.browse",
        "petclinic_Visit.edit",
        "petclinic_Pet.browse",
        "petclinic_Pet.edit",
        "petclinic_Owner.browse",
        "petclinic_Owner.edit",
        "petclinic_Veterinarian.browse",
        "petclinic_Veterinarian.edit",
        "petclinic_PetType.browse",
        "petclinic_PetType.edit",
        "petclinic_Specialty.browse",
        "petclinic_Specialty.lookup",
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
