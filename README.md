# Java Backend Developer Challenge

- **ID: 6530fa8a952f8a33fbf2a5b2**
- **Author: Pedro Tejero Diez**

## Table of Contents

- [**Prerequisites**](#prerequisites)
- [**Getting Started**](#getting-started)
- [**Docker Configuration**](#docker-configuration)
- [**Endpoints**](#endpoints)
- [**Test**](#Testing)
- [**Classes Relationships**](#Classes)


## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Docker](https://www.docker.com/) installed
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) 8 or higher


## Getting Started

1. Clone the repository in your computer

2. Start Docker daemon

3. Open the terminal window in the  project folder in your computer and write the command: *docker-compose up* and wait for the containers *mysql* and *myapp* are up and running

4. configure your Postman (or similar framework) to send requests to localhost port 8080

5. You can use the json examples in **src/main/resources** as body requests for testing the service.

##  Docker Configuration

1. As requested, there are two Docker files: Dockerfile.mysql that create a mysql container with the hospital database already created and the user and password data (you can change this in the dockercompose file)

2. In the Dockerfile.maven a new maven project is created from the /src directory in the local machine and a fresh war executable file is created and run

3. These two Docker files are build and images are run following the script in the file **docker-compose.yml**. In this file, we create a network (*mynetwork*) so that the two images can communicate

## Endpoints

1. As requested, I just updated the "createAppointment" method inside the Appointment controller

2. the rest of endpoints you can test with postman or other framework of choice once the container mysql and myapp are running

3. Inside the folder **src/main/resources**, you can find a json example of each entity to use for testing. 

4. **IMPORTANT:** Be sure to always add a new Room name when trying to save an appointment as the room name is at the same time, the mysql ID and cannot be modified. This should be fixed at the level of entities but I am not allowed to change them

## Test

1. I was assigned two tasks: Create entities test for the entities inside the EntityUnitTest.java (Appointment, Doctor,Patient and Room). They were made and working.

2. The second task was to create Test for each of these entities Controllers inside the EntityControllerUnitTest.java (Doctor,Patient and Room). They were made and ready to test the code.

## Classes Relationships
1. This is a image of the organization of the classes:

- Doctors and Patients inherit from Person class, so the have the same attributes but are save in different tables in the database.
- Rooms are an independent class whose name is at the same time their identification in the database.
- Appointments are made out of one Doctor, one Patient and One room at a date. So, there can be several appointments with the same doctor and patient data but in different times.

![Classes Relationships](./images/class-diagram.png)

