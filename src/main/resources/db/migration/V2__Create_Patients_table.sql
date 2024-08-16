CREATE TABLE PATIENTS (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          phone VARCHAR(20),
                          cpf VARCHAR(20) NOT NULL,
                          address VARCHAR(255),
                          birth_date DATE
);