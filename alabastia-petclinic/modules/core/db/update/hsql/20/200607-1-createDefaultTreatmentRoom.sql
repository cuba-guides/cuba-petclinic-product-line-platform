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
);