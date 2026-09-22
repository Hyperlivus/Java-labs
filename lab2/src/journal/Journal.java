package journal;

import scheme.SchemeValidator;

import java.util.ArrayList;

public class Journal {
    final ArrayList<StudentData> students = new ArrayList<>();
    private SchemeValidator validator = new SchemeValidator();

    public void addStudent(StudentData data) {
        validator.validate(data);
        students.add(data);
    }

    public String print() {
        if (students.size() == 0) {
            return "Empty";
        }
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < students.size(); i ++) {
            builder.append(i + 1);
            builder.append(".");
            StudentData data = students.get(i);
            builder.append(data.firstname());
            builder.append("|");
            builder.append(data.lastname());
            builder.append("|");
            builder.append(data.birthdate());
            builder.append("|");
            builder.append(data.phone());
            builder.append("|");
            builder.append(data.address());
            builder.append("|");

        }

        return builder.toString();
    }

}

