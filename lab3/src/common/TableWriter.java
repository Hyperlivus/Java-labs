package common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableWriter<T> {
    private final List<T> rows;
    private final Map<String, String> headers;

    public TableWriter(List<T> rows) {
        this.rows = rows;
        this.headers = new HashMap<>();
    }

    public TableWriter(T[] rows) {
        this(Arrays.asList(rows));
    }

    public TableWriter<T> localize(String key, String text) {
        this.headers.put(key, text);
        return this;
    }

    public void write() {
        if (this.rows.isEmpty()) {
            System.out.println("(порожньо)");
            return;
        }

        List<String> keys = readKeys(this.rows.getFirst().getClass());
        List<List<String>> cells = new ArrayList<>();
        for (T row : this.rows) {
            List<String> line = new ArrayList<>();
            for (String key : keys) {
                line.add(String.valueOf(readValue(row, key)));
            }
            cells.add(line);
        }

        List<String> titles = keys.stream()
                .map(key -> this.headers.getOrDefault(key, key))
                .toList();

        int[] widths = new int[keys.size()];
        for (int i = 0; i < keys.size(); i++) {
            widths[i] = titles.get(i).length();
            for (List<String> line : cells) {
                widths[i] = Math.max(widths[i], line.get(i).length());
            }
        }

        StringBuilder out = new StringBuilder();
        appendLine(out, titles, widths);
        appendSeparator(out, widths);
        for (List<String> line : cells) {
            appendLine(out, line, widths);
        }

        System.out.print(out);
    }

    private static List<String> readKeys(Class<?> type) {
        if (type.isRecord()) {
            return Arrays.stream(type.getRecordComponents())
                    .map(RecordComponent::getName)
                    .toList();
        }

        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .map(Field::getName)
                .toList();
    }

    private static Object readValue(Object row, String key) {
        try {
            Field field = row.getClass().getDeclaredField(key);
            field.setAccessible(true);
            return field.get(row);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Cannot read property " + key, e);
        }
    }

    private static void appendLine(StringBuilder out, List<String> values, int[] widths) {
        out.append('|');
        for (int i = 0; i < widths.length; i++) {
            out.append(' ').append(pad(values.get(i), widths[i])).append(" |");
        }
        out.append('\n');
    }

    private static void appendSeparator(StringBuilder out, int[] widths) {
        out.append('|');
        for (int width : widths) {
            out.append("-".repeat(width + 2)).append('|');
        }
        out.append('\n');
    }

    private static String pad(String value, int width) {
        return value + " ".repeat(width - value.length());
    }
}
