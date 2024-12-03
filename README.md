# UofT Indoor Navigation

## Contributors
Yuxi Zhang (yuxi229) \
Bosen Xu (xubosen) \
Nicholas Koh (nicholaskohh) \
Rishith Singhagra (rishithsinghagra) \
Jackie Chen (39kie) \
Carter Hugill (chugill)

## About This Project

Uoft Indoor Navigation allows you to navigate a building at the University of Toronto.
You can start a route from a room and finish at another room.

### Purpose

This project was created to combat the difficulty that students of all ages face
when starting a new semester, and for visitors that want to tour the historic buildings at the university.

# Table of Contents
- [About This Project](#About This Project)
- [Software Features](#Software Features)
- [Installation Instructions](#Installation Instructions)
- [Usage Guide](#Usage Guide)
- [License](#License)
- [Feedback](#Feedback)
- [Contributions](#Contributions)
- [User Stories](#User Stories)

# Software Features
- Browse all the floors of the building
- Start a route: pick from the nearest room as the destination room and enter the destination room

# Installation Instructions
1. Install a Java Compiler, preferably IntelliJ: https://www.jetbrains.com/shop/eform/students
2. Install Java and choose the SDK: openjdk-22
3. Download the following packages: 
   - Maven https://maven.apache.org/download.cgi 
      (Note: sometimes you may need to right-click the pom.xml file and select `Maven -> Reload project' )
   - javax.swing https://docs.oracle.com/javase/tutorial/uiswing/index.html
4. Install Git at https://git-scm.com/downloads
5. Fork and clone this repository 
6. Open file: Main and click on "Run 'Main'" 

# Usage Guide

Start the program by running Main. 

The software opens in a new window. Type in the roomcode of the room you're the closest to, the type in the 
roomcode of your destination room and click on "Begin Route". 

To view the map of the building freely, click on the botton "View Freely"

# License

The software under the name "CSC207-Project" is a public repository. 

# Feedback

Provide feedback by emailing carter.hugill@mail.utoronto.ca.
Any feedback on improvement on Clean Architecture and improving current functions would be appreciated. 

# Contributions

Contributions can be made by forking this repository and creating a merge request, specifying what 
has been added to the program in the message. 


# User Stories

As a student / professor / visitor:

**I want to be able to create an account so I can have personalized information saved and be able to log-in and log-out (Team)**

- The user registers for an account
  - The user chooses a username
  - The user chooses a password and enters it twice (to help them remember)
  - If the username already exists, the system alerts the user
  - If the two passwords don't match, the system alerts the user
  - If the username doesn’t exist in the system and the passwords match, then the system creates the user but does not log them in

- The user logs in

  - The user enters their username
  - If the username doesn’t exist the system alerts the user
  - The user enters the password
  - If the password is incorrect the system alerts the user

- The user logs out

  - The user clicks on the profile icon and clicks on log out
  - The system takes the user back to the log-in page


**I want to be able to enter a departure and arrival classroom and the app should give me directions, distance, and ETA for my trip. (Team)**

- The user enters the departure location

  - The user types in the classroom code into a textbox
  - If the classroom does not exist, the system alerts the user
  - The user taps a dropdown button which displays all available classrooms + main entrance
  - If the user clicks “start” without entering the destination, the system alerts the user

- The user enters the destination classroom

  - The user types in the classroom code into a textbox
  - If the classroom does not exist, the system alerts the user
  - The user taps a dropdown button which displays all available classrooms + main entrance
  - The system presents simple directions towards destination in words
  - The system displays the approximated distance and ETA


**I want to be able to easily save locations to my saved list so I can select my favourite classrooms to navigate to and from  (Carter)**

- The user adds a new location to the saved list tab
  - The user clicks on the saved list tab
  - The user clicks on the plus button
  - The user selects one of the classrooms available and presses “done”

- The user enters the destination classroom
  - The user clicks on the “save to favourites” button

- The user deletes a location from the saved list

  - The user clicks on the saved list tab
  - The user swipes left on a location and clicks on the delete icon

- The user clicks on one of the saved locations

  - The use chooses between “navigate from” and “navigate to”
  - The system takes the user to navigation view with the chosen room in either the departure or destination slot (depending what the user chose), with the other slot being unfilled


**I want to be see a visual representation (eg map and arrows) that show the directions to my destination (Nick)**

- The user enters a departure and destination location
  - *same as above*
  - The system gives the user a top-down view of a map and the arrows to the destination
  - If the starting and ending location are on different floors, the system indicates an arrow to an elevator/stairs and displays both floors

**I want to be able to select which floor of the building I’m viewing (Yuxi)**

- The user chooses from all available floors
  - The user clicks on a dropdown button which shows all the floors in the building
  - The user clicks on one of the options
  - The system displays a top-down view of the floor map


**I want to be able to freely look at a labelled floor plan without any destination in mind. (Simon)**

- The user enters a spectator mode through a toggle
  - The system displays all floors that the user is able to scroll through

- The user exits spectator mode through a toggle
  - The system returns to the default home page

**I want to be able to view a list of all the rooms and important amenities in Bahen. (Rishith)**

- The user clicks on the “rooms and amenities” tab
  - The system displays all rooms and amenities available, separated via categories

- The user clicks on one of the rooms
- The system gives the user choices between “save to favorites” or “find room”
  - If the user chooses “save to favorites”, the system alerts the user that the room has been saved to the list
  - If the user chooses “find room”, the system takes the user to navigation view with the chosen room in the “destination” slot, with the departure location being unfilled 

 