DROP TABLE book;
DROP TABLE person;

CREATE TABLE person (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR (100) UNIQUE,
    year INT CHECK (year > 1900 AND year <2020)
);

CREATE TABLE book (
    id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title VARCHAR (100),
    author VARCHAR (30),
    year INT CHECK (year >= 0 AND year <= 2020 ),
    person_id INT REFERENCES person(id) ON DELETE SET NULL,
    issuing_time TIMESTAMP
);
