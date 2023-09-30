
ALTER TABLE pacientes DROP COLUMN urbanización, DROP COLUMN codigoPostal, DROP COLUMN provincia;
ALTER TABLE pacientes ADD COLUMN calle VARCHAR(25);