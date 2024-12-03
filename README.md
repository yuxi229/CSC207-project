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
- [About This Project](##About This Project)
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
2. Install Java 17
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

Team - As a user I want to be able input a departure room and destination room

Simon - As a user, I want to know be able to receive a set of instructions based on my departure and destination rooms

Yuxi - As a user, I want to know whether the rooms I entered exists, so I can enter a valid room to begin navigation

Nicholas - As a user, I want to see clear visual directions to my destination with indicators such as arrows, so I can navigate there easily.

Jackie - As a user, I want to be able to select which floor of the building I’m viewing and freely look at a labelled floor plan

Carter - As a user, I want to be able to select a recently chosen route, from a dropdown

Rishith - As a user, I want to know all the amendities and rooms in the building