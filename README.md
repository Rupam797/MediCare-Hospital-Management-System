# 🏥 MediCare - Hospital Management System

MediCare is a state-of-the-art desktop-based **Hospital Management System (HMS)** built with Java Swing and AWT. It features a premium, centralized **Dark Mode Design System** that provides a modern, responsive, and intuitive user experience for hospital operators, front-desk administrators, and management staff.

---

## ✨ Features

-   **🔑 Secure User Authentication**: A modern login interface (`Login.java`) connected to a MySQL database backend.
-   **📊 Reception Dashboard**: A central dashboard (`Reception.java`) displaying real-time metrics (patient counts, available rooms, department counts, active ambulances) and providing quick-action navigation.
-   **📝 New Patient Registration**: Form-based interface to register patients (`NEW_PATIENT.java`), assign available rooms, register ID credentials, record current disease details, and accept security deposits.
-   **🛏️ Room Management**: Comprehensive lists and filters to search for room availability, room clean-status (Clean/Dirty), and nightly rates (`Room.java`, `Search_room.java`).
-   **🔄 Live Bill Estimation & Update**: Dynamic calculation of pending balances based on room pricing and amount already deposited (`Update_patient_details.java`).
-   **🚪 Patient Discharge**: Checkout management (`Patient_discharge.java`) that calculates final balances, releases the patient, and automatically updates room status back to *Available*.
-   **💼 Staff & Doctor Information**: Database search of all doctors, nurses, and hospital administrators (`Employee_info.java`).
-   **🚒 Emergency Ambulance Services**: Tracking and management of ambulance vehicle statuses and driver assignments (`Ambulance.java`).
-   **🏢 Department Directories**: View hospital departments and contact details (`Department.java`).

---

## 🎨 Design System & UI Aesthetics

MediCare is styled using a custom, centralized design engine located in [UITheme.java](file:///c:/Users/ASUS/Desktop/MediCare-Hospital-Management-System/src/hospital/management/system/UITheme.java). Key features include:

-   **Modern Dark Palette**: Deep blues, slate grays, vibrant primary accents, and clean text hierarchies.
-   **Smooth UI Elements**: Custom buttons with hover animations, flat borders, rounded text fields, and styled checkboxes.
-   **Custom Table Rendering**: High-performance JTables featuring alternating row highlights, custom scrollbars, and colored headers.
-   **High-DPI Emojis**: Crisp UI labels using system-supported iconography for navigation and headers.

---

## 📂 Project Structure

```text
MediCare-Hospital-Management-System/
├── .idea/                             # IntelliJ project configurations
├── src/                               # Source files
│   ├── hospital/management/system/    # Main application package
│   │   ├── Ambulance.java             # Ambulance management GUI
│   │   ├── Conn.java                  # JDBC Database connection helper
│   │   ├── Department.java            # Department details GUI
│   │   ├── Employee_info.java         # Employee information GUI
│   │   ├── Login.java                 # Login Authentication frame (Main entry)
│   │   ├── NEW_PATIENT.java           # Patient registration form
│   │   ├── Patient_discharge.java     # Patient checkout/discharge manager
│   │   ├── Patient_info.java          # Tabular patient search GUI
│   │   ├── Reception.java             # Main control dashboard
│   │   ├── Room.java                  # Room overview directory
│   │   ├── Search_room.java           # Advanced room filter/search GUI
│   │   ├── UITheme.java               # Centralized Dark Mode UI engine
│   │   └── Update_patient_details.java# Patient bill/details update GUI
│   └── icon/                          # Graphic assets and images
│       ├── dr.png                     # Dashboard visuals
│       └── logo1.png                  # Hospital logo
└── Hospital management system.iml     # Eclipse/IntelliJ module configuration file
```

---

## 🗄️ Database Setup

The project uses a MySQL database named `hospital_management_system`. Below is the SQL script to initialize the tables and database:

```sql
-- 1. Create the Database
CREATE DATABASE IF NOT EXISTS hospital_management_system;
USE hospital_management_system;

-- 2. Create Login Table
CREATE TABLE IF NOT EXISTS login (
    ID VARCHAR(50) PRIMARY KEY,
    PW VARCHAR(50)
);
-- Default login credentials
INSERT INTO login (ID, PW) VALUES ('admin', 'admin123');

-- 3. Create Room Table
CREATE TABLE IF NOT EXISTS Room (
    Room_no VARCHAR(20) PRIMARY KEY,
    Availablity VARCHAR(20),  -- 'Available' or 'Occupied'
    Price VARCHAR(20),
    Clean_Status VARCHAR(20)   -- 'Clean' or 'Dirty'
);

-- 4. Create Patient Information Table
CREATE TABLE IF NOT EXISTS patient_info (
    ID_Type VARCHAR(50),
    Number VARCHAR(50),
    Name VARCHAR(50),
    Gender VARCHAR(20),
    Disease VARCHAR(100),
    Room_Number VARCHAR(20),
    Time VARCHAR(100),
    Deposite VARCHAR(20)
);

-- 5. Create Department Table
CREATE TABLE IF NOT EXISTS department (
    Department_Name VARCHAR(50),
    Phone_Number VARCHAR(20)
);

-- 6. Create Employee Information Table
CREATE TABLE IF NOT EXISTS EMP_info (
    Name VARCHAR(50),
    Age VARCHAR(10),
    Gender VARCHAR(20),
    Job VARCHAR(50),
    Salary VARCHAR(20),
    Phone VARCHAR(20),
    Email VARCHAR(50),
    Aadhar VARCHAR(20)
);

-- 7. Create Ambulance Table
CREATE TABLE IF NOT EXISTS Ambulance (
    Name VARCHAR(50),
    Gender VARCHAR(20),
    Car_Name VARCHAR(50),
    Available VARCHAR(20),
    Location VARCHAR(50)
);
```

---

## 🚀 Getting Started

### Prerequisites

-   **Java Development Kit (JDK)**: Version 17 or higher.
-   **MySQL Server**: Running on port `3306`.
-   **MySQL JDBC Driver**: `mysql-connector-j-8.3.0.jar` (or equivalent).

### Installation & Run

1.  **Configure Database**:
    *   Open MySQL Command Line Client or workbench.
    *   Execute the SQL setup script provided above.
    *   Verify/update the connection details in [Conn.java](file:///c:/Users/ASUS/Desktop/MediCare-Hospital-Management-System/src/hospital/management/system/Conn.java) if your local MySQL root password differs from `"12345"`.

2.  **Compilation**:
    *   Compile the project source files with the classpath reference. For Windows PowerShell:
    ```powershell
    javac -encoding UTF-8 -cp "C:\path\to\mysql-connector-j-8.3.0.jar" -d bin src/hospital/management/system/*.java
    ```

3.  **Run Application**:
    *   Execute the compiled classes starting with the Login frame:
    ```powershell
    java -cp "bin;C:\path\to\mysql-connector-j-8.3.0.jar" hospital.management.system.Login
    ```
