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

