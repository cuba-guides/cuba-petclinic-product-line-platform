### Alabastia Petclinic

The Alabastia Petclinic application is a tailored Petclinic towards the Alabastia Petclinic Inc.
which needs some adjustments over the standard Petclinic product.

In particular the following adjustments have been made:

#### Idea: Declarative DataManager Loading

In this branch the default treatment room in the UI is not loaded via `dataManager.load(DefaultTreatmentRoom.class).query("...").optional()`.

Instead the `dataCotainer` concept is utilized to define the loading of the optional instance
of the `DefaultTreatmentRoom` in a declarative XML based manner. This way, the java code is simplified in the controller. It only requires to define the parameter as well as pick up the value via `defaultTreatmentRoomDc.getItem()`.

#### The Reason: higher level abstraction

The reason behind it is the same which led to the introduction of the various screen facets like the `MessageDialogFacet`. It pushes the complexity of "how to load a particular entity" into the declarative XML part. The Java code then can just rely on the `defaultTreatmentRoomDc` to correctly fetched the data. This simplifies the java code and provides a higher level abstraction in the UI controller to work with.


#### Example: Default Treatment Room Lookup

The declarative definition of the instance container looks like this:


```xml
<instance id="defaultTreatmentRoomDc"
      class="com.alabastia.petclinic.entity.DefaultTreatmentRoom">
  <view extends="_local">
    <property name="treatmentRoom" view="_minimal" />
  </view>
  <loader id="defaultTreatmentRoomLc">
    <query>
      <![CDATA[select e from alabastiapetclinic_DefaultTreatmentRoom e where e.user = :currentUser]]>
    </query>
  </loader>
</instance>
```


The controller code is simplified as described above:

```java
public class AlabastiaVisitEdit extends VisitEdit {
    @Inject
    protected InstanceContainer<DefaultTreatmentRoom> defaultTreatmentRoomDc;
    @Inject
    protected InstanceLoader<DefaultTreatmentRoom> defaultTreatmentRoomLc;

    @Subscribe
    protected void onInit(InitEvent event) {
        // provide the declarative dataContainer with required parameter
        defaultTreatmentRoomLc.setParameter("currentUser", currentUser());
    }
    
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {

        if (entityStates.isNew(getEditedEntity())) {

            getEditedEntity().setAssignedNurse(
                currentUser()
            );

            // retrieve a possible configured default treatment room through declarative dataContainer
            final TreatmentRoom defaultTreatmentRoom =
                defaultTreatmentRoomDc.getItem().getTreatmentRoom();

            initTreatmentRoom(defaultTreatmentRoom);
        }
    }
}
```



### Current Problems with the declarative approach

Currently it works well for the happy path: the `instanceContainer` actually returns a value. The problem occurs when there is no result. For a regular `instanceContainer` this is not very often the case, because
normally an instance container is not used together with an associated data loader and a query.

But in this situation, the instance not being present by the query might be a valid business situation.

Currently, the `InstanceLoaderImpl` throws an exception in case the entity is not found:

```java
entity = getDataManager().load(loadContext);

if (entity == null) {
    throw new EntityAccessException(container.getEntityMetaClass(), entityId);
}
```


Therefore, it requires in the controller to catch this exception as well as explicitly trigger the dataLoader.load() in order to programmatically catch the exception.


### Proposed Solution

In order to make the usage of `dataContainer` as a declarative way of using the `dataManager` for this scenario work, it should be possible to mark an instance container as optional. This way when the `instanceContainer` is loaded and no result is found `getItem()` would either return null or alternatively an instance of `Optional<DefaultTreatmentRoom>`.

The resulting XML:



```xml
<instance id="defaultTreatmentRoomDc"
      optional="true"
      class="com.alabastia.petclinic.entity.DefaultTreatmentRoom">
  <view extends="_local">
    <property name="treatmentRoom" view="_minimal" />
  </view>
  <loader id="defaultTreatmentRoomLc">
    <query>
      <![CDATA[select e from alabastiapetclinic_DefaultTreatmentRoom e where e.user = :currentUser]]>
    </query>
  </loader>
</instance>
```
 
The corresponding controller code:

```java
public class AlabastiaVisitEdit extends VisitEdit {
    @Inject
    protected InstanceContainer<DefaultTreatmentRoom> defaultTreatmentRoomDc;
    @Inject
    protected InstanceLoader<DefaultTreatmentRoom> defaultTreatmentRoomLc;

    @Subscribe
    protected void onInit(InitEvent event) {
        // provide the declarative dataContainer with required parameter
        defaultTreatmentRoomLc.setParameter("currentUser", currentUser());
    }
    
    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {

        if (entityStates.isNew(getEditedEntity())) {

            getEditedEntity().setAssignedNurse(
                currentUser()
            );

            // retrieve a possible configured default treatment room through declarative dataContainer
            if (defaultTreatmentRoomDc.getItem() != null) {
               final TreatmentRoom defaultTreatmentRoom =
                               defaultTreatmentRoomDc.getItem().getTreatmentRoom();
               
               initTreatmentRoom(defaultTreatmentRoom); 
            }
        }
    }
}
```

With this non-exception-when-null behavior it would also not require to explicitly trigger the dataLoader load anymore and the `@LoadDataBeforeShow`