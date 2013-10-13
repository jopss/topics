
CREATE TABLE permission
(
  id bigint NOT NULL,
  datecreated timestamp,
  dateupdated timestamp,
  active boolean NOT NULL,
  "role" varchar(255) NOT NULL,
  CONSTRAINT permission_pkey PRIMARY KEY (id)
);

CREATE TABLE topic_user (
    id bigint not null, 
    dateCreated timestamp, 
    dateUpdated timestamp, 
    active boolean not null, 
    password varchar(255) not null, 
    username varchar(255) not null, 
    permission_id bigint, 
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT user_permission_fk FOREIGN KEY (permission_id) REFERENCES permission (id)
);

INSERT INTO permission (id,datecreated,dateupdated,active,"role") VALUES
(1,'2013-09-26',null,true,'ROLE_USER');

INSERT INTO topic_user (id,datecreated,dateupdated,active,password,username,permission_id) VALUES
(1,'2013-09-26',null,true,'e10adc3949ba59abbe56e057f20f883e','admin',1);