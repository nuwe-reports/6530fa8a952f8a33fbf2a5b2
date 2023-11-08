# Java Backend Developer Challenge

- **ID: 6530fa8a952f8a33fbf2a5b2**
- **Author: Pedro Tejero Diez**

## Table of Contents

- [**Prerequisites**](#prerequisites)
- [**Getting Started**](#getting-started)
- [**Docker Configuration**](#docker-configuration)
- [**Endpoints**](#endpoints)
- [**Test**](#Testing)


## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Docker](https://www.docker.com/) installed
- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html) 8 or higher


## Getting Started

1. Clone the repository in your computer.
2. Start Docker daemon
3. Open the terminal window in the  project folder in your computer and write: docker-compose up
4. configure your Postman (or similar framework) to send requests to localhost port 8080
5. You can use the json examples in **src/main/resources** as body requests for testing the service.

##  Docker Configuration

1. As requested, there are two Docker files: Dockerfile.mysql that create a mysql container with the hospital database already created and the user and password data (to be changed for the real ones)
2. In the Dockerfile.maven, a fresh maven project is built and it will create the executable file for the application.
3. These two Docker files are build and images are run following the script in the file **docker-compose.yml**. To run this script you should first **start Docker** and then open the terminal in the project folder and type: **docker-compose up**

## Endpoints

1. As requested, I just updated the "createAppointment" method inside the Appointment controller
2. the rest of endpoints you can test with postman once the container mysql and myapp are running
3. Inside the folder **src/main/resources**, you can find an json example of each entity to start with. You should first save the doctor, patient and room data in the mysql databases using **postman** or other similar framework. Then you will be able to use the appointment.json data to test the system.
4.**IMPORTANT:** Be sure to always add a new Room name when trying to save an appointment as the system is saving the room everytime you send an appointment. This should be fixed at the level of entities as the room name is also the mysql id.
