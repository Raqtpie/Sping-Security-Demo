CREATE DATABASE IF NOT EXISTS security_demo_db;

USE security_demo_db;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS permission;
DROP TABLE IF EXISTS role_permission;

CREATE TABLE IF NOT EXISTS user (
    id BIGINT PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status INT DEFAULT 0,
    del_flag INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS role (
    user_id BIGINT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS permission (
    id BIGINT PRIMARY KEY NOT NULL,
    url VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS role_permission (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL
);

-- 生成用户数据
INSERT INTO user (id, username, password)
VALUES (1, 'user1', '$2a$10$tKL9ZeqmqClYS0XRNrRKDeXWE.YGIK/Ap5kRVYsuV2FesYmjrohvK'),
       (2, 'user2', '$2a$10$pbv4B4Em/bhIegdFQsChy.Aro9Ysso6t7usy92IZa0P/UpeuJd8lm'),
       (3, 'user3', '$2a$10$qaNkhFCLX0K34BvOaF0rheb1YVmqPMPymhSC/sYY.FwzNLmF1ZDZC');

-- 生成角色数据
INSERT INTO role (user_id, name)
VALUES (1, 'ROLE_role1'),
       (2, 'ROLE_role2'),
       (3, 'ROLE_role3');

-- 生成权限数据
INSERT INTO permission (id, url)
VALUES (1, '/api/resource1'),
       (2, '/api/resource2'),
       (3, '/api/resource3');

-- 生成用户-角色关系数据
INSERT INTO user_role (user_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

-- 生成角色-权限关系数据
INSERT INTO role_permission (role_id, permission_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);
