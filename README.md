# Flexible Calculator

## Overview

The Flexible Calculator is a Java-based, Spring Boot-powered application that provides a simple, extensible, and RESTful API for performing basic arithmetic operations. The calculator supports operations such as addition, subtraction, multiplication, and division. It also allows for chaining multiple operations together.

## Features

- **Basic Operations**: Supports addition, subtraction, multiplication, and division.
- **Chaining Operations**: Users can chain multiple operations in a single request.
- **RESTful API**: The calculator functionalities are exposed through a REST API.
- **Extensibility**: New operations can be easily added without modifying the existing code.
- **IoC Compatibility**: The application is designed to be compatible with Inversion of Control (IoC) principles, allowing for easy dependency injection and testing.
- **Automatic Reset**: The calculator resets after each API call to ensure independent calculations for every request.

## Design principles 

### 1. **Open-Closed Principle (OCP)**

The Open-Closed Principle states that software entities  should be open for extension but closed for modification. 

**How OCP is Satisfied:**

- **OperationStrategy Interface and Strategy Pattern:** The `OperationStrategy` interface is designed to allow new operations to be added by simply creating new classes that implement this interface (e.g., `AddOperation`, `SubtractOperation`, `MultiplyOperation`, `DivideOperation`). 
- **Extensibility:** To add a new operation, such as modulo or exponentiation, we just need to create a new class implementing the `OperationStrategy` interface. The existing code in the `Calculator` class remains unchanged, fulfilling the Open-Closed Principle.

### 2. **Maintainability**

Maintainability refers to how easily software can be understood, corrected, adapted, and enhanced.

**How Maintainability is Satisfied:**

- **Modular Design:** The separation of concerns between different components ensures that each class has a clear responsibility. This modular design makes the code easier to maintain.
- **Clear and Consistent Structure:** The project is organized in a clear and consistent structure, with logical separation between the model, service, and controller layers.
- **Test Coverage:** Comprehensive unit tests help ensure that changes made to the codebase do not introduce regressions.

### 3. **Extensibility**

Extensibility refers to the ability of the software to be extended with new functionality without major modifications to the existing code.

**How Extensibility is Satisfied:**

- **Adding New Operations:** As mentioned under OCP, new operations can be added by simply creating new classes that implement the `OperationStrategy` interface. The `Calculator` class doesn’t need to be modified to support new operations, which makes it highly extensible.
- **Configuration Flexibility:** With the use of Spring's IoC container, dependencies can be injected and managed externally, which means different implementations can be swapped or added without changing the core logic of the `Calculator` class.

## Installation and Setup

**Clone the Repository**

```bash
git clone https://github.com/1109122780/flexibleCalculatorv1.git

cd flexibleCalculatorv1
```

**Build the Project**

```bash
mvn clean install
```

**Run the Application**

```bash
mvn spring-boot
```

**Access the API**

The API will be available at `http://localhost:8080/api/v1/calculator`.

## API Endpoints

### 1. **Single Operation**

**POST** `/api/v1/calculator/calculate`

**Request Body:**

```json
{ "operation": "ADD", "num1": 5, "num2": 3 }
```

**Response:**

```json
{ "result": 8.0 }
```



### 2. **Chaining Operations**

**POST** `/api/v1/calculator/chain`

**Request Body:**

```json
[ {"operation": "ADD", "num1": 5, "num2": 0}, {"operation": "MULTIPLY", "num1": null, "num2": 2} ]
```

**Response:**

```json
{ "result": 10.0 }
```



## Assumptions

- **Initial Value for Chaining**: When chaining operations, the first operation’s `num1` is used as the initial value. For subsequent operations, `num1` can be `null`, in which case the result of the previous operation is used as `num1`.
- **Division by Zero**: The application will throw an `IllegalArgumentException` if an attempt is made to divide by zero.
- **Unsupported Operations**: The application will throw an `UnsupportedOperationException` if an unsupported operation is assigned.
- **Reset After Each Request**: The calculator automatically resets its current value after each API call to ensure independent calculations for each request.
- **Null Input Handling**: The application will throw an `IllegalArgumentException` if any of the input numbers (`num1` or `num2`) are `null` during a calculation, except in cases where `num1` is expected to be `null` as part of chaining logic.

## Testing

- **Unit Tests**: The project includes unit tests to verify the correctness of individual operations and chaining functionality.

## Future Enhancements

- **Support for More Operations**: Additional operations (e.g., modulo, exponentiation) could be added.
- **Persistent Storage**: Introduce persistent storage to allow for saving and retrieving past calculations.