-- Database setup for ColibaoCRUD application
-- Execute these commands in your MySQL database

-- Create the database
CREATE DATABASE IF NOT EXISTS colibao;
USE colibao;

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL UNIQUE,
    userPassword VARCHAR(100) NOT NULL,
    userLevel VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert sample data (optional)
INSERT INTO users (userName, userPassword, userLevel) VALUES 
('admin', 'admin123', 'Admin'),
('guest1', 'guest123', 'Guest'),
('user1', 'user123', 'Guest');

-- Show the table structure
DESCRIBE users;

-- Show the sample data
SELECT * FROM users;