package weekone;

class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee(" + employeeId + ", " + name + ", " + position + ", " + salary + ")";
    }
}

public class Exercise4 {
    private Employee[] employees;
    private int size;

    public Exercise4(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add an employee
    public boolean addEmployee(Employee employee) {
        if (size == employees.length) {
            return false; // Array is full
        }
        employees[size++] = employee;
        return true;
    }

    // Search for an employee by employeeId
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == employeeId) {
                return employees[i];
            }
        }
        return null; // Employee not found
    }

    // Traverse all employees
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete an employee by employeeId
    public boolean deleteEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == employeeId) {
                // Shift elements to the left
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null; // Nullify the last element
                return true;
            }
        }
        return false; // Employee not found
    }

    public static void main(String[] args) {
        Exercise4 ems = new Exercise4(10);

        ems.addEmployee(new Employee(1, "Alice", "Developer", 60000));
        ems.addEmployee(new Employee(2, "Bob", "Designer", 50000));
        ems.addEmployee(new Employee(3, "Charlie", "Manager", 70000));

        System.out.println("All Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearch for Employee with ID 2:");
        System.out.println(ems.searchEmployee(2));

        System.out.println("\nDelete Employee with ID 1:");
        ems.deleteEmployee(1);
        ems.traverseEmployees();
    }
}
