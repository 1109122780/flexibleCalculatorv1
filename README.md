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
- **Reset After Each Request**: The calculator automatically resets its current value after each API call to ensure independent calculations for each request.

## Design Decisions

- **Operation Strategy Pattern**: The operations (`Add`, `Subtract`, `Multiply`, `Divide`) are implemented using the Strategy design pattern. This makes the calculator extensible, allowing new operations to be added with minimal changes to the existing codebase.
- **IoC Compatibility**: The application is designed with Spring’s IoC container, ensuring that all dependencies are injected and managed by Spring. This also allows for easy unit testing and swapping of components.

## Testing

- **Unit Tests**: The project includes unit tests to verify the correctness of individual operations and chaining functionality.

## Future Enhancements

- **Support for More Operations**: Additional operations (e.g., modulo, exponentiation) could be added.
- **Persistent Storage**: Introduce persistent storage to allow for saving and retrieving past calculations.