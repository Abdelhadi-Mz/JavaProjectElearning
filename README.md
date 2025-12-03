# Java E-Learning Management System
A desktop-based E-Learning application built using Java Swing, built with an MVC-like structure.  
The system manages Students, Courses, and Student-Course assignments through a simple and clear user interface.

---

## Project Demo Video
A demonstration video will be added here showing the program running and its main features.



https://github.com/user-attachments/assets/a45a96ab-ebbc-4f10-bc66-fd9ca6b2fed9


---

## Features

### Student Management
- Add new students  
- Edit student information  
- Delete students  
- Display all students  
- Logic handled through the StudentService class

### Course Management
- Add new courses  
- Edit courses  
- Delete courses  
- Display all courses  
- Logic handled through the CoursService class

### Student–Course Assignment
- Assign students to courses  
- Display all student–course relationships  
- Managed through StudentCoursService

### User Interface (Java Swing)
- Dashboard and forms created with NetBeans GUI Builder  
- Panels, buttons, and tables connected to service logic  
- Clean and organized UI structure

---

## Project Structure
Below is the directory tree, protected so it displays correctly everywhere (GitHub, browsers, editors):

````text
e-learning/
│
├── src/
│   ├── Connexion/          # Connection class (database-ready structure)
│   ├── dao/                # Interfaces (IDao)
│   ├── entities/           # Student, Course, StudentCourse models
│   ├── services/           # CRUD operations and business logic
│   ├── ui/                 # Java Swing user interface
│   └── test/               # Test classes
│
├── build/                  # NetBeans build outputs
├── dist/                   # Packaged JAR files
├── nbproject/              # NetBeans project metadata
├── build.xml               # Ant build script
└── manifest.mf             # Application manifest file
````
---

## Technologies Used
- Java (Swing)
- NetBeans GUI Builder
- Ant Build System
- Inno Setup (for generating the Windows installer)
- JDBC-ready project structure

---

## Installation

### 1. Download the Installer
The project is distributed as a Windows executable created using Inno Setup.

Download the installer file:

e-learning.exe

---

### 2. Run the Installer
- Double-click `e-learning.exe`
- Follow the installation wizard (Next → Install → Finish)
- A desktop shortcut will be created automatically if enabled during setup

### 3. Launch the Application
You can run the program either:
- From the desktop shortcut  
- From the installation directory created by the installer

---

## About the Installer (Inno Setup)
The application uses Inno Setup to generate a professional Windows installer, which provides:
- Automatic folder creation  
- Optional Start Menu and desktop shortcuts  
- Clean uninstallation  
- A simple installation experience for users  
- Packaged executable ready for distribution  

---

## Project Architecture

The project is designed with maintainability in mind:

- `entities/` contains basic POJO models  
- `services/` contains business logic for CRUD operations  
- `ui/` contains all GUI components and event handling  
- `dao/` defines the structure for data access  
- `Connexion/` prepares the project for future database integration  

---

## Future Improvements
- Add MySQL/SQLite database support  
- Add login and authentication page  
- Enhance UI design  
- Add reporting (PDF/Excel)  
- Add charts and analytics  
- Add roles (admin, instructor, student)

---

## Author
Name : Abdelhadi el mezouari  
Cours : Java orienté objet  
Date : Décembre 2025  
Encadré par : Pr. Mohamed LACHGAR

