/**
 * Aluno: Gustavo Augusto Palmeira Oliveira
 * Matricula: 22052624
 * Disciplina: Sistemas Operacionais (IEC584)
 * Trabalho Prático III - Substituição de Páginas
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/input"));  // Lê o arquivo de entrada
        String line = reader.readLine();  // Lê a linha do arquivo
        String[] pageReferences = line.split(";");  // Divide a linha em referências de páginas

        // Cria os objetos para os algoritmos de substituição de páginas
        PageReplacementAlgorithms fifo = new PageReplacementAlgorithms(8000);
        LRU lru = new LRU(8000);
        SecondChance secondChance = new SecondChance(8000);

        // Para cada referência de página, chama o método referencePage para cada algoritmo
        for (String ref : pageReferences) {
            String[] parts = ref.split(",");
            if (parts.length < 2) {
                System.out.println("Referência de página malformada: " + ref);
                continue;
            }
            int page = Integer.parseInt(parts[1]);
            fifo.referencePage(page);
            lru.referencePage(page);
            secondChance.referencePage(page);
        }

        // Imprime o número de page faults para cada algoritmo
        System.out.println("FIFO Page Faults: " + fifo.getPageFaults());
        System.out.println("LRU Page Faults: " + lru.getPageFaults());
        System.out.println("Second Chance Page Faults: " + secondChance.getPageFaults());
    }
}
