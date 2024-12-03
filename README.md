# CampusNavigator


## Contributors
Yuxi Zhang (yuxi229) \
Bosen Xu (xubosen) \
Nicholas Koh (nicholaskohh) \
Rishith Singhagra (rishithsinghagra) \
Jackie Chen (39kie) \
Carter Hugill (chugill)

## About This Project

CampusNavigator allows you to navigate a building at the University of Toronto.
You can start a route from a room, or the entrance, and finish at another room.

### Purpose

This project was created to combat the difficulty that students of all ages face
when starting a new school year.

# Table of Contents

## Software Features
- Browse the map
- Start a route, either from the entrance or another room
- Save rooms

# Installation Instructions

Download the repository. Dependencies are automatically installed!

Check and ensure your Java version is 17+.

# Using the Software

Start the program by running Main. The software
opens in a new window. Enter a room to depart from, and a destination.

Routes are saved automatically to the dropdown list.

# Usage Guide

**Screenshots**

![Choose a Floor to Browse](../../Desktop/Choose%20a%20Floor%20to%20Browse.png)\
[View Freely](../../Desktop/View%20Freely.png)\
![Map](../../Desktop/Map.png)\
![Where To?](../../Desktop/Where%20To%3F.png)

# License
The software is free to use, and may be modified.

# Feedback

Provide feedback by emailing carter.hugill@mail.utoronto.ca.
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

 