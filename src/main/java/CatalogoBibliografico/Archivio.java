package CatalogoBibliografico;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private final Map<String, ElementoCatalogo> catalogo = new HashMap<>();

    public void aggiungiElemento(ElementoCatalogo elemento) {
        if (catalogo.containsKey(elemento.getIsbn())) {
            System.out.println("Errore: ISBN già presente.");
            return;
        }
        catalogo.put(elemento.getIsbn(), elemento);
    }

    public ElementoCatalogo cercaPerIsbn(String isbn) {
        return Optional.ofNullable(catalogo.get(isbn))
                .orElseThrow(() -> new ElementoNonTrovato(isbn));
    }

    public void rimuoviElemento(String isbn) {
        if (catalogo.remove(isbn) == null) {
            System.out.println("Nessun elemento trovato con ISBN " + isbn);
        } else {
            System.out.println("Elemento rimosso con successo");
        }
    }

    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogo.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<Libro> cercaPerAutore(String autore) {
        return catalogo.values().stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) {
        if (!catalogo.containsKey(isbn)) {
            throw new ElementoNonTrovato(isbn);
        }
        catalogo.put(isbn, nuovoElemento);
        System.out.println("Elemento aggiornato");
    }

    public void mostraStatistiche() {
        System.out.println("Numero totale di elementi: " + catalogo.size());

        catalogo.values().stream()
                .max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine))
                .ifPresent(e -> System.out.println("Elemento con più pagine: " + e));

        double media = catalogo.values().stream()
                .mapToInt(ElementoCatalogo::getNumeroPagine)
                .average()
                .orElse(0);

        System.out.printf("Media pagine: %.2f%n", media);
    }
}
