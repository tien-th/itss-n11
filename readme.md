This is N11's project for itss abcd. 

MỌI NGƯỜI updaTE 2 câu query này vào db trên postgres nhé 

ALTER TABLE user
RENAME COLUMN mail to email;
ALTER TABLE pet
ADD name VARCHAR(255);


6-21-2023
ALTER TABLE lichkham DROP time ;
ALTER TABLE lichkham ADD day date NOT NULL;
ALTER TABLE lichkham ADD time int NOT NULL;

6-22-2023 

Drop table dvtrong_giu ;

CREATE TABLE dvtrong_giu (
chuong_id INT,
start_time TIMESTAMP,
end_time TIMESTAMP,
state VARCHAR(10),
pet_id INT REFERENCES pet(pet_id),
PRIMARY KEY (chuong_id, start_time, end_time)
);

6-22-23
drop table dichvuvs;
CREATE TABLE dichvuVS (
loai_dv VARCHAR(255),
day date,
time_slot INT,
state VARCHAR(10),
PRIMARY KEY (loai_dv, day, time_slot)
);

ALTER TABLE lichkham
RENAME COLUMN time TO time_slot;