CREATE  TABLE users (
  userid bigint NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  status varchar(10) NOT NULL DEFAULT 'ACTIVE' ,
  address VARCHAR(60) NOT NULL,
  email VARCHAR(40) NOT NULL,
  createdby varchar(20) NOT NULL DEFAULT 'SYSTEM' ,
  PRIMARY KEY (userid));

CREATE  TABLE roles (
  roleid bigint NOT NULL,
  rolename VARCHAR(45) NOT NULL ,
  PRIMARY KEY (roleid));
   
CREATE TABLE user_roles (
 -- user_role_id bigint NOT NULL AUTO_INCREMENT,
  userid bigint NOT NULL,
  roleid bigint NOT NULL,
  --PRIMARY KEY (user_role_id),
  PRIMARY KEY (userid,roleid),
  UNIQUE KEY uni_username_role (roleid,userid),
  CONSTRAINT fk_username FOREIGN KEY (userid) REFERENCES users (userid),
  CONSTRAINT fk_rolename FOREIGN KEY (roleid) REFERENCES roles (roleid));
  
INSERT INTO roles (roleid, rolename)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles (roleid, rolename)
VALUES (2, 'ROLE_USER');

/*INSERT INTO users(userid,username,password,enabled)
VALUES ('001','admin','$2a$04$WO3.omJY4auftFnYp.3cyesfBts0UZ74FPyrARvgGgzrRHVrfeSP6', true);
INSERT INTO users(userid,username,password,enabled)
VALUES ('002','user','$2a$04$cMVQUCh8LUmShi68Ifisbu984LF3Jc3wdshQ8epnZTMCAf.dP9WGS', true);
 
INSERT INTO user_roles (userid, role)
VALUES ('002', 'ROLE_USER');
INSERT INTO user_roles (userid, role)
VALUES ('001', 'ROLE_ADMIN');
INSERT INTO user_roles (userid, role)
VALUES ('001', 'ROLE_USER');*/