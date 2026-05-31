Show databases;

use saudepro;

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo VARCHAR(30) NOT NULL
);

-- Tabela de médicos
CREATE TABLE IF NOT EXISTS medicos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE,
    telefone VARCHAR(15),
    email VARCHAR(100)
);


-- Inserir usuários no sistema
INSERT INTO usuario (nome, login, senha, tipo) VALUES
('Administrador', 'admin', 'admin', 'GERENTE'),
('João Silva', 'joao.silva', '123456', 'RECEPCIONISTA'),
('Maria Santos', 'maria.santos', '123456', 'MEDICO'),
('Carlos Oliveira', 'carlos.oliveira', '123456', 'RECEPCIONISTA'),
('Ana Paula Costa', 'ana.costa', '123456', 'MEDICO');

-- Inserir médicos no sistema
INSERT INTO medicos (nome, especialidade, crm, telefone, email) VALUES
('Dr. Carlos Alberto Silva', 'Cardiologia', 'CRM-SP 12345', '(11) 98765-4321', 'carlos.silva@saudepro.com'),
('Dra. Ana Maria Santos', 'Pediatria', 'CRM-RJ 23456', '(21) 98765-4322', 'ana.santos@saudepro.com'),
('Dr. Roberto Oliveira', 'Ortopedia', 'CRM-MG 34567', '(31) 98765-4323', 'roberto.oliveira@saudepro.com'),
('Dra. Patrícia Costa', 'Ginecologia', 'CRM-RS 45678', '(51) 98765-4324', 'patricia.costa@saudepro.com'),
('Dr. Fernando Lima', 'Dermatologia', 'CRM-PR 56789', '(41) 98765-4325', 'fernando.lima@saudepro.com'),
('Dra. Mariana Souza', 'Neurologia', 'CRM-SC 67890', '(47) 98765-4326', 'mariana.souza@saudepro.com'),
('Dr. Ricardo Pereira', 'Oftalmologia', 'CRM-BA 78901', '(71) 98765-4327', 'ricardo.pereira@saudepro.com'),
('Dra. Camila Rocha', 'Psiquiatria', 'CRM-PE 89012', '(81) 98765-4328', 'camila.rocha@saudepro.com'),
('Dr. André Almeida', 'Urologia', 'CRM-CE 90123', '(85) 98765-4329', 'andre.almeida@saudepro.com'),
('Dra. Luciana Ferreira', 'Endocrinologia', 'CRM-DF 01234', '(61) 98765-4330', 'luciana.ferreira@saudepro.com');