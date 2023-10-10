import java.util.*;

// Classe para o algoritmo LRU (Least Recently Used)
class LRU extends PageReplacementAlgorithms {
    HashMap<Integer, Integer> lastIndex = new HashMap<>(); // Mapa para guardar o último índice de cada página

    // Construtor que recebe o tamanho da memória
    LRU(int size) {
        super(size);
    }

    @Override
    void referencePage(int page) {
        // Se a página não está na memória
        if (!pages.contains(page)) {
            // Se a memória está cheia, remove a página menos recentemente usada
            if (pages.size() == size) {
                int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
                for (int i=0; i<size; i++) {
                    if (lastIndex.get(pages.get(i)) < lru) {
                        lru = lastIndex.get(pages.get(i));
                        val = pages.get(i);
                    }
                }

                pages.remove(pages.indexOf(val));
                lastIndex.remove(val);
            }
        } else {
            // Se a página já está na memória, remove do mapa para atualizar o índice
            lastIndex.remove(page);
        }

        pages.add(page); // Adiciona a nova página no final da memória e atualiza o mapa com o novo índice
        lastIndex.put(page, pages.size());
        pageFaults++;  // Incrementa o contador de page faults
    }
}
