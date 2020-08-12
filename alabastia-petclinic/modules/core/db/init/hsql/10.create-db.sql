-- begin ALABASTIAPETCLINIC_TREATMENT_ROOM
create table ALABASTIAPETCLINIC_TREATMENT_ROOM (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    NAME varchar(255),
    --
    ROOM_NUMBER varchar(255) not null,
    --
    primary key (ID)
)^
-- end ALABASTIAPETCLINIC_TREATMENT_ROOM
-- begin PETCLINIC_VISIT
alter table PETCLINIC_VISIT add column TREATMENT_ROOM_ID varchar(36) ^
alter table PETCLINIC_VISIT add column DTYPE varchar(31) ^
update PETCLINIC_VISIT set DTYPE = 'AlabastiaVisit' where DTYPE is null ^
-- end PETCLINIC_VISIT
-- begin ALABASTIAPETCLINIC_DEFAULT_TREATMENT_ROOM
create table ALABASTIAPETCLINIC_DEFAULT_TREATMENT_ROOM (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36) not null,
    TREATMENT_ROOM_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end ALABASTIAPETCLINIC_DEFAULT_TREATMENT_ROOM
