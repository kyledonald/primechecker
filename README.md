# Prime Checker

## Introduction

This project is a command-line based Prime Checker written in Java, designed to elicit all possible prime numbers from a numeric sequence entered by the user.

## Usage Documentation

1. Run Main.java.
2. Enter your username when prompted.
3. Input a numeric sequence to find all the prime numbers it contains.
4. Choose to continue or quit the program.
5. Prime numbers elicited from sequences will be logged in primes.txt. Duplicates will not be logged.
6. Run PrimeCheckerTest.java to confirm expected results / validation rules.

### Example

- **Input:** `12345`
- **Output:** `Prime Numbers in sequence: 2, 3, 5, 23`

### Requirements 

- Developed using Java 21.
- Maven 3.9.9 was used for dependencies and building.

### Dependencies

- JUnit 5.8.1 for unit testing to ensure validation and functionality requirements are met.

### Assumptions and Limitations

- The program will accept up to 20 digits of input in the sequence.
  Input sequences longer than this often resulted in significantly degraded performance during testing due to the computational complexity of checking all substrings of large inputs.
- Letters, special characters, floats and negative numbers are rejected.
- Username validation is quite limited due to time constraints, validation focus is on numeric input.
