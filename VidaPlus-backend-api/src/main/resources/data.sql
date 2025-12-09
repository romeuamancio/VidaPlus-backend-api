INSERT INTO usuario (nome, email, senha, role)
VALUES ('Administrador', 'admin@clinic.com',
        '$2a$10$z7x4BRXca7ecxol2M41QvOBhTIy/JQonjDKe0durrdQKzyaqIQtbi',
        'ROLE_ADMIN');

-- exemplo de paciente (opcional)
INSERT INTO paciente (nome, cpf, data_nascimento, telefone, email)
VALUES ('Paciente Exemplo', '12345678900', '1990-01-01', '11999999999', 'paciente@clinic.com');
