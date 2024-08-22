show databases;
use hospital;
show tables;

select * from admin;
select * from adminlogin;
select * from doctors;
select * from nurses;
select * from departments;
select * from inventory;

select * from patients;
select * from patientslogin;
select * from appointments;
select * from reports;
select * from history;
drop table history;

 create table adminlogin(
		username varchar(30) unique not null,
        password varchar(30) unique not null 
);

CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    github_url VARCHAR(255) NOT NULL,
    linkedin_url VARCHAR(255) NOT NULL,
    instagram_url VARCHAR(255) NOT NULL,
    twitter_url VARCHAR(255) NOT NULL
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    specialization VARCHAR(100) NOT NULL
);

create table nurses(
	id int auto_increment primary key,
	name varchar(30), 
    age int,
    gender varchar(30),
    phone varchar(30), 
    email varchar(30), 
    address varchar(50),
    qualification varchar(50)
);

create table departments(
	id int auto_increment primary key,
	department_name varchar(50),
    head_of_department varchar(50),
    number_of_beds int
);

CREATE TABLE inventory (
    equipment_id INT PRIMARY KEY AUTO_INCREMENT,
    equipment_name VARCHAR(255) NOT NULL,
    specifications TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

ALTER TABLE inventory AUTO_INCREMENT = 10000;




-- Create the patients table
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT,
    report_id INT,
    patient_name VARCHAR(255) UNIQUE NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    phone_no VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    disease VARCHAR(255),
    doctor_name VARCHAR(255),
    date_of_appointment DATE,
    status VARCHAR(20),
    date_of_report DATE,
    report_info TEXT
);

-- Create the patientlogin table
CREATE TABLE patientslogin (
    patient_id INT auto_increment PRIMARY KEY ,
    patient_name VARCHAR(255) unique not null,
    password VARCHAR(255)
);


CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT not null,
    report_id INT,
    patient_name VARCHAR(255) not null,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    phone_no VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    disease VARCHAR(255) not null,
    doctor_name VARCHAR(255) not null,
    date_of_appointment DATE not null,
    status VARCHAR(20) default "pending",
    date_of_report DATE,
    report_info TEXT
);

alter table appointments auto_increment = 100;

CREATE TABLE reports (
	report_id INT auto_increment primary key,
    appointment_id INT not null,
    patient_id INT null,
    patient_name VARCHAR(255) not null,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    phone_no VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    disease VARCHAR(255) not null,
    doctor_name VARCHAR(255) not null,
    date_of_appointment DATE not null,
    status VARCHAR(20) not null,
    date_of_report DATE,
    report_info TEXT
);

ALTER TABLE reports AUTO_INCREMENT = 1000;

CREATE TABLE history (
	report_id INT,
    appointment_id INT unique,
    patient_id INT null,
    patient_name VARCHAR(255) not null,
    age INT NOT NULL,
    gender VARCHAR(20) NOT NULL,
    phone_no VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    disease VARCHAR(255),
    doctor_name VARCHAR(255),
    date_of_appointment DATE,
    status VARCHAR(20),
    date_of_report DATE,
    report_info TEXT
);




-- Insert Data into tables  

insert into adminlogin values
("karthik", "123456789"),
("admin", "admin123");

INSERT INTO admin (username, email, phone, github_url, linkedin_url, instagram_url, twitter_url) 
VALUES (
    'Karthik', 
    'jodukarthik25072@gmail.com', 
    '9390617667', 
    'https://github.com/KarthikJodu/', 
    'https://linkedin.com/in/karthik', 
    'https://www.instagram.com/merciful_s_servant', 
    'https://x.com/KarthikJodu'
),
(
    'admin', 
    'admin123@gmail.com', 
    '1234567890', 
    'https://github.com/admin', 
    'https://linkedin.com/in/admin', 
    'https://www.instagram.com/admin', 
    'https://x.com/admin'
);

INSERT INTO doctors (name, gender, specialization) VALUES 
('Karthik', 'Male', 'Cardiology'),
('Navadeep', 'Male', 'Neurology'),
('Chandu', 'Male', 'Orthopedics'),
('Chandana', 'Female', 'Pediatrics'), 
('Amit', 'Male', 'Dermatology'),
('Sita', 'Female', 'Gynecology'),
('Rohan', 'Male', 'Ophthalmology'),
('Priya', 'Female', 'Endocrinology'),
('Ravi', 'Male', 'Gastroenterology'),
('Maya', 'Female', 'Radiology'),
('John', 'Male', 'Urology'),
('Nina', 'Female', 'Anesthesiology'),
('Akhil', 'Male', 'Pulmonology'),
('Sunita', 'Female', 'Rheumatology'),
('Anand', 'Male', 'Hematology'),
('Geeta', 'Female', 'Oncology'),
('Rajesh', 'Male', 'Pathology'),
('Meera', 'Female', 'Allergy and Immunology'),
('Vikram', 'Male', 'Nephrology'),
('Lalitha', 'Female', 'Neonatology'),
('Ashok', 'Male', 'Emergency Medicine'),
('Divya', 'Female', 'Infectious Disease'),
('Suresh', 'Male', 'Internal Medicine'),
('Sneha', 'Female', 'Medical Genetics'),
('Manoj', 'Male', 'Endocrinology'),
('Rupa', 'Female', 'Neurology'),
('Vijay', 'Male', 'Orthopedics'),
('Preeti', 'Female', 'Pediatrics'),
('Kiran', 'Male', 'Psychiatry'),
('Anjali', 'Female', 'Pulmonology'),
('Rahul', 'Male', 'Radiology'),
('Anita', 'Female', 'Rheumatology'),
('Naveen', 'Male', 'Surgery'),
('Swati', 'Female', 'Geriatrics'),
('Tarun', 'Male', 'Cardiothoracic Surgery'),
('Nisha', 'Female', 'Obstetrics'),
('Arun', 'Male', 'Vascular Surgery'),
('Lakshmi', 'Female', 'Otolaryngology'),
('Gopal', 'Male', 'Dermatopathology'),
('Rani', 'Female', 'Genetics'),
('Harish', 'Male', 'Nuclear Medicine'),
('Rekha', 'Female', 'Pain Medicine'),
('Vikas', 'Male', 'Physical Medicine'),
('Shalini', 'Female', 'Plastic Surgery'),
('Ramesh', 'Male', 'Preventive Medicine'),
('Jyothi', 'Female', 'Radiation Oncology'),
('Naresh', 'Male', 'Rehabilitation'),
('Sindhu', 'Female', 'Sleep Medicine'),
('Ajay', 'Male', 'Sports Medicine'),
('Kavitha', 'Female', 'Thoracic Surgery');

INSERT INTO Nurses (name, age, gender, phone, email, address, qualification) VALUES
('Alice Johnson', 29, 'Female', '555-1234', 'alice.johnson@example.com', '123 Maple Street', 'RN'),
('Bob Smith', 35, 'Male', '555-5678', 'bob.smith@example.com', '456 Oak Avenue', 'BSN'),
('Carol White', 41, 'Female', '555-8765', 'carol.white@example.com', '789 Pine Road', 'MSN'),
('David Brown', 30, 'Male', '555-4321', 'david.brown@example.com', '101 Birch Lane', 'LPN'),
('Eva Green', 27, 'Female', '555-6789', 'eva.green@example.com', '202 Cedar Court', 'RN'),
('Frank Black', 33, 'Male', '555-9876', 'frank.black@example.com', '303 Elm Street', 'BSN'),
('Grace Blue', 40, 'Female', '555-5432', 'grace.blue@example.com', '404 Willow Way', 'MSN'),
('Henry Silver', 28, 'Male', '555-7654', 'henry.silver@example.com', '505 Aspen Place', 'LPN'),
('Ivy Gold', 32, 'Female', '555-6543', 'ivy.gold@example.com', '606 Redwood Drive', 'RN'),
('Jack Gray', 36, 'Male', '555-3456', 'jack.gray@example.com', '707 Cypress Circle', 'BSN'),
('Karen Orange', 29, 'Female', '555-1235', 'karen.orange@example.com', '808 Palm Street', 'MSN'),
('Larry Violet', 34, 'Male', '555-5679', 'larry.violet@example.com', '909 Spruce Avenue', 'LPN'),
('Mia Brown', 38, 'Female', '555-8767', 'mia.brown@example.com', '1010 Fir Road', 'RN'),
('Ned Yellow', 31, 'Male', '555-4322', 'ned.yellow@example.com', '111 Birch Lane', 'BSN'),
('Olive Green', 27, 'Female', '555-6790', 'olive.green@example.com', '2022 Cedar Court', 'MSN'),
('Paul Black', 35, 'Male', '555-9877', 'paul.black@example.com', '3033 Elm Street', 'LPN'),
('Quincy Red', 30, 'Male', '555-5433', 'quincy.red@example.com', '4044 Willow Way', 'RN'),
('Rita Blue', 39, 'Female', '555-7655', 'rita.blue@example.com', '5055 Aspen Place', 'BSN'),
('Sam White', 28, 'Male', '555-6544', 'sam.white@example.com', '6066 Redwood Drive', 'MSN'),
('Tina Brown', 33, 'Female', '555-3457', 'tina.brown@example.com', '7077 Cypress Circle', 'LPN'),
('Ursula Green', 37, 'Female', '555-1236', 'ursula.green@example.com', '8088 Palm Street', 'RN'),
('Victor Gray', 34, 'Male', '555-5670', 'victor.gray@example.com', '9099 Spruce Avenue', 'BSN'),
('Wendy Blue', 29, 'Female', '555-8768', 'wendy.blue@example.com', '10101 Fir Road', 'MSN'),
('Xander Silver', 32, 'Male', '555-4323', 'xander.silver@example.com', '11111 Birch Lane', 'LPN'),
('Yara Gold', 30, 'Female', '555-6791', 'yara.gold@example.com', '20222 Cedar Court', 'RN'),
('Zack Black', 36, 'Male', '555-9878', 'zack.black@example.com', '30333 Elm Street', 'BSN'),
('Anna White', 29, 'Female', '555-1237', 'anna.white@example.com', '40444 Willow Way', 'MSN'),
('Brian Brown', 38, 'Male', '555-5671', 'brian.brown@example.com', '50555 Aspen Place', 'LPN'),
('Clara Green', 31, 'Female', '555-8769', 'clara.green@example.com', '60666 Redwood Drive', 'RN'),
('Derek Blue', 28, 'Male', '555-4324', 'derek.blue@example.com', '70777 Cypress Circle', 'BSN'),
('Elaine Gray', 33, 'Female', '555-6792', 'elaine.gray@example.com', '80888 Palm Street', 'MSN'),
('Fred Silver', 37, 'Male', '555-9879', 'fred.silver@example.com', '90999 Spruce Avenue', 'LPN'),
('Gina Gold', 30, 'Female', '555-5434', 'gina.gold@example.com', '101010 Fir Road', 'RN'),
('Harry Black', 34, 'Male', '555-7656', 'harry.black@example.com', '111111 Birch Lane', 'BSN'),
('Isla White', 29, 'Female', '555-6545', 'isla.white@example.com', '202222 Cedar Court', 'MSN'),
('James Brown', 35, 'Male', '555-3458', 'james.brown@example.com', '303333 Elm Street', 'LPN'),
('Katie Green', 31, 'Female', '555-1238', 'katie.green@example.com', '404444 Willow Way', 'RN'),
('Leo Blue', 33, 'Male', '555-5672', 'leo.blue@example.com', '505555 Aspen Place', 'BSN'),
('Mona Gray', 28, 'Female', '555-8770', 'mona.gray@example.com', '606666 Redwood Drive', 'MSN'),
('Noah Silver', 32, 'Male', '555-4325', 'noah.silver@example.com', '707777 Cypress Circle', 'LPN'),
('Olivia Gold', 36, 'Female', '555-6793', 'olivia.gold@example.com', '808888 Palm Street', 'RN'),
('Peter Black', 31, 'Male', '555-9880', 'peter.black@example.com', '909999 Spruce Avenue', 'BSN'),
('Quinn White', 34, 'Female', '555-5435', 'quinn.white@example.com', '1010101 Fir Road', 'MSN'),
('Rachel Brown', 30, 'Female', '555-7657', 'rachel.brown@example.com', '1111111 Birch Lane', 'LPN'),
('Steve Green', 38, 'Male', '555-6546', 'steve.green@example.com', '2022222 Cedar Court', 'RN'),
('Tara Blue', 35, 'Female', '555-3459', 'tara.blue@example.com', '3033333 Elm Street', 'BSN'),
('Ulysses Gray', 29, 'Male', '555-1239', 'ulysses.gray@example.com', '4044444 Willow Way', 'MSN');

INSERT INTO departments (department_name, head_of_department, number_of_beds) VALUES 
('Cardiology', 'Dr. John Doe', 50),
('Neurology', 'Dr. Jane Smith', 45),
('Pediatrics', 'Dr. Emily Johnson', 30),
('Oncology', 'Dr. Michael Brown', 40),
('Orthopedics', 'Dr. Elizabeth Davis', 35),
('Gastroenterology', 'Dr. David Wilson', 25),
('Dermatology', 'Dr. Jennifer Moore', 20),
('Endocrinology', 'Dr. Robert Taylor', 15),
('Hematology', 'Dr. Patricia Anderson', 22),
('Nephrology', 'Dr. Charles Thomas', 18),
('Urology', 'Dr. Linda Jackson', 17),
('Pulmonology', 'Dr. Christopher White', 29),
('Rheumatology', 'Dr. Barbara Harris', 27),
('Ophthalmology', 'Dr. James Clark', 19),
('Gynecology', 'Dr. Mary Lewis', 24),
('Geriatrics', 'Dr. William Walker', 23),
('Psychiatry', 'Dr. Richard Hall', 28),
('Immunology', 'Dr. Thomas Allen', 21),
('Radiology', 'Dr. Susan Young', 33),
('Anesthesiology', 'Dr. Betty King', 16),
('Pathology', 'Dr. Donald Wright', 31),
('Allergy and Immunology', 'Dr. Karen Scott', 26),
('Infectious Disease', 'Dr. Nancy Green', 32),
('Occupational Therapy', 'Dr. Margaret Adams', 15),
('Physical Therapy', 'Dr. Ronald Baker', 34),
('Rehabilitation', 'Dr. Kimberly Nelson', 30),
('Plastic Surgery', 'Dr. Steven Carter', 22),
('Palliative Care', 'Dr. Sandra Mitchell', 17),
('Emergency Medicine', 'Dr. Laura Perez', 25),
('Sports Medicine', 'Dr. Kevin Roberts', 14),
('Pain Management', 'Dr. Donna Turner', 20),
('Sleep Medicine', 'Dr. Lisa Phillips', 21),
('Critical Care Medicine', 'Dr. Charles Campbell', 27),
('Occupational Medicine', 'Dr. Sandra Parker', 23),
('Family Medicine', 'Dr. Karen Evans', 28),
('General Surgery', 'Dr. Eric Edwards', 35),
('Vascular Surgery', 'Dr. Sarah Collins', 29),
('Thoracic Surgery', 'Dr. Mark Stewart', 26),
('Transplant Surgery', 'Dr. Michelle Morris', 24),
('Trauma Surgery', 'Dr. Laura Rogers', 33),
('Maxillofacial Surgery', 'Dr. Lisa Reed', 19),
('Bariatric Surgery', 'Dr. Daniel Cook', 18),
('Colorectal Surgery', 'Dr. Brian Morgan', 22),
('Pediatric Surgery', 'Dr. Patricia Bell', 27),
('Hepatobiliary Surgery', 'Dr. Angela Murphy', 25),
('Neurosurgery', 'Dr. Cynthia Bailey', 30),
('Hand Surgery', 'Dr. Dorothy Rivera', 16),
('Breast Surgery', 'Dr. Amy Cooper', 20),
('Cardiothoracic Surgery', 'Dr. Amanda Richardson', 28);

INSERT INTO inventory (equipment_name, specifications, price) VALUES 
('Sphygmomanometer', 'Manual blood pressure monitor with cuff, gauge, and stethoscope', 45.99),
('Stethoscope', 'Lightweight, dual-head, with acoustic tubing and soft ear tips', 65.50),
('Thermometer', 'Digital, fast-read, waterproof, with LCD display', 15.75),
('Pulse Oximeter', 'Portable fingertip, with LED display and battery', 29.99),
('Glucometer', 'Compact blood glucose monitoring system with test strips', 50.00),
('ECG Machine', 'Portable, 12-lead, with interpretation software', 2999.99),
('Defibrillator', 'Automated External Defibrillator (AED), battery-operated', 1499.99),
('Nebulizer', 'Portable, ultrasonic, with masks and mouthpieces', 89.99),
('Oxygen Concentrator', '5L, with humidifier bottle and nasal cannula', 699.99),
('Wheelchair', 'Manual, lightweight, foldable, with footrests', 249.99),
('Hospital Bed', 'Electric, adjustable, with side rails and mattress', 999.99),
('IV Stand', 'Stainless steel, adjustable height, with four hooks', 39.99),
('Surgical Table', 'Hydraulic, adjustable height, with tilting capability', 3999.99),
('X-ray Machine', 'Digital, portable, with imaging software', 14999.99),
('Ultrasound Machine', 'Portable, color Doppler, with probes and printer', 9999.99),
('Suction Machine', 'Portable, with rechargeable battery and accessories', 199.99),
('Infusion Pump', 'Programmable, dual-channel, with alarm system', 799.99),
('Ventilator', 'Portable, with humidifier and battery backup', 7999.99),
('Exam Light', 'LED, adjustable, with flexible arm', 299.99),
('Syringe Pump', 'Compact, with infusion set and adjustable flow rate', 499.99),
('Autoclave', 'Portable, tabletop, with sterilization cycles and safety features', 1200.00),
('Patient Monitor', 'Multi-parameter, with ECG, SpO2, NIBP, and temperature', 2499.99),
('ECG Electrodes', 'Disposable, pre-gelled, with adhesive backing, box of 100', 45.00),
('Laryngoscope', 'Fiber optic, with interchangeable blades and handle', 150.00),
('Orthopedic Drill', 'Battery-operated, with variable speed and safety lock', 999.99),
('Anesthesia Machine', 'With ventilator, vaporizer, and monitoring system', 14999.99),
('Blood Bank Refrigerator', 'Upright, with temperature control and alarm system', 2999.99),
('Cautery Machine', 'Electrosurgical unit, with monopolar and bipolar modes', 1200.00),
('Patient Transfer Stretcher', 'Hydraulic, adjustable height, with side rails and IV pole', 2999.99),
('Diagnostic Set', 'Otoscope and ophthalmoscope, with rechargeable handle', 299.99);





-- Insert User Data

INSERT INTO patients (patient_name, age, gender, phone_no, address)
VALUES 
('Aarav Sharma', 45, 'Male', '9876543210', '123 MG Road, Mumbai, MH'),
('Aanya Verma', 34, 'Female', '9876543211', '456 Park Street, Kolkata, WB'),
('Rohan Gupta', 60, 'Male', '9876543212', '789 Brigade Road, Bangalore, KA'),
('Meera Desai', 28, 'Female', '9876543213', '101 Residency Road, Chennai, TN'),
('Karan Singh', 52, 'Male', '9876543214', '102 Juhu Beach, Mumbai, MH'),
('Neha Patel', 43, 'Female', '9876543215', '103 Connaught Place, Delhi, DL'),
('Vikram Rao', 39, 'Male', '9876543216', '104 Banjara Hills, Hyderabad, TS'),
('Priya Kumar', 25, 'Female', '9876543217', '105 Koregaon Park, Pune, MH'),
('Arjun Mehta', 48, 'Male', '9876543218', '106 Nehru Place, Delhi, DL'),
('Riya Bhatt', 37, 'Female', '9876543219', '107 Marine Drive, Mumbai, MH'),
('Aditya Joshi', 32, 'Male', '9876543220', '108 Rajarhat, Kolkata, WB'),
('Sneha Kapoor', 54, 'Female', '9876543221', '109 Salt Lake, Kolkata, WB'),
('Siddharth Chatterjee', 49, 'Male', '9876543222', '110 Indiranagar, Bangalore, KA'),
('Tanya Das', 26, 'Female', '9876543223', '111 T Nagar, Chennai, TN'),
('Rahul Sharma', 41, 'Male', '9876543224', '112 Powai, Mumbai, MH'),
('Anjali Reddy', 29, 'Female', '9876543225', '113 Gachibowli, Hyderabad, TS'),
('Ravi Nair', 61, 'Male', '9876543226', '114 Vashi, Navi Mumbai, MH'),
('Kavya Bhat', 30, 'Female', '9876543227', '115 Electronic City, Bangalore, KA'),
('Amit Yadav', 44, 'Male', '9876543228', '116 BTM Layout, Bangalore, KA'),
('Shweta Iyer', 33, 'Female', '9876543229', '117 Anna Nagar, Chennai, TN'),
('Rohit Roy', 36, 'Male', '9876543230', '118 Whitefield, Bangalore, KA'),
('Pooja Nambiar', 58, 'Female', '9876543231', '119 MG Road, Kochi, KL'),
('Ashwin Menon', 47, 'Male', '9876543232', '120 Andheri, Mumbai, MH'),
('Sakshi Kaul', 31, 'Female', '9876543233', '121 Lajpat Nagar, Delhi, DL'),
('Manish Agrawal', 50, 'Male', '9876543234', '122 Ballygunge, Kolkata, WB'),
('Divya Pillai', 27, 'Female', '9876543235', '123 Kalyan Nagar, Bangalore, KA'),
('Nikhil Malhotra', 42, 'Male', '9876543236', '124 Vasant Kunj, Delhi, DL'),
('Ritu Saxena', 35, 'Female', '9876543237', '125 Malabar Hill, Mumbai, MH'),
('Rajesh Khanna', 53, 'Male', '9876543238', '126 Poes Garden, Chennai, TN'),
('Shruti Sen', 40, 'Female', '9876543239', '127 Jayanagar, Bangalore, KA'),
('Harsh Vardhan', 38, 'Male', '9876543240', '128 Nariman Point, Mumbai, MH'),
('Ananya Bose', 55, 'Female', '9876543241', '129 DLF Phase 1, Gurgaon, HR'),
('Kunal Roy', 43, 'Male', '9876543242', '130 Sector 62, Noida, UP'),
('Maya Rathi', 24, 'Female', '9876543243', '131 Sector 17, Chandigarh, PB'),
('Arvind Singh', 57, 'Male', '9876543244', '132 Sector 50, Noida, UP'),
('Pallavi Joshi', 22, 'Female', '9876543245', '133 Koramangala, Bangalore, KA'),
('Gaurav Bhatia', 49, 'Male', '9876543246', '134 Golf Course Road, Gurgaon, HR'),
('Smita Salvi', 46, 'Female', '9876543247', '135 Church Street, Bangalore, KA'),
('Rakesh Jain', 31, 'Male', '9876543248', '136 LBS Road, Mumbai, MH'),
('Aishwarya Shetty', 33, 'Female', '9876543249', '137 Banjara Hills, Hyderabad, TS'),
('Rohit Deshmukh', 51, 'Male', '9876543250', '138 Carter Road, Mumbai, MH'),
('Sonali Sinha', 29, 'Female', '9876543251', '139 Sector 15, Gurgaon, HR'),
('Vivek Chawla', 27, 'Male', '9876543252', '140 Sector 29, Chandigarh, PB'),
('Anu Gupta', 38, 'Female', '9876543253', '141 HSR Layout, Bangalore, KA'),
('Kabir Khan', 34, 'Male', '9876543254', '142 Sarjapur Road, Bangalore, KA'),
('Sanya Kapoor', 45, 'Female', '9876543255', '143 Carter Road, Mumbai, MH'),
('Vijay Prasad', 56, 'Male', '9876543256', '144 Sion, Mumbai, MH'),
('Mira Nanda', 28, 'Female', '9876543257', '145 MG Road, Bangalore, KA');


INSERT INTO patientslogin (patient_name, password)
VALUES 
('Aarav Sharma', 'password123'),
('Aanya Verma', 'password123'),
('Rohan Gupta', 'password123'),
('Meera Desai', 'password123'),
('Karan Singh', 'password123'),
('Neha Patel', 'password123'),
('Vikram Rao', 'password123'),
('Priya Kumar', 'password123'),
('Arjun Mehta', 'password123'),
('Riya Bhatt', 'password123'),
('Aditya Joshi', 'password123'),
('Sneha Kapoor', 'password123'),
('Siddharth Chatterjee', 'password123'),
('Tanya Das', 'password123'),
('Rahul Sharma', 'password123'),
('Anjali Reddy', 'password123'),
('Ravi Nair', 'password123'),
('Kavya Bhat', 'password123'),
('Amit Yadav', 'password123'),
('Shweta Iyer', 'password123'),
('Rohit Roy', 'password123'),
('Pooja Nambiar', 'password123'),
('Ashwin Menon', 'password123'),
('Sakshi Kaul', 'password123'),
('Manish Agrawal', 'password123'),
('Divya Pillai', 'password123'),
('Nikhil Malhotra', 'password123'),
('Ritu Saxena', 'password123'),
('Rajesh Khanna', 'password123'),
('Shruti Sen', 'password123'),
('Harsh Vardhan', 'password123'),
('Ananya Bose', 'password123'),
('Kunal Roy', 'password123'),
('Maya Rathi', 'password123'),
('Arvind Singh', 'password123'),
('Pallavi Joshi', 'password123'),
('Gaurav Bhatia', 'password123'),
('Smita Salvi', 'password123'),
('Rakesh Jain', 'password123'),
('Aishwarya Shetty', 'password123'),
('Rohit Deshmukh', 'password123'),
('Sonali Sinha', 'password123'),
('Vivek Chawla', 'password123'),
('Anu Gupta', 'password123'),
('Kabir Khan', 'password123'),
('Sanya Kapoor', 'password123'),
('Vijay Prasad', 'password123'),
('Mira Nanda', 'password123');