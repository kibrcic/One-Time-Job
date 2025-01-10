# One-Time-Job
A Java-based application utilizing SQLite for data management, designed to facilitate short-term employment by connecting users posting one-time tasks (e.g., cleaning, pet care) with individuals seeking quick job opportunities.

## Features
- **Job Creation**: Users can post details of tasks such as cleaning apartments, walking dogs, or running errands.
- **Job Browsing**: Individuals seeking temporary work can explore available tasks.
- **User Management**: Separate portals for administrators and employees to manage tasks, registrations, and inquiries.
- **SQLite Integration**: Ensures reliable data storage and retrieval for jobs, user accounts, and activity logs.

## Technologies Used
- **Programming Language**: Java
- **Database**: SQLite
- **Development Tools**: Integrated development environment (IDE), Git for version control

## Application Structure
- **Admin Portal**: Allows administrators to create, edit, and delete job postings.
- **Employee Portal**: Enables employees to register, browse, apply for jobs, and make inquiries.
- **Database**: SQLite database file (`OneTimeJob.sqlite`) stores all application data securely.

### Files:
- `Admin_CreateJob.java`, `Admin_DeleteJob.java`, `Admin_EditJob.java`: Manage job postings.
- `Emp_Register.java`, `Emp_Apply_Job.java`, `Emp_Inquires.java`: Handle employee interactions.
- `Login_Admin.java`, `Login_Emp.java`: Provide secure login functionalities.
- `Sqliteconnection.java`: Establishes and manages the database connection.

## How to Use
1. **Setup**: Clone the repository and ensure Java and SQLite are installed on your system.
2. **Run Application**: Compile and run the main files to start the application.
3. **Admin Access**: Use the Admin Portal to manage job postings and oversee user activity.
4. **Employee Access**: Use the Employee Portal to register, browse jobs, and apply for opportunities.

## Future Enhancements
- Implement real-time notifications for job postings and applications.
- Add a rating system for users to provide feedback on completed tasks.
- Expand database functionality for scalability.
