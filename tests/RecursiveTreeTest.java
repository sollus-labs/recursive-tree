import java.util.List;

public final class RecursiveTreeTest {
    public static void main(String[] args) {
        calculatesBaseCase();
        calculatesPreparedExample();
        calculatesDifferentParameters();
        summarizesLevels();
        rendersSmallTree();
        rejectsInvalidParameters();
        System.out.println("Todos os testes passaram.");
    }

    private static void calculatesBaseCase() {
        assertEquals(1, RecursiveTree.calculate(1, 2, 2), "caso-base");
    }

    private static void calculatesPreparedExample() {
        assertEquals(32, RecursiveTree.calculate(8, 2, 2), "exemplo preparado");
    }

    private static void calculatesDifferentParameters() {
        assertEquals(27, RecursiveTree.calculate(9, 3, 3), "tres chamadas por nivel");
        assertEquals(8, RecursiveTree.calculate(5, 1, 2), "uma chamada por nivel");
    }

    private static void summarizesLevels() {
        List<RecursiveTree.Level> levels = RecursiveTree.levels(8, 2, 2);
        assertEquals(4, levels.size(), "quantidade de niveis");
        assertEquals(1, levels.get(0).nodes(), "nos do nivel zero");
        assertEquals(8, levels.get(0).totalCost(), "custo do nivel zero");
        assertEquals(8, levels.get(3).nodes(), "nos do ultimo nivel");
        assertEquals(8, levels.get(3).totalCost(), "custo do ultimo nivel");
    }

    private static void rendersSmallTree() {
        String tree = RecursiveTree.renderTree(2, 2, 2);
        assertTrue(tree.contains("T(2)"), "raiz da arvore");
        assertTrue(tree.contains("T(1)"), "folha da arvore");
    }

    private static void rejectsInvalidParameters() {
        assertThrows(() -> RecursiveTree.calculate(0, 2, 2), "tamanho invalido");
        assertThrows(() -> RecursiveTree.calculate(8, 0, 2), "ramificacao invalida");
        assertThrows(() -> RecursiveTree.calculate(8, 2, 1), "reducao invalida");
    }

    private static void assertEquals(long expected, long actual, String name) {
        if (expected != actual) {
            throw new AssertionError(name + ": esperado " + expected + ", recebido " + actual);
        }
    }

    private static void assertEquals(int expected, int actual, String name) {
        if (expected != actual) {
            throw new AssertionError(name + ": esperado " + expected + ", recebido " + actual);
        }
    }

    private static void assertTrue(boolean condition, String name) {
        if (!condition) {
            throw new AssertionError(name);
        }
    }

    private static void assertThrows(Runnable action, String name) {
        try {
            action.run();
            throw new AssertionError(name + ": excecao nao foi lancada");
        } catch (IllegalArgumentException expected) {
            return;
        }
    }
}
