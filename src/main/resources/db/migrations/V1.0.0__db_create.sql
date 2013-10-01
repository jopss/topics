DROP TABLE IF EXISTS hibernate_sequences;
CREATE TABLE hibernate_sequences
(
  sequence_name varchar(255),
  sequence_next_hi_value integer
);

DROP TABLE IF EXISTS blacklist;
CREATE TABLE blacklist
(
  id bigint NOT NULL,
  datecreated timestamp ,
  dateupdated timestamp ,
  keyword varchar(255) NOT NULL,
  CONSTRAINT blacklist_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS topic;
CREATE TABLE topic
(
  id bigint NOT NULL,
  datecreated timestamp ,
  dateupdated timestamp ,
  description varchar(255) NOT NULL,
  CONSTRAINT topic_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS comment;
CREATE TABLE comment
(
  id bigint NOT NULL,
  datecreated timestamp ,
  dateupdated timestamp ,
  content varchar(255) NOT NULL,
  reference_id bigint,
  topic_id bigint,
  CONSTRAINT comment_pkey PRIMARY KEY (id),
  CONSTRAINT comment_topic_fk FOREIGN KEY (topic_id) REFERENCES topic (id),
  CONSTRAINT comment_comment_ref_fk FOREIGN KEY (reference_id) REFERENCES comment(id)
);