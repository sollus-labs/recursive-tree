import java.util.ArrayList;
import java.util.List;

public final class RecursiveTree {
    private static final long MAX_RENDERED_NODES = 1024;

    private RecursiveTree() {
    }

    public static long calculate(int size, int branches, int divisor) {
        validate(size, branches, divisor);
        return calculateNode(size, branches, divisor);
    }

    public static List<Level> levels(int size, int branches, int divisor) {
        validate(size, branches, divisor);
        List<Level> result = new ArrayList<>();
        addLevel(result, size, branches, divisor, 0, 1);
        return List.copyOf(result);
    }

    public static String renderTree(int size, int branches, int divisor) {
        validate(size, branches, divisor);
        try {
            if (countNodes(size, branches, divisor) > MAX_RENDERED_NODES) {
                return "Arvore completa omitida: use a tabela de niveis para esta entrada.";
            }
        } catch (ArithmeticException exception) {
            return "Arvore completa omitida: quantidade de nos muito grande.";
        }

        StringBuilder result = new StringBuilder();
        appendTree(result, size, branches, divisor, 0);
        return result.toString();
    }

    private static long calculateNode(int size, int branches, int divisor) {
        if (size <= 1) {
            return 1;
        }

        long childCost = calculateNode(size / divisor, branches, divisor);
        return Math.addExact(size, Math.multiplyExact(branches, childCost));
    }

    private static void addLevel(
            List<Level> levels,
            int size,
            int branches,
            int divisor,
            int number,
            long nodes) {
        long totalCost = Math.multiplyExact(nodes, size);
        levels.add(new Level(number, nodes, size, totalCost));

        if (size > 1) {
            addLevel(levels, size / divisor, branches, divisor, number + 1,
                    Math.multiplyExact(nodes, branches));
        }
    }

    private static long countNodes(int size, int branches, int divisor) {
        if (size <= 1) {
            return 1;
        }

        long childNodes = countNodes(size / divisor, branches, divisor);
        return Math.addExact(1, Math.multiplyExact(branches, childNodes));
    }

    private static void appendTree(
            StringBuilder result,
            int size,
            int branches,
            int divisor,
            int level) {
        result.append("  ".repeat(level))
                .append("T(")
                .append(size)
                .append(") custo local = ")
                .append(size)
                .append(System.lineSeparator());

        if (size <= 1) {
            return;
        }

        for (int branch = 0; branch < branches; branch++) {
            appendTree(result, size / divisor, branches, divisor, level + 1);
        }
    }

    private static void validate(int size, int branches, int divisor) {
        if (size < 1) {
            throw new IllegalArgumentException("n deve ser maior que zero");
        }
        if (branches < 1) {
            throw new IllegalArgumentException("a deve ser maior que zero");
        }
        if (divisor < 2) {
            throw new IllegalArgumentException("b deve ser maior ou igual a dois");
        }
    }

    public record Level(int number, long nodes, int size, long totalCost) {
    }
}
