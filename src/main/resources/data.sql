INSERT INTO ROLE (name) VALUES('admin');
INSERT INTO ROLE (name) VALUES('user');
INSERT INTO USER( name, email, password, role_id) VALUES ( 'admin', 'admin@boot.com', '$2a$10$pi9RrYoFQZSN6cu/GGYPIe6wDfsTtFfpCJvwUVV5ESsKCTAk80RQa', 1);
INSERT INTO USER( name, email, password, role_id) VALUES ( 'developer', 'dev@boot.com', '$2a$10$pi9RrYoFQZSN6cu/GGYPIe6wDfsTtFfpCJvwUVV5ESsKCTAk80RQa', 2);