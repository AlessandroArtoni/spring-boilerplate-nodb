-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE person(
    id UUID NOT NULL PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR (100) NOT NULL
);