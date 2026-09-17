import java.util.List;
import java.util.Scanner;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int size = readInteger(scanner, "Digite n, o tamanho inicial: ");
            int branches = readInteger(scanner, "Digite a, a quantidade de chamadas: ");
            int divisor = readInteger(scanner, "Digite b, o fator de reducao: ");

            List<RecursiveTree.Level> levels = RecursiveTree.levels(size, branches, divisor);
            long totalCost = RecursiveTree.calculate(size, branches, divisor);

            printRecurrence(size, branches, divisor);
            printLevels(levels);
            System.out.println("Custo total: " + totalCost);
            System.out.println();
            System.out.println("Arvore de recursao:");
            System.out.print(RecursiveTree.renderTree(size, branches, divisor));
        } catch (IllegalArgumentException | ArithmeticException exception) {
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private static int readInteger(Scanner scanner, String message) {
        System.out.print(message);
        if (!scanner.hasNextInt()) {
            throw new IllegalArgumentException("digite um numero inteiro");
        }
        return scanner.nextInt();
    }

    private static void printRecurrence(int size, int branches, int divisor) {
        System.out.println();
        System.out.println("Recorrencia analisada: T(n) = " + branches
                + "T(floor(n / " + divisor + ")) + n");
        System.out.println("Caso-base: T(n) = 1 quando n <= 1");
        System.out.println("Entrada inicial: n = " + size);
        System.out.println();
    }

    private static void printLevels(List<RecursiveTree.Level> levels) {
        System.out.println("Resumo por nivel:");
        System.out.printf("%-8s %-12s %-16s %-16s%n", "Nivel", "Quantidade", "Tamanho", "Custo total");
        for (RecursiveTree.Level level : levels) {
            System.out.printf("%-8d %-12d %-16d %-16d%n",
                    level.number(), level.nodes(), level.size(), level.totalCost());
        }
        System.out.println();
    }
}
