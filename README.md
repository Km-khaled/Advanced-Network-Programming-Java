# Advanced Networking in Java: UDP and TCP Socket Programming

## Overview

This repository houses a comprehensive set of practical exercises for the "Réseaux Avancés" (Advanced Networking) course, tailored for M1 GL 2023/2024. The project encompasses a wide range of Java networking concepts, including UDP and TCP socket programming, object serialization, file I/O operations, and the integration of graphical user interfaces with network applications.

## Table of Contents

1. [Key Features](#key-features)
2. [System Requirements](#system-requirements)
3. [Installation Guide](#installation-guide)
4. [Repository Structure](#repository-structure)
5. [Execution Instructions](#execution-instructions)
6. [Technical Implementation](#technical-implementation)
7. [Learning Outcomes](#learning-outcomes)
8. [Contribution Guidelines](#contribution-guidelines)
9. [Licensing Information](#licensing-information)

## Key Features

- Robust UDP and TCP client-server architectures
- Advanced object serialization and transmission
- Multi-data type handling (int, boolean, String, double)
- Efficient multicast communication protocols
- Server-side mathematical operation processing
- Seamless GUI integration with socket programming
- Network-oriented file I/O operations
- Scalable multi-threaded server implementations
- Image transfer over TCP sockets
- Screen capture and transmission
- Dynamic file and image transfer based on user selection
- Client authentication system

## System Requirements

- Java Development Kit (JDK) 
- Integrated Development Environment (IDE) - NetBeans (recommended)
- Fundamental knowledge of networking concepts and Java programming

## Installation Guide

1. Clone the repository:
\`\`\`
git clone https://github.com/Km-khaled/Advanced-Network-Programming-Java.git
\`\`\`

2. Navigate to the project directory:
\`\`\`
cd Advanced-Network-Programming-Java
\`\`\`

3. Open the project in your preferred Java IDE

## Repository Structure

- `TP1/`: Foundational Networking Concepts
- `TP2/`: TCP Socket Programming Implementations
- `TP3/`: UDP Socket Programming Implementations
- `TP4/`: GUI-Integrated TCP Socket Programming
- `TP5/`: Advanced TCP Applications

## Execution Instructions

1. Run the server 
2. Run the client 

## Technical Implementation

- Uses `DatagramSocket` for UDP communication
- Implements `Serializable` for object transmission
- Utilizes `ByteArrayOutputStream` and `ByteArrayInputStream` for data conversion
- Employs `MulticastSocket` for group communication
- Implements `Socket` and `ServerSocket` for TCP communication
- Integrates Java Swing for GUI applications
- Utilizes `ImageIO` for image reading and writing
- Implements `Robot` class for screen capture functionality
- Uses `DataOutputStream` and `DataInputStream` for type-specific data transfer
- Incorporates multi-threading for handling multiple client connections
- Implements a basic authentication system for client verification

## Key Learning Objectives

- Understanding UDP and TCP socket programming in Java
- Implementing serialization and deserialization of objects
- Working with Java networking classes (`Socket`, `ServerSocket`, `DatagramSocket`, `InetAddress`)
- Developing multi-threaded server applications
- Creating GUIs for network applications using Swing
- Handling file I/O operations in networking contexts
- Implementing practical client-server applications
- Mastery of image transfer techniques over TCP sockets
- Implementation of screen capture and transmission functionality
- Development of dynamic file and image transfer systems
- Creation of multi-threaded servers for handling concurrent client connections
- Implementation of basic client authentication mechanisms

## Contributing

Contributions to improve the exercises or add new relevant networking concepts are welcome. Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/newFeature`)
3. Commit your changes (`git commit -m 'Add some new Features'`)
4. Push to the branch (`git push origin feature/newFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

---

For any questions or issues, please open an issue in the GitHub repository.

