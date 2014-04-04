DROP TABLE instructor_booking;
DROP TABLE instructor_sports;
DROP TABLE instructor;
DROP TABLE queue_facility;
DROP TABLE facility_booking;
DROP TABLE HOTEL_USER;
DROP TABLE unpaid_reservations;
DROP TABLE reservation;
DROP TABLE customer;
DROP TABLE room;
DROP TABLE room_cost;
DROP TABLE facility;


CREATE TABLE room(
ID number(5),
Type varchar2(30) NOT NULL,
PRIMARY KEY (ID)
);

DROP SEQUENCE reservationSeq;
CREATE SEQUENCE reservationSeq START WITH 1;
DROP SEQUENCE customerSeq;
CREATE SEQUENCE customerSeq START WITH 1;
DROP SEQUENCE facilitySeq;
CREATE SEQUENCE facilitySeq START WITH 1;
DROP SEQUENCE facility_bookingSeq;
CREATE SEQUENCE facility_bookingSeq START WITH 1;
DROP SEQUENCE hotel_userSeq;
CREATE SEQUENCE hotel_userSeq START WITH 1;
DROP SEQUENCE queue_facilitySeq;
CREATE SEQUENCE queue_facilitySeq START WITH 1;
DROP SEQUENCE instructorSeq;
CREATE SEQUENCE instructorSeq START WITH 1;
DROP SEQUENCE instructor_bookingSeq;
CREATE SEQUENCE instructor_bookingSeq START WITH 1;

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
has_instructor number(1),
PRIMARY KEY (ID)
);

CREATE TABLE HOTEL_USER (
ID number(5) PRIMARY KEY,
Username varchar(20) NOT NULL,
Psw varchar(20) NOT NULL,
Status varchar(20) NOT NULL,
Reservation_id number(5),
Spent number(5) NOT NULL,
FOREIGN KEY (Reservation_id) REFERENCES reservation(ID)
);

CREATE TABLE facility_booking (
ID number(5) NOT NULL,
FACILITY_ID number(5) NOT NULL,
BOOKING_DATE date NOT NULL,
TIMESLOT number(5) NOT NULL,
User_id number(5) NOT NULL,
PRIMARY KEY (ID),
FOREIGN KEY (FACILITY_ID) REFERENCES facility(ID),
FOREIGN KEY (User_id) REFERENCES HOTEL_USER(ID)
);

CREATE TABLE queue_facility(
ID number(5) NOT NULL,
USER_ID number(5) NOT NULL,
FACILITY_BOOKING_ID number(5) NOT NULL,
PRIMARY KEY(ID),
FOREIGN KEY (USER_ID) REFERENCES HOTEL_USER(ID),
FOREIGN KEY (FACILITY_BOOKING_ID) REFERENCES FACILITY_BOOKING(ID)
);


CREATE TABLE unpaid_reservations (
ID number(5) NOT NULL,
BOOKING_DATE date NOT NULL,
FOREIGN KEY (ID) REFERENCES reservation(ID)
);

CREATE TABLE instructor (
ID number(5) PRIMARY KEY,
user_id number(5) NOT NULL,
FOREIGN KEY (user_id) REFERENCES hotel_user(id)
); 

CREATE TABLE instructor_sports (
instructor_id number(5) NOT NULL,
type varchar(20),
FOREIGN KEY (instructor_id) REFERENCES instructor(ID)
);

CREATE TABLE instructor_booking(
id number(5) PRIMARY KEY,
facility_id number(5) NOT NULL,
instructor_id number(5) NOT NULL,
booked_date date NOT NULL,
timeslot number(5) NOT NULL,
FOREIGN KEY (instructor_id) REFERENCES instructor(ID),
FOREIGN KEY (facility_id) REFERENCES facility(ID)
);
