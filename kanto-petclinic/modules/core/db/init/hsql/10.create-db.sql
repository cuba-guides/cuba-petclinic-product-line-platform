-- begin PETCLINIC_VISIT
alter table PETCLINIC_VISIT add column PREVIOUS_ILLNESSES varchar(4000) ^
alter table PETCLINIC_VISIT add column DTYPE varchar(31) ^
update PETCLINIC_VISIT set DTYPE = 'KantoVisit' where DTYPE is null ^
-- end PETCLINIC_VISIT
