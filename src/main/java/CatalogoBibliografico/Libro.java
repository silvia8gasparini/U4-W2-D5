package CatalogoBibliografico;

import java.util.Objects;

public class Libro {
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;
    private String autore;
    private String genere;

public Libro(String isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
    this.isbn = isbn;
    this.titolo = titolo;
    this.annoPubblicazione = annoPubblicazione;
    this.numeroPagine = numeroPagine;
    this.autore = autore;
    this.genere = genere;
}

    public String getIsbn() { return isbn; }
    public String getTitolo() { return titolo; }
    public int getAnnoPubblicazione() { return annoPubblicazione; }
    public int getNumeroPagine() { return numeroPagine; }
    public String getAutore() { return autore; }
    public String getGenere() { return genere; }

    @Override
    public String toString() {
    return String.format("ISBN: %s, Titolo: %s, Anno: %d, Pagine: %d, Autore: %s, Genere: %s", isbn, titolo, annoPubblicazione, numeroPagine, autore, genere);
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Libro)) return false;
    Libro libro = (Libro) o;
    return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
    return Objects.hash(isbn);
    }
}
