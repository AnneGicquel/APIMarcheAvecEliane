# API Marche avec Eliane - Documentation

## Overview

This project includes an API developed using Spring 3.3.0 and Java 21. The database used is PostgreSQL, which can be managed and visualized using DBeaver. This API is required for the "Marche avec Eliane" code to access its data.

## Technologies Used

- **Framework:** Spring 3.3.0
- **Programming Language:** Java 21
- **Database:** PostgreSQL
- **Database Management Tool:** DBeaver

## Getting Started

### Prerequisites

Ensure you have the following installed on your local machine:

- Java 21 JDK
- PostgreSQL
- DBeaver (for database visualization and management)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/AnneGicquel/APIMarcheAvecEliane.git

## API Endpoints

The following endpoints are available in the API:

- `GET /api/elderlies/getAllElderlies` - Retrieves a list of elderlies.
- `POST /api/outings/createNewOuting` - Creates a new outing.
- `PUT /api/updateOutingByVolunteerId/{volunteerId}/outing/{outingId}` - Updates a specific outing by elderly ID.
- `DELETE /api/deleteOutingByElderlyId/{elderlyId}/outing/{outingId}` - Deletes a specific outing by elderly ID.

## Database Management with DBeaver

To visualize and manage the PostgreSQL database, you can use DBeaver:

### Download and Install DBeaver

Download the latest version of DBeaver [here](https://dbeaver.io/download/).

### Connect to the PostgreSQL Database

1. Open DBeaver and create a new connection to PostgreSQL.
2. Enter the database credentials (host, database name, username, password) configured in the `application.properties` file.

### Manage and Visualize Data

Use DBeaver to run SQL queries, manage tables, and visualize the database schema.

## About Me

I am Anne, a final-year student in web and mobile development with Simplon.
This is my masterpiece project 🏆 It has been designed and developed with all my heart ♡


[@AnneGicquel](https://www.github.com/AnneGicquel)
