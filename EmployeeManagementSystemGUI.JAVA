import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary;
    }
}

class EmployeeManager {
    ArrayList<Employee> employees = new ArrayList<>();

    void addEmployee(int id, String name, String department, double salary) {
        employees.add(new Employee(id, name, department, salary));
    }

    void removeEmployee(int id) {
        employees.removeIf(emp -> emp.id == id);
    }

    String getEmployees() {
        StringBuilder sb = new StringBuilder();
        for (Employee emp : employees) {
            sb.append(emp).append("\n");
        }
        return sb.toString();
    }
}

public class EmployeeManagementSystemGUI {
    private EmployeeManager manager = new EmployeeManager();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmployeeManagementSystemGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField deptField = new JTextField();
        JTextField salaryField = new JTextField();

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(deptField);
        inputPanel.add(new JLabel("Salary:"));
        inputPanel.add(salaryField);

        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove by ID");
        inputPanel.add(addBtn);
        inputPanel.add(removeBtn);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        JPanel buttonPanel = new JPanel();
        JButton showBtn = new JButton("Show All Employees");
        JButton exitBtn = new JButton("Exit");
        buttonPanel.add(showBtn);
        buttonPanel.add(exitBtn);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                String dept = deptField.getText().trim();
                double salary = Double.parseDouble(salaryField.getText().trim());

                manager.addEmployee(id, name, dept, salary);
                displayArea.setText("Employee added successfully.\n");
            } catch (Exception ex) {
                displayArea.setText("Error: Invalid input.\n");
            }
        });

        removeBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                manager.removeEmployee(id);
                displayArea.setText("Employee removed successfully (if existed).\n");
            } catch (Exception ex) {
                displayArea.setText("Error: Invalid ID.\n");
            }
        });

        showBtn.addActionListener(e -> {
            String employees = manager.getEmployees();
            if (employees.isEmpty()) {
                displayArea.setText("No employees to display.\n");
            } else {
                displayArea.setText(employees);
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
