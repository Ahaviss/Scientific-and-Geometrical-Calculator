# Scientific and Geometrical Calculator

A Java CLI application that combines a scientific calculator and a geometrical calculator into one program. Supports arithmetic operations, geometry formulas for 2D and 3D shapes, calculation history, and flexible decimal output.

---

## Features

### Scientific Calculator
- Addition, Subtraction, Multiplication, Division
- Exponents
- Square Root
- Factorial
- Factor Finder
- Calculation history with `prev` keyword support (reuse last result as input)
- Clear history option

### Geometrical Calculator

**2D Shapes — Area**
| Shape | Inputs Required |
|-------|----------------|
| Circle | Radius or Diameter |
| Rectangle | Length, Width |
| Square | Side Length |
| Triangle | Base, Height |
| Trapezoid | Base 1, Base 2, Height |

**3D Shapes — Volume and Surface Area**
| Shape | Inputs Required |
|-------|----------------|
| Cube | Side Length |
| Cylinder | Radius or Diameter, Height |
| Square-Based Pyramid | Base Side, Height |
| Rectangular Prism | Length, Width, Height |
| Triangular Prism | Base, Height, Length |

- Accepts metric labels (cm, m, ft, in) and appends them to output
- Optional decimal precision control on any result

---

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── ahaviss/
│   │           ├── Main.java                        # Application entry point
│   │           ├── enums/
│   │           │   ├── CalculatorType.java
│   │           │   └── TypeOfCalculation.java
│   │           ├── history/
│   │           │   ├── History.java
│   │           │   └── HistoryManager.java
│   │           ├── utils/
│   │           │   └── ProjectUtils.java
│   │           └── calculators/
│   │               ├── scicalc/
│   │               │   ├── calcmain/
│   │               │   │   └── SciCalc.java
│   │               │   └── operations/
│   │               │       ├── Addition.java
│   │               │       ├── Subtraction.java
│   │               │       ├── Multiplication.java
│   │               │       ├── Division.java
│   │               │       ├── Exponents.java
│   │               │       ├── SquareRoot.java
│   │               │       ├── Factorial.java
│   │               │       └── Factors.java
│   │               └── geocalc/
│   │                   ├── calcmain/
│   │                   │   └── GeoCalc.java
│   │                   ├── enums/
│   │                   │   ├── GeometryOperation.java
│   │                   │   └── RadiusOrDiameter.java
│   │                   ├── logic/
│   │                   │   ├── ShapeLogic2D.java
│   │                   │   └── ShapeLogic3D.java
│   │                   ├── shapes2D/
│   │                   │   ├── Shape2D.java
│   │                   │   ├── Circle.java
│   │                   │   ├── Rectangle.java
│   │                   │   ├── Square.java
│   │                   │   ├── Triangle.java
│   │                   │   └── Trapezoid.java
│   │                   └── shapes3D/
│   │                       ├── Shape3D.java
│   │                       ├── Cube.java
│   │                       ├── Cylinder.java
│   │                       ├── PyramidSquare.java
│   │                       ├── RecPrism.java
│   │                       └── TrianglePrism.java
│   └── resources/                                   # Production configurations if needed
│
└── test/
    ├── java/
    │   └── com/
    │       └── ahaviss/                             # Root test package matching main source
    │           ├── GeometricalCalcTests.java        # Geometry JUnit Parameterized test suite
    │           ├── TestUtils.java                   # Terminal/Scanner testing mocking utilities
    │           └── generators/                      # Dedicated package for data generation utilities
    │               ├── GenerateAllData.java         # Master coordination script 
    │               ├── SciCalcDataGenerator.java
    │               ├── TwoDDataGenerator.java
    │               ├── ThreeDDataGeneratorSA.java
    │               └── ThreeDDataGeneratorVolume.java
    └── resources/                                   # Target location for generated source arrays
        ├── scicalcinput.csv
        ├── 2Dgeocalcinput.csv
        ├── 3Dgeocalcinputsa.csv
        └── 3Dgeocalcinputvolume.csv
```

---

## Getting Started

### Requirements
- Java 21 or higher
- Any IDE or terminal with `mvn test` and `mvn compile` available

### Compile

From the `src/` directory

## Usage

On launch, you will be prompted to choose a calculator:

```
Welcome to the calculator!
Would you like to open: 1 (Scientific Calculator), 2 (Geometrical Calculator), 3 (Quit Program)
```

Navigate each calculator through numbered menus. All inputs are validated — entering an invalid type will prompt you to try again without crashing.

In the scientific calculator, you can type `prev` as an input to reuse the result of your last calculation.

---

## Design Overview

- `Shape2D` and `Shape3D` are abstract classes. Each shape extends one and implements its required formula methods (`area()`, `volume()`, `surfaceArea()`).
- `ProjectUtils` centralizes all validated input methods (`getValidInt`, `getValidDouble`, `getValidString`, `getValidLong`) used across both calculators.
- `HistoryManager` stores a static list of `History` objects for the session, accessible from the scientific calculator menu.
- Enums (`GeometryOperation`, `RadiusOrDiameter`, `CalculatorType`) are used to control branching logic cleanly instead of raw strings or integers.

---

## License

Read LICENSE

## Updates

* MAJOR:
  * Moved build tool to Maven
  * Added a robust JUnit testing suite with fake input generators.
  * JUnit tests both scientfic and geometrical calculators.

