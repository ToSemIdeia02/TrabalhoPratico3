import java.util.*;

// Classe base para os algoritmos de substituição de páginas
class PageReplacementAlgorithms {
    int size; // Tamanho da memória
    ArrayList<Integer> pages = new ArrayList<>(); // Lista das páginas na memória
    int pageFaults = 0; // Contador de page faults

    // Construtor que recebe o tamanho da memória
    PageReplacementAlgorithms(int size) {
        this.size = size;
    }

    // Método para referenciar uma página
    void referencePage(int page) {
        // Se a página não está na memória
        if (!pages.contains(page)) {
            // Se a memória está cheia, remove a primeira página
            if (pages.size() == size) {
                pages.remove(0);
            }
            pages.add(page);// Adiciona a nova página no final da memória
            pageFaults++; // Incrementa o contador de page faults
        }
    }

    // Método para obter o número de page faults
    int getPageFaults() {
        return pageFaults;
    }
}
