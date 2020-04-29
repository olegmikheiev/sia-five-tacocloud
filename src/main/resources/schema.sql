create table if not exists INGREDIENT (
  ID   varchar(4)  not null,
  NAME varchar(25) not null,
  TYPE varchar(10) not null
);

create table if not exists TACO (
  ID         identity,
  NAME       varchar(50) not null,
  CREATED_AT timestamp   not null
);

create table if not exists TACO_INGREDIENTS (
  TACO       bigint     not null,
  INGREDIENT varchar(4) not null
);

alter table TACO_INGREDIENTS
  add foreign key (TACO) references TACO (ID);
alter table TACO_INGREDIENTS
  add foreign key (INGREDIENT) references INGREDIENT (ID);

create table if not exists TACO_ORDER (
  ID              identity,
  DELIVERY_NAME   varchar(50) not null,
  DELIVERY_STREET varchar(50) not null,
  DELIVERY_CITY   varchar(50) not null,
  DELIVERY_STATE  varchar(2)  not null,
  DELIVERY_ZIP    varchar(10) not null,
  CC_NUMBER       varchar(16) not null,
  CC_EXPIRATION   varchar(5)  not null,
  CC_CVV          varchar(3)  not null,
  PLACED_AT       timestamp   not null
);

create table if not exists TACO_ORDER_TACOS (
  TACO_ORDER bigint not null,
  TACO       bigint not null
);

alter table TACO_ORDER_TACOS
  add foreign key (TACO_ORDER) references TACO_ORDER (ID);
alter table TACO_ORDER_TACOS
  add foreign key (TACO) references TACO (ID);
