package CatalogoBibliografico;

public class LibroNonTrovato extends RuntimeException {
    public LibroNonTrovato(String isbn) {
        super("Libro con ISBN" + isbn + " non trovato.");
    }
}
