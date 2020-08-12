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
