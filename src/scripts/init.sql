DROP TABLE reservation;
DROP TABLE customer;
DROP TABLE room;
DROP TABLE room_cost;
DROP TABLE facility;
DROP TABLE facility_booking;


CREATE TABLE room(
ID number(5),
Type varchar2(30) NOT NULL,
PRIMARY KEY (ID)
);

DROP SEQUENCE reservationSeq;
CREATE SEQUENCE reservationSeq START WITH 1;
DROP SEQUENCE customerSeq;
CREATE SEQUENCE customerSeq START WITH 1;

CREATE TABLE room_cost (
Type varchar2(30) NOT NULL,
Cost number(4) NOT NULL,
CONSTRAINT Type_Unique UNIQUE (Type)
);


CREATE TABLE customer (
ID number (5) NOT NULL,
Address varchar2(30) NOT NULL ,
Country varchar2(20) NOT NULL,
First_name varchar2(40) NOT NULL,
Last_name varchar2(40) NOT NULL,
Phone varchar2(10) NOT NULL,
Email varchar2(50) NOT NULL,
Travel_agency varchar2(50),
PRIMARY KEY (ID)
);


CREATE TABLE reservation (
ID number(5) NOT NULL,
Room_ID number(5) NOT NULL,
Customer_ID number(5) NOT NULL,
Checkin_date date NOT NULL,
Departure_date date NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (Room_ID) REFERENCES Room(ID),
FOREIGN KEY (Customer_ID) REFERENCES Customer
);

CREATE TABLE facility (
ID number(5) NOT NULL,
Name varchar2(20) NOT NULL,
Type varchar2(20) NOT NULL,
Capacity number NOT NULL,
has_waiting_list number(1),
has_booking number(1),
has_instructor number(1)
);

CREATE TABLE facility_booking (
ID number(5) NOT NULL,
FACILITY_ID number(5) NOT NULL,
BOOKING_DATE date NOT NULL,
TIMESLOT number(5) NOT NULL
);
