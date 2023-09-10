# Order_Management_System
JDBC Order Management System with JSON Integration and Database Operations



## Introduction

This repository contains the code for an Enterprise Computing lab exercise focused on implementing the functionality of "Saving an Order" using JDBC. The task involves reading order data from a JSON file and storing it in a relational database. Additionally, the code includes functionality to retrieve and display the saved order.



## Task Description

1. Create the following classes based on the structure of the provided JSON data:

   - `Customer` class with required fields for customer information.
   - `Address` class with required fields for address information.
   - `OrderItem` class with required fields for order items.
   - `Order` class as an aggregation of `Customer`, `Address`, a list of `OrderItem` objects, and other fields as specified in the JSON file.

2. Implement a Data Access Object (DAO) for managing orders. The `OrderDAO` interface should include the following methods:

   - `void saveOrder(Order order)`: This function saves the given order data into the database.
   - `Order readOrder(int order_no)`: This function reads order data from the database based on the provided order number and constructs the corresponding `Order` object. If the order number does not exist, it returns `null`.

3. Create a tester class that performs the following actions:

   - Reads an order from the provided JSON file.
   - Saves the order into the database using the `saveOrder` function.
   - Reads the same order from the database using the `readOrder` function.
   - Prints the retrieved order information.

## Database Configuration

To establish a database connection, you can use the `DBCon.getConnection()` method provided in the [DBCon.java](https://github.com/pmjat/j2ee/blob/master/j2ee/jdbc-demo/src/main/java/pmj/jee/jdbc/DBCon.java) file. You may need to update the `DBConfig` as per your specific database configuration.

## Database Tables

The project assumes the existence of the following database tables:

- `Customer`
- `Order`
- `OrderItem`
- `Product`

The `Product` table stores attributes: `ID`, `Description`, `Rate`, and `QtyInStock`. It is assumed that the `ProductID` in the order item corresponds to an existing product ID in the `Product` table.

## Usage

1. Clone this repository.
2. Implement the required classes and methods as described in the tasks.
3. Configure the database connection in `DBConfig` if necessary.
4. Run the tester class to save and retrieve orders.

## Deliverables

- Source code for the required classes and the tester class.
- Documentation and comments explaining the code and its functionality.

---

**Note:** Please ensure that you have the required database and JDBC setup before running the code. Make any necessary adjustments to the database configuration and dependencies to match your environment.
