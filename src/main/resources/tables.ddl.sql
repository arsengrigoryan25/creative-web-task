# ======================================================================================================================
#   CREATE DATABASE
# ======================================================================================================================
DROP DATABASE creative_web_task;
CREATE DATABASE creative_web_task CHARACTER SET utf8 COLLATE utf8_general_ci;
USE creative_web_task;

# ======================================================================================================================
#   CREATE TABLE
# ======================================================================================================================
DROP TABLE comments;
DROP TABLE notification;
# -----------------------------------------------

create table comments
(
    id             	bigint auto_increment primary key,
    comment         varchar(255),
    time   			timestamp default current_timestamp()
);


create table notification
(
    id             	bigint auto_increment primary key,
    comment_id     	bigint not null,
    time   			timestamp default current_timestamp(),
    delivered 		BOOLEAN
);