# OpenSeat

OpenSeat is a Spring Boot web application that lets users track course availability, manage a personal watchlist, and receive alerts when a watched course becomes available.

This project was built as a lightweight MVP to practice the core pieces of a full-stack Java web app: server-side rendering, authentication, authorization, persistence, and role-based admin controls.

<img width="1373" height="800" alt="image" src="https://github.com/user-attachments/assets/2edbc19e-f163-4e56-9366-5894526dc258" />


---

## Features

- User login with Spring Security
- Role-based access control for student and admin users
- Browse available courses
- Add courses to a personal watchlist
- Remove courses from a watchlist
- Admin-only course management page
- Alerts generated when a watched course changes from unavailable to open
- Server-rendered UI with Thymeleaf and Bootstrap
- H2 in-memory database for fast local development

---

## Tech Stack

**Backend**
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

**Frontend**
- Thymeleaf
- HTML
- Bootstrap

**Database**
- H2

**Build Tool**
- Maven

---

## Project Goal

The goal of OpenSeat was to build a small but real vertical-slice web application that demonstrates:

- MVC structure in Spring Boot
- authentication and authorization
- database-backed user-specific data
- admin-protected functionality
- clean separation between controllers, services, repositories, and models

Rather than building a large production system, this project focuses on understanding the foundation of a secure Spring web application.

---

## Demo Accounts

Use the seeded demo accounts below after starting the app:

**Student**
- Email: `student@openseat.local`
- Password: `password123`

**Admin**
- Email: `admin@openseat.local`
- Password: `admin123`

---

## How It Works

1. A user logs into the application
2. The user browses available courses
3. The user adds a course to their watchlist
4. An admin updates course availability
5. If a watched course transitions from unavailable to open, an alert is created for that user

---



