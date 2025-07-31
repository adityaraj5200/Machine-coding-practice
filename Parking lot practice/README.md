# Parking Lot Management System

A comprehensive Java-based parking lot management system that handles vehicle parking, ticket generation, fee calculation, and real-time status monitoring.

## 🚗 Features

### Core Functionality
- **Multi-floor parking lot** with configurable floors and slots
- **Vehicle type support** (Car, Bike, Truck) with dedicated parking slots
- **Automatic slot assignment** based on vehicle type
- **Ticket generation** with entry time tracking
- **Fee calculation** based on parking duration
- **Real-time status monitoring** of all parking slots
- **Vehicle tracking** and slot management

### Advanced Features
- **Dynamic slot distribution** (60% Car, 20% Bike, 20% Truck slots)
- **Parking lot status display** with detailed slot information
- **Ticket management** with active/closed status tracking
- **Duration-based fee calculation** with minimum fee guarantee
- **Multiple parking lot support** with unique identifiers
- **Thread-safe operations** with proper concurrency handling
- **Race condition prevention** using synchronized methods and atomic operations
- **Concurrent vehicle parking/unparking** support

## 📁 Project Structure

```
Parking lot practice/
├── com/
│   └── aditya/
│       └── parkinglot/
│           ├── models/
│           │   ├── Vehicle.java
│           │   ├── VehicleType.java
│           │   ├── ParkingSlot.java
│           │   ├── ParkingFloor.java
│           │   ├── ParkingLot.java
│           │   └── Ticket.java
│           └── services/
│               ├── ParkingLotService.java
│               └── TicketService.java
├── ParkingLotSystem.java
├── ConcurrencyTest.java
└── README.md
```

## 🏗️ Architecture

### Models (`com.aditya.parkinglot.models`)

#### Vehicle & VehicleType
- **Vehicle**: Represents a vehicle with number and type
- **VehicleType**: Enum for CAR, BIKE, TRUCK

#### Parking Infrastructure
- **ParkingSlot**: Individual parking slot with occupancy status
- **ParkingFloor**: Floor containing multiple parking slots
- **ParkingLot**: Main parking lot containing multiple floors

#### Ticket Management
- **Ticket**: Parking ticket with entry/exit times and fee calculation

### Services (`com.aditya.parkinglot.services`)

#### ParkingLotService
- **createParkingLot()**: Creates parking lot with specified configuration
- **findAvailableSlot()**: Finds available slot for a vehicle
- **parkVehicle()**: Parks vehicle in a slot
- **unparkVehicle()**: Removes vehicle from slot
- **getAvailableSlots()**: Returns count of available slots by vehicle type
- **displayParkingLotStatus()**: Shows current status of all slots

#### TicketService
- **generateTicket()**: Creates parking ticket and parks vehicle
- **closeTicket()**: Closes ticket, unparks vehicle, calculates fee
- **calculateParkingFee()**: Calculates parking fee based on duration
- **displayTicketInfo()**: Shows detailed ticket information
- **displayAllTickets()**: Lists all tickets

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code)

### Running the Application

1. **Compile the project:**
   ```bash
   javac -cp . com/aditya/parkinglot/models/*.java
   javac -cp . com/aditya/parkinglot/services/*.java
   javac -cp . ParkingLotSystem.java
   ```

2. **Run the application:**
   ```bash
   java ParkingLotSystem
   ```

### Example Usage

```java
// Initialize services
ParkingLotService parkingLotService = new ParkingLotService();
TicketService ticketService = new TicketService(parkingLotService);

// Create parking lot (2 floors, 10 slots per floor)
ParkingLot parkingLot = parkingLotService.createParkingLot("PL001", 2, 10);

// Park a vehicle
Vehicle car = new Vehicle("CAR001", VehicleType.CAR);
Ticket ticket = ticketService.generateTicket("PL001", car);

// Display status
parkingLotService.displayParkingLotStatus("PL001");

// Close ticket and calculate fee
ticketService.closeTicket(ticket.getTicketId());
```

## 💰 Fee Structure

- **Hourly Rate**: $10 per hour
- **Minimum Fee**: $5 (regardless of duration)
- **Calculation**: `max(duration_hours × $10, $5)`

## 📊 Slot Distribution

Each floor follows this distribution:
- **60% Car slots** (6 out of 10 slots)
- **20% Bike slots** (2 out of 10 slots)  
- **20% Truck slots** (2 out of 10 slots)

## 🔧 Key Methods

### ParkingLotService
```java
// Create parking lot
createParkingLot(String parkingLotId, int floors, int slotsPerFloor)

// Find available slot
findAvailableSlot(String parkingLotId, Vehicle vehicle)

// Park/unpark vehicle
parkVehicle(String parkingLotId, Vehicle vehicle, ParkingSlot slot)
unparkVehicle(String parkingLotId, Vehicle vehicle)

// Get status
getAvailableSlots(String parkingLotId, VehicleType vehicleType)
displayParkingLotStatus(String parkingLotId)
```

### TicketService
```java
// Generate ticket
generateTicket(String parkingLotId, Vehicle vehicle)

// Close ticket and calculate fee
closeTicket(int ticketId)

// Get ticket information
getTicket(int ticketId)
displayTicketInfo(int ticketId)
displayAllTickets()
```

## 🧪 Testing

The main class (`ParkingLotSystem.java`) includes comprehensive testing:

1. **Parking lot creation** with 2 floors and 10 slots each
2. **Vehicle parking** for different vehicle types
3. **Ticket generation** and tracking
4. **Vehicle unparking** and fee calculation
5. **Status monitoring** and reporting

### Concurrency Testing

The `ConcurrencyTest.java` class demonstrates thread safety:

1. **Concurrent parking operations** with multiple threads
2. **Race condition testing** for slot allocation
3. **Thread-safe ticket generation** with atomic counters
4. **Concurrent unparking operations** with proper synchronization
5. **Data consistency verification** under high concurrency

## 🔄 System Flow

1. **Entry Process:**
   - Vehicle arrives
   - System finds available slot based on vehicle type
   - Ticket is generated with entry time
   - Vehicle is parked in assigned slot

2. **Exit Process:**
   - Ticket is presented
   - System calculates parking duration
   - Fee is calculated based on duration
   - Vehicle is unparked from slot
   - Ticket is closed

## 🎯 Design Patterns

- **Service Layer Pattern**: Separation of business logic
- **Model-View-Controller**: Clear separation of data and logic
- **Singleton-like Services**: Centralized service management
- **Factory Pattern**: Dynamic object creation for parking infrastructure
- **Thread-Safe Singleton**: Concurrent access management
- **Synchronized Methods**: Race condition prevention
- **Atomic Operations**: Thread-safe counter management

## 🔮 Future Enhancements

- **Database integration** for persistent storage
- **REST API** for web/mobile access
- **Payment gateway integration**
- **Reservation system** for advance booking
- **Multi-tenant support** for multiple parking locations
- **Real-time notifications** for slot availability
- **Analytics dashboard** for parking lot metrics

## 📝 License

This project is created for educational purposes and system design practice.

## 👨‍💻 Author

Created by Aditya for parking lot system design practice.

---

**Happy Parking! 🚗🅿️** 