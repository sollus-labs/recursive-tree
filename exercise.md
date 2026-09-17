# Trabalho: Desenvolvimento de um Sistema Genérico

**Universidade Católica do Salvador**  
**Escola de Tecnologias**  
**Curso:** Engenharia de Software  
**Disciplina:** Tópicos Avançados de Programação  
**Professor:** Elton Figueiredo da Silva  
**Turma:** ____________________  
**Data:** ____/____/________

## 1. Objetivo

Cada equipe deverá desenvolver um sistema genérico capaz de resolver problemas relacionados ao tema atribuído à equipe:

- Método de Gauss-Jacobi;
- Método de Gauss-Seidel;
- Recursividade;
- Árvore de recursão.

O sistema não deve resolver apenas um exercício específico com valores fixos ou hardcoded. Ele deverá receber os parâmetros do problema e aplicar o algoritmo correspondente de forma genérica para encontrar a solução.

## 2. Regra estrita de código

É proibido inserir comentários no código-fonte do sistema. O código deve ser limpo, organizado e autoexplicativo. A presença de qualquer comentário no código acarretará perda de pontos.

## 3. Dinâmica do dia da apresentação

A apresentação de cada equipe será dividida em três etapas.

### Etapa 1: Explicação conceitual

- **1º sorteio:** o professor sorteará um integrante da equipe para conduzir esta etapa;
- a equipe deverá explicar o tema desde os conceitos básicos, assumindo que a turma não possui conhecimento prévio sobre o assunto;
- a explicação deverá ser clara, didática e abordar os fundamentos teóricos e a lógica do algoritmo.

### Etapa 2: Demonstração com exemplo da equipe

- **2º sorteio:** o professor sorteará um integrante da equipe para conduzir esta etapa;
- o aluno sorteado deverá executar o sistema utilizando um exemplo preparado previamente pela equipe e trazido de casa;
- durante a execução, deverá explicar passo a passo a resolução do problema por meio do software.

### Etapa 3: Desafio do professor

- **3º sorteio:** o professor sorteará outro integrante da equipe, diferente dos dois sorteados anteriormente;
- o aluno sorteado receberá um novo exemplo surpresa, fornecido pelo professor no momento da apresentação;
- o aluno deverá inserir os dados no sistema ao vivo, demonstrar a solução e explicar o resultado obtido.

## 4. Critérios de avaliação

1. **Generalidade do sistema:** capacidade de resolver qualquer entrada válida dentro do tema atribuído;
2. **Qualidade do código:** cumprimento da regra de ausência total de comentários, além de organização, clareza e legibilidade;
3. **Clareza didática:** qualidade da explicação teórica inicial para um público sem conhecimento prévio;
4. **Entrega:** todas as equipes deverão entregar o trabalho na mesma data.

## 5. Projeto da equipe: árvore de recursão

### Linguagem e ambiente

O sistema será desenvolvido em Java 21. O ambiente de desenvolvimento será fornecido por Nix e carregado automaticamente pelo direnv.

Para entrar no ambiente:

```text
direnv allow
```

O projeto usa somente o JDK. Não há dependências externas nem framework.

### Problema resolvido

O sistema analisa a recorrência genérica:

```text
T(n) = aT(floor(n / b)) + n, para n > 1
T(n) = 1, para n <= 1
```

Os valores não ficam fixos no programa. A pessoa usuária informa:

- `n`: tamanho inicial do problema;
- `a`: quantidade de chamadas recursivas em cada nó;
- `b`: fator de redução do tamanho do problema.

O valor `a` deve ser maior que zero e `b` deve ser maior ou igual a dois.

### Como o algoritmo funciona

1. O programa recebe `n`, `a` e `b`.
2. Cada chamada mostra um problema de tamanho `n` e possui custo local igual a `n`.
3. Se o tamanho for maior que um, a chamada cria `a` subproblemas de tamanho `floor(n / b)`.
4. O processo continua recursivamente até chegar ao caso-base.
5. O custo total é a soma dos custos de todos os nós da árvore.
6. O programa mostra uma tabela com a quantidade de nós, o tamanho dos problemas e o custo de cada nível.

O cálculo não depende de um exemplo específico. Por exemplo, com `n = 8`, `a = 2` e `b = 2`, os custos por nível são `8`, `8`, `8` e `8`, resultando em custo total `32`.

Para entradas pequenas, a árvore completa também é impressa no terminal. Para entradas grandes, o programa mantém o resumo por nível e informa que a impressão completa foi omitida.

### Organização do projeto

- `src/Main.java`: leitura dos valores e apresentação dos resultados;
- `src/RecursiveTree.java`: validação, cálculo recursivo, resumo dos níveis e impressão da árvore;
- `tests/RecursiveTreeTest.java`: testes do caso-base, do exemplo preparado, de outros parâmetros e de entradas inválidas;
- `flake.nix`: ambiente Java 21;
- `.envrc`: integração com direnv.

Os arquivos Java não contêm comentários. A explicação conceitual está neste documento para cumprir a regra da atividade sem prejudicar a compreensão do código.

### Execução

Dentro do ambiente carregado pelo direnv, compile o programa e os testes:

```text
mkdir -p out
javac -d out src/*.java tests/*.java
java -ea -cp out RecursiveTreeTest
java -cp out Main
```

Na apresentação, o exemplo preparado pode usar `8`, `2` e `2`. Para o desafio surpresa, basta informar novos valores válidos de `n`, `a` e `b` e explicar como cada nível da árvore foi formado.