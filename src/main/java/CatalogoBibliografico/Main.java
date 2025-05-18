package CatalogoBibliografico;

import java.util.Scanner;

public class Main {
    public static final Archivio archivio = new Archivio ();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu Biblioteca");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Cerca per ISBN");
            System.out.println("3. Rimuovi libro");
            System.out.println("4. Cerca per anno");
            System.out.println("5. Cerca per autore");
            System.out.println("6. Aggiorna libro");
            System.out.println("7. Mostra statistiche");
            System.out.println("8. Esci");
            System.out.println("Scelta: ");

            try {
                int scelta = Integer.parseInt(scanner.nextLine());
                switch (scelta) {
                    case 1 -> aggiungiLibro();
                    case 2 -> cercaIsbn();
                    case 3 -> rimuoviLibro();
                    case 4 -> cercaAnno();
                    case 5 -> cercaAutore();
                    case 6 -> aggiornaLibro();
                    case 7 -> archivio.mostraStatistiche();
                    case 0 -> {
                        System.out.println("Uscita in corso...");
                        return;
                    }
                    default -> System.out.println("Scelta non valida.");
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }

    private static void aggiungiLibro() {
        try {
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Anno: ");
            int anno = Integer.parseInt(scanner.nextLine());
            System.out.print("Pagine: ");
            int pagine = Integer.parseInt(scanner.nextLine());
            System.out.print("Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Genere: ");
            String genere = scanner.nextLine();

            archivio.aggiungiLibro(new Libro(isbn, titolo, anno, pagine, autore, genere));
            System.out.println("Libro aggiunto con successo!");
        } catch (NumberFormatException e) {
            System.out.println("Errore: anno o pagine non sono numeri validi.");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta del libro: " + e.getMessage());
        }
    }


    private static void cercaIsbn() {
        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println(archivio.cercaPerIsbn(isbn));
    }

    private static void rimuoviLibro() {
        System.out.print("ISBN da rimuovere: ");
        archivio.rimuoviLibro(scanner.nextLine());
    }

    private static void cercaAnno() {
        System.out.print("Anno di pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());
        archivio.cercaPerAnno(anno).forEach(System.out::println);
    }

    private static void cercaAutore() {
        System.out.print("Autore: ");
        archivio.cercaPerAutore(scanner.nextLine()).forEach(System.out::println);
    }

    private static void aggiornaLibro() {
        System.out.print("ISBN del libro da aggiornare: ");
        String isbn = scanner.nextLine();
        System.out.print("Nuovo titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Nuovo anno: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuove pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());
        System.out.print("Nuovo autore: ");
        String autore = scanner.nextLine();
        System.out.print("Nuovo genere: ");
        String genere = scanner.nextLine();

        archivio.aggiornaLibro(isbn, new Libro(isbn, titolo, anno, pagine, autore, genere));
    }

}
