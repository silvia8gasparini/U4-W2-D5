package CatalogoBibliografico;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private final Map<String, Libro> catalogo = new HashMap<>();
    public void aggiungiLibro(Libro libro) {
        if (catalogo.containsKey(libro.getIsbn())) {
            System.out.println("Errore: ISBN giù presente.");
            return;
        }
        catalogo.put(libro.getIsbn(), libro);
    }
    public Libro cercaPerIsbn(String isbn) {
        return Optional.ofNullable(catalogo.get(isbn)).orElseThrow(() -> new LibroNonTrovato(isbn));
    }

    public void rimuoviLibro(String isbn) {
        if (catalogo.remove(isbn) == null) {
            System.out.println("Nessun libro trovato con ISBN " + isbn);
        } else {
            System.out.println("Libro rimosso con successo");
        }
    }

    public List<Libro> cercaPerAnno(int anno) {
        return catalogo.values().stream().filter(l -> l.getAnnoPubblicazione() == anno).collect(Collectors.toList());
    }

    public List<Libro> cercaPerAutore(String autore) {
        return catalogo.values().stream().filter(l -> l.getAutore().equalsIgnoreCase(autore)).collect(Collectors.toList());
    }

    public void aggiornaLibro(String isbn, Libro nuovoLibro) {
        if (!catalogo.containsKey(isbn)) {
            throw new LibroNonTrovato(isbn);
        }
        catalogo.put(isbn, nuovoLibro);
        System.out.println("Libro aggiornato");
    }

    public void mostraStatistiche() {
        System.out.println("Numero totale di libri: " + catalogo.size());

        catalogo.values().stream().max(Comparator.comparingInt(Libro::getNumeroPagine)).ifPresent(l -> System.out.println("Libro con più pagine: " + l));

        double media = catalogo.values().stream().mapToInt(Libro::getNumeroPagine).average().orElse(0);

        System.out.printf("Media pagine: %.2f%n", media);
    }
}
