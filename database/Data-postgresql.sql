CREATE TABLE "user" (
    username VARCHAR(255) PRIMARY KEY,
    pass VARCHAR(255),
    mail VARCHAR(255),
    hoten VARCHAR(255),
    birthday DATE,
    gender VARCHAR(10),
    role INT DEFAULT 0 
);
CREATE TABLE pet (
    pet_id INT PRIMARY KEY,
    username VARCHAR(255) REFERENCES "user"(username),
    category VARCHAR(255),
    age INT,
    gender VARCHAR(10),
    color VARCHAR(255)
);
CREATE TABLE LichKham (
    time TIMESTAMPTZ PRIMARY KEY,
    state VARCHAR(3),
    pet_id INT REFERENCES pet(pet_id)
);
CREATE TABLE dvtrong_giu (
    chuong_id INT PRIMARY KEY,
    time INTERVAL,
    state VARCHAR(10),
    pet_id INT REFERENCES pet(pet_id)
);

CREATE TABLE dichvuVS (
    loai_dv VARCHAR(255),
    time INTERVAL,
    state VARCHAR(10),
    PRIMARY KEY (loai_dv, time)
);

CREATE TABLE thuoc (
    thuoc_id INT PRIMARY KEY,
    ten_thuoc VARCHAR(255),
    nhom_thuoc VARCHAR(255),
    soluong INT,
    NhaSX VARCHAR(255),
    HSD DATE
);
CREATE TABLE benhan (
    record_id SERIAL PRIMARY KEY,
    pet_id INT REFERENCES pet(pet_id),
    trieuchung TEXT,
    chuandoan TEXT,
    nhacnho TEXT
);

CREATE TABLE donthuoc (
    record_id INT REFERENCES benhan(record_id),
    thuoc_id INT REFERENCES thuoc(thuoc_id),
    soluong INT,
    time DATE,
    PRIMARY KEY (record_id, thuoc_id)
);


-- insert to user 





