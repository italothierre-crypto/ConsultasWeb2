DROP TABLE IF EXISTS consulta;
DROP TABLE IF EXISTS paciente;
DROP TABLE IF EXISTS medico;

CREATE TABLE paciente (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(100),
    data_nascimento DATE,
    PRIMARY KEY (id)
);

CREATE TABLE medico (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    crm VARCHAR(20) NOT NULL UNIQUE,
    especialidade VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE consulta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    horario TIME NOT NULL,
    observacoes TEXT,
    paciente_id BIGINT NOT NULL,
    medico_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_consulta_paciente
        FOREIGN KEY (paciente_id)
        REFERENCES paciente(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_consulta_medico
        FOREIGN KEY (medico_id)
        REFERENCES medico(id)
        ON DELETE CASCADE
);