
-- Receptionist Marta
insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, GROUP_NAMES, IP_MASK, SYS_TENANT_ID)
values ('5769d573-a189-70a1-896f-a5c367a5123e', 1, '2020-05-12 09:34:58', 'admin', '2020-05-12 09:34:58', null, null, null, 'marta', 'marta', '$2a$10$vHUHuVkGghGRmbDwYEP2L.R2DJY1aHV4iN72SrBrp2x94L5.5uV3m', 'bcrypt', 'Receptionist Marta', 'Receptionist', 'Marta', null, null, null, 'en', null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null, null, null);
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('7a5aa7c2-7aee-b5ba-ff87-f50d755ad351', 1, '2020-05-12 09:34:58', 'admin', '2020-05-12 09:34:58', null, null, null, '5769d573-a189-70a1-896f-a5c367a5123e', null, 'Receptionist');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('8ff5621d-1fef-31e1-1c49-ad076b3b13a8', 1, '2020-05-12 09:34:58', 'admin', '2020-05-12 09:34:58', null, null, null, '5769d573-a189-70a1-896f-a5c367a5123e', null, 'system-minimal');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('2ce1241a-5924-32ee-6152-02d530a552ae', 1, '2020-05-12 09:34:58', 'admin', '2020-05-12 09:34:58', null, null, null, '5769d573-a189-70a1-896f-a5c367a5123e', null, 'helium-theme-minimal');
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID, ROLE_NAME)
values ('2ce1241a-5924-32ee-6152-02d530cc52a1', 1, '2020-05-12 09:34:58', 'admin', '2020-05-12 09:34:58', null, null, null, '5769d573-a189-70a1-896f-a5c367a5123e', null, 'Employee');

