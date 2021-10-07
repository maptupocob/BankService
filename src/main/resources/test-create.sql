create schema if not exists BANK;

set schema BANK;

create table if not exists CLIENTS
(
    ID   INT auto_increment,
    NAME VARCHAR,
    constraint CLIENTS_PK
        primary key (ID)
);

create unique index if not exists CLIENTS_ID_UINDEX
    on CLIENTS (ID);

create table if not exists ACCOUNTS
(
    ID        INT auto_increment,
    CLIENT_ID INT            not null,
    NUMBER    INT            not null,
    CURRENCY  VARCHAR(3)     not null,
    BALANCE   LONG default 0 not null,
    constraint ACCOUNTS_TO_CLIENT_FK
        foreign key (CLIENT_ID) references CLIENTS (ID)
            on update cascade on delete cascade,
    constraint ACCOUNTS_PK
        primary key (ID)
);

create unique index if not exists ACCOUNTS_ID_UINDEX
    on ACCOUNTS (ID);

create table if not exists CARDS
(
    ID         INT auto_increment,
    NUMBER     VARCHAR(25) not null,
    ACCOUNT_ID INT         not null,
    constraint CARDS_ACCOUNTS_ID_FK
        foreign key (ACCOUNT_ID) references ACCOUNTS (ID)
            on update cascade on delete cascade,
    constraint CARDS_PK
        primary key (ID)
);

create unique index if not exists CARDS_ID_UINDEX
    on CARDS (ID);



