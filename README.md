# CUBA Petclinic Product Customizations

<p align="center">
  <img src="https://github.com/cuba-platform/cuba-petclinic/blob/master/img/petclinic_logo_with_slogan.svg"/>
</p>

This example shows how to use CUBA's application components in order to build customizations to the Petclinic product on a customer-by-customer basis.

### Repository Structure

#### Petclinic Core
The [petclinic-core](/petclinic-core) directory contains the regular CUBA Petclinic project. In order to act as an application component the [app-component.xml](/petclinic-core/modules/global/src/com/haulmont/sample/petclinic/app-component.xml) has been created (via CUBA studio).

#### Lancaster Petclinic

The [lancaster-petclinic](/lancaster-petclinic) project is a CUBA application, that uses the Petclinic as a basis. It contains some customizations, that are described in the [README](/lancaster-petclinic/README.md) of the project.

The way it uses the petclinic-core is by adding it as a application-component in the [build.gradle](/lancaster-petclinic/build.gradle#L39).



#### Rocky Mount Petclinic

The [rocky-mount-petclinic](/rocky-mount-petclinic) project is a CUBA application, that uses the Petclinic as a basis. It contains some customizations, that are described in the [README](/rocky-mount-petclinic/README.md) of the project.

The way it uses the petclinic-core is by adding it as a application-component in the [build.gradle](/rocky-mount/build.gradle#L37).

### Running Locally

In order to run the different applications locally, the petclinic-core project has to be installed into the local Maven repository. This can be achieved via running the shell script `./install-petclinic-core.sh`. Alternatively the petclinic-core project can be opened in CUBA Studio and installed from there via the Main Menu: `CUBA > Advanced > Install App Component`.
