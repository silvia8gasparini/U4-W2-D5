package CatalogoBibliografico;

public class ElementoNonTrovato extends RuntimeException {
  public ElementoNonTrovato(String isbn) {
    super("Libro con ISBN" + isbn + " non trovato.");
  }
}