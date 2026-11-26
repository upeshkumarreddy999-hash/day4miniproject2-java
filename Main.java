package StudentManagementSystemusingArray.LinkedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> studentIds = new ArrayList<>();
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Integer> studentMarks = new ArrayList<>();
        LinkedList<String> attendanceList = new LinkedList<>();

        while (true) {
            System.out.println("\n====================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Remove Student");
            System.out.println("6. Add Student Attendance");
            System.out.println("7. Display Attendance");
            System.out.println("8. Remove Attendance");
            System.out.println("9. Display Student Statistics");
            System.out.println("10. Sort Student Names");
            System.out.println("11. Exit");
            System.out.print("\nEnter Your Choice: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = sc.nextLine().trim();

                    if (studentIds.contains(id)) {
                        System.out.println("Student ID already exists!");
                        break;
                    }

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Enter Student Marks (0-100): ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid marks! Please enter a valid integer.");
                        sc.nextLine();
                        break;
                    }
                    int marks = sc.nextInt();
                    sc.nextLine();

                    if (marks < 0 || marks > 100) {
                        System.out.println("Invalid marks! Marks must be between 0 and 100.");
                    } else {
                        studentIds.add(id);
                        studentNames.add(name);
                        studentMarks.add(marks);
                        System.out.println("Student Added Successfully!");
                    }
                    break;

                case 2:
                    if (studentNames.isEmpty()) {
                        System.out.println("No student records found.");
                    } else {
                        System.out.println("\n--------- STUDENT RECORDS ---------");
                        System.out.printf("%-12s %-18s %s%n", "Student ID", "Student Name", "Marks");
                        for (int i = 0; i < studentNames.size(); i++) {
                            System.out.printf("%-12s %-18s %d%n", studentIds.get(i), studentNames.get(i), studentMarks.get(i));
                        }
                        System.out.println("\nTotal Students: " + studentNames.size());
                    }
                    break;

                case 3:
                    if (studentNames.isEmpty()) {
                        System.out.println("No student records available to search.");
                        break;
                    }
                    System.out.print("Enter Student Name: ");
                    String searchName = sc.nextLine().trim();

                    if (studentNames.contains(searchName)) {
                        int idx = studentNames.indexOf(searchName);
                        System.out.println("\nStudent Found!");
                        System.out.println("ID: " + studentIds.get(idx));
                        System.out.println("Name: " + studentNames.get(idx));
                        System.out.println("Marks: " + studentMarks.get(idx));
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    if (studentNames.isEmpty()) {
                        System.out.println("No student records available to update.");
                        break;
                    }
                    System.out.print("Enter Student Name: ");
                    String updateName = sc.nextLine().trim();

                    int updateIdx = studentNames.indexOf(updateName);
                    if (updateIdx != -1) {
                        System.out.print("Enter New Marks (0-100): ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid marks! Please enter a valid integer.");
                            sc.nextLine();
                            break;
                        }
                        int newMarks = sc.nextInt();
                        sc.nextLine();

                        if (newMarks < 0 || newMarks > 100) {
                            System.out.println("Invalid marks! Marks must be between 0 and 100.");
                        } else {
                            studentMarks.set(updateIdx, newMarks);
                            System.out.println("Student Marks Updated Successfully!");
                            System.out.println("Updated Record -> ID: " + studentIds.get(updateIdx) + ", Name: " + studentNames.get(updateIdx) + ", Marks: " + studentMarks.get(updateIdx));
                        }
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 5:
                    if (studentNames.isEmpty()) {
                        System.out.println("No student records available to remove.");
                        break;
                    }
                    System.out.print("Enter Student Name: ");
                    String removeName = sc.nextLine().trim();

                    int removeIdx = studentNames.indexOf(removeName);
                    if (removeIdx != -1) {
                        studentIds.remove(removeIdx);
                        studentNames.remove(removeIdx);
                        studentMarks.remove(removeIdx);
                        System.out.println("Student Removed Successfully!");

                        if (studentNames.isEmpty()) {
                            System.out.println("Student list is now empty.");
                        } else {
                            System.out.println("\nUpdated Student List:");
                            for (int i = 0; i < studentNames.size(); i++) {
                                System.out.println(studentIds.get(i) + " - " + studentNames.get(i) + " : " + studentMarks.get(i));
                            }
                        }
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 6:
                    System.out.print("Enter Student Name for Attendance: ");
                    String attName = sc.nextLine().trim();

                    System.out.println("1. Add Normally (End)");
                    System.out.println("2. Add at Beginning");
                    System.out.println("3. Add at End");
                    System.out.print("Choose position: ");
                    int pos = sc.hasNextInt() ? sc.nextInt() : 1;
                    sc.nextLine();

                    if (pos == 2) {
                        attendanceList.addFirst(attName);
                    } else if (pos == 3) {
                        attendanceList.addLast(attName);
                    } else {
                        attendanceList.add(attName);
                    }
                    System.out.println("Attendance marked for " + attName);
                    break;

                case 7:
                    if (attendanceList.isEmpty()) {
                        System.out.println("Attendance list is empty.");
                    } else {
                        System.out.println("\nPresent Students: " + attendanceList);
                        System.out.println("Total Present Students: " + attendanceList.size());

                        System.out.print("Search a student in attendance? (y/n): ");
                        String ans = sc.nextLine().trim();
                        if (ans.equalsIgnoreCase("y")) {
                            System.out.print("Enter Student Name to search: ");
                            String searchAtt = sc.nextLine().trim();
                            if (attendanceList.contains(searchAtt)) {
                                System.out.println(searchAtt + " is present.");
                            } else {
                                System.out.println(searchAtt + " is not in the attendance list.");
                            }
                        }
                    }
                    break;

                case 8:
                    if (attendanceList.isEmpty()) {
                        System.out.println("Attendance list is empty.");
                        break;
                    }
                    System.out.print("Enter Student Name to remove from attendance: ");
                    String remAttName = sc.nextLine().trim();

                    if (attendanceList.remove(remAttName)) {
                        System.out.println(remAttName + " removed from attendance.");
                    } else {
                        System.out.println("Student not found in attendance list.");
                    }
                    break;

                case 9:
                    if (studentMarks.isEmpty()) {
                        System.out.println("No student records available for statistics.");
                    } else {
                        int highest = studentMarks.get(0);
                        int lowest = studentMarks.get(0);
                        int sum = 0;
                        int above75 = 0;

                        for (int m : studentMarks) {
                            if (m > highest) highest = m;
                            if (m < lowest) lowest = m;
                            sum += m;
                            if (m > 75) above75++;
                        }

                        double average = (double) sum / studentMarks.size();

                        System.out.println("\nTotal Students: " + studentMarks.size());
                        System.out.println("Highest Marks: " + highest);
                        System.out.println("Lowest Marks: " + lowest);
                        System.out.printf("Average Marks: %.2f%n", average);
                        System.out.println("Students Above 75: " + above75);
                    }
                    break;

                case 10:
                    if (studentNames.isEmpty()) {
                        System.out.println("No student names to sort.");
                    } else {
                        ArrayList<String> sortedNames = new ArrayList<>(studentNames);
                        Collections.sort(sortedNames);
                        System.out.println("\nAlphabetical Order: " + sortedNames);

                        Collections.reverse(sortedNames);
                        System.out.println("Reverse Order: " + sortedNames);
                    }
                    break;

                case 11:
                    studentIds.clear();
                    studentNames.clear();
                    studentMarks.clear();
                    attendanceList.clear();
                    System.out.println("Exiting Student Management System. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select between 1 and 11.");
            }
        }
    }
}