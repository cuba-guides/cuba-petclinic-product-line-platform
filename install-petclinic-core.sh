#!/bin/bash

echo "---------------------"
echo "Installing Petclinic Core to local maven repository"
echo "---------------------"

cd petclinic-core
./gradlew install

echo "---------------------"
echo "Petclinic Core install complete. Application can now use the application component"
echo "---------------------"
