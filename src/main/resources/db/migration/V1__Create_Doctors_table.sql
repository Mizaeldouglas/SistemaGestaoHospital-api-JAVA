CREATE TABLE DOCTORS (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         phone VARCHAR(20),
                         crm VARCHAR(50) NOT NULL,
                         specialty VARCHAR(100) NOT NULL
);