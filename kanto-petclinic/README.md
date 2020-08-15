### Kanto Petclinic

The Kanto Petclinic application is a tailored Petclinic towards the Kanto Petclinic Inc. which needs some adjustments over the standard Petclinic product.

In particular the following adjustments have been made:

### Functional Adjustments

#### Visit Creation

* Visits can be created directly from the Pet List
* When a new Visit is created previous Illnesses can be documented


### Technical Adjustments

#### Data Model

In order to support those business requirements the data model has been adjusted accordingly:

##### Visit Entity extension
The Visit entity has been extended to store previous Illnesses:

```java
@Extends(Visit.class)
@Entity(name = "kantopetclinic_KantoVisit")
@DiscriminatorValue("KantoVisit")
public class KantoVisit extends Visit {

    @Column(name = "PREVIOUS_ILLNESSES", length = 4000)
    private String previousIllnesses;

    public String getPreviousIllnesses() {
        return previousIllnesses;
    }

    public void setPreviousIllnesses(String previousIllnesses) {
        this.previousIllnesses = previousIllnesses;
    }
}
```

#### Screen Extensions

the Visit Editor screen has been extend to place the `treatmentRoom` lookup field inside the form component:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<window xmlns="http://schemas.haulmont.com/cuba/screen/window.xsd"
  messagesPack="com.kanto.petclinic.web.screens.visit"
  xmlns:ext="http://schemas.haulmont.com/cuba/window-ext.xsd"
  extends="com/haulmont/sample/petclinic/web/screens/visit/visit-edit.xml">
  <layout>
    <form id="form">
      <column id="column1">
        <textArea
          id="previousIllnessesField"
          rows="5"
          property="previousIllnesses"
          colspan="2"
          width="100%"/>
      </column>
    </form>
  </layout>
</window>
```

##### Create Visit for Pet

For creating a visit from the Pet browse screen, an extension for the Pet browse screen has been created:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<window xmlns="http://schemas.haulmont.com/cuba/screen/window.xsd"
  xmlns:c="http://schemas.haulmont.com/cuba/screen/jpql_condition.xsd"
  messagesPack="com.kanto.petclinic.web.screens.pet"
  xmlns:ext="http://schemas.haulmont.com/cuba/window-ext.xsd"
  extends="com/haulmont/sample/petclinic/web/pet/pet/pet-browse.xml">
  <facets>
    <inputDialog
      id="createVisitForPetDialog"
      caption="msg://createVisitForPetCaption"
      defaultActions="OK_CANCEL"
      onAction="petTable.createVisit"
    >
      <parameters>
        <enumParameter id="visitType"
          enumClass="com.haulmont.sample.petclinic.entity.visit.VisitType"
          caption="msg://visitType" />
      </parameters>
    </inputDialog>
  </facets>
  <layout>
    <groupTable id="petsTable">
      <actions>
        <action id="createVisit"
          trackSelection="true"
          caption="msg://createVisit"
          icon="USER_MD"
        />
      </actions>
      <buttonsPanel id="buttonsPanel">
        <button id="createVisitBtn" action="petsTable.createVisit"/>
      </buttonsPanel>
    </groupTable>
  </layout>
</window>
```

A input dialog facet is used for receiving the `visitType` from the user. In the Controller it uses a new business service that creates the visit:

```java
@UiController("petclinic_Pet.browse")
@UiDescriptor("kanto-pet-browse.xml")
public class KantoPetBrowse extends PetBrowse {

    @Inject
    protected GroupTable<Pet> petsTable;
    @Inject
    protected Notifications notifications;
    @Inject
    protected VisitCreationService visitCreationService;

    @Subscribe("createVisitForPetDialog")
    protected void onCreateVisitForPetDialogClose(CloseEvent event) {
        if (event.getCloseAction().equals(InputDialog.INPUT_DIALOG_OK_ACTION)) {
            final VisitType visitType = (VisitType) event.getValues().get("visitType");

            final Visit createdVisit = visitCreationService
                .createVisitForPet(petsTable.getSingleSelected(), visitType);
            notifications.create(NotificationType.TRAY)
                            .withCaption("created..." + createdVisit.getId())
                            .show();
        }
    }
}
```


#### Business Logic

A new Service has been created that allows to create a new Visit for a given Pet via the UI:

```java
public interface VisitCreationService {

    String NAME = "kantopetclinic_VisitCreationService";

    Visit createVisitForPet(Pet pet, VisitType visitType);
}
```

