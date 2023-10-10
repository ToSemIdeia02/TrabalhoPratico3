import java.util.*;

// Classe para o algoritmo Second Chance
class SecondChance extends PageReplacementAlgorithms {
    LinkedList<Integer> buffer = new LinkedList<>(); // Buffer para implementar a política circular do algoritmo Second Chance
    HashMap<Integer, Boolean> secondChance = new HashMap<>(); // Mapa para guardar se uma página tem uma segunda chance

    // Construtor que recebe o tamanho da memória
    SecondChance(int size) {
        super(size);
    }

    @Override
    void referencePage(int page) {
        // Se a página não está na memória
        if (!pages.contains(page)) {
            // Se a memória está cheia, aplica a política do algoritmo Second Chance para remover uma página
            if (pages.size() == size) {
                while (true) {
                    int oldestPage = buffer.peek();
                    if (secondChance.get(oldestPage)) {
                        secondChance.put(oldestPage, false);
                        buffer.add(buffer.remove());
                    } else {
                        pages.remove(pages.indexOf(oldestPage));
                        secondChance.remove(oldestPage);
                        buffer.remove();
                        break;
                    }
                }
            }
            // Adiciona a nova página no final da memória e no buffer, e marca que ela tem uma segunda chance no mapa
            pages.add(page);
            buffer.add(page);
            secondChance.put(page, true);
            // Incrementa o contador de page faults
            pageFaults++;
        } else {
            // Se a página já está na memória, marca que ela tem uma segunda chance no mapa
            secondChance.put(page, true);
        }
    }
}
