create table COMPETITION
(
    ID bigint generated by default as identity primary key,
    COMPETITION_TYPE varchar(10),
    NAME varchar(30)
);

create table TEAM
(
    ID bigint generated by default as identity primary key,
    NAME varchar(30)
);

create table COMPETITION_TEAMS 
(
    COMPETITION bigint, 
    TEAM bigint
);

create table FIXTURE
(
    ID bigint generated by default as identity primary key,
    COMPETITION bigint,
    HOME_TEAM bigint,
    AWAY_TEAM bigint,
    MATCH_DATE date
);

create table PLAYER
(
    ID bigint generated by default as identity primary key,
    TEAM bigint,
    FIRST_NAME varchar(30),
    LAST_NAME varchar(30),
    PHONE_NUMBER varchar(20)
);

create table RESULT
(
    ID bigint generated by default as identity primary key,
    FIXTURE bigint,
    HOME_SCORE varchar(10),
    AWAY_SCORE varchar(10)
);

create table USER
(
    ID bigint generated by default as identity primary key,
    EMAIL varchar(30),
    FIRST_NAME varchar(30),
    LAST_NAME varchar(30),
    AGE bigint
);