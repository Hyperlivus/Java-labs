package app;

import input.ConsoleReader;
import journal.Journal;
import journal.StudentData;

import java.util.Objects;
import java.util.Scanner;

public class App {
    final Journal journal = new Journal();
    final Scanner scanner = new Scanner(System.in);
    final ConsoleReader<StudentData> reader = new ConsoleReader<>();


    public void launch()  {
        System.out.println("What you wanna do? read(r)|write(w)|exit(e)");
        String command = scanner.nextLine();

        if (Objects.equals(command, "exit") || Objects.equals(command, "e")) {
            return;
        }

        if (Objects.equals(command, "read") || Objects.equals(command, "r")) {
            System.out.println(journal.print());
        }

        if (Objects.equals(command, "write") || Objects.equals(command, "w")) {
            try {
                StudentData data = reader.read(StudentData.class);
                journal.addStudent(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Invalid field");
            }
        }

        this.launch();
    }

}
