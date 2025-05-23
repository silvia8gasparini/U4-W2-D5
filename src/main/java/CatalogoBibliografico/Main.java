package CatalogoBibliografico;

import java.util.Scanner;

public class Main {
    public static final Archivio archivio = new Archivio();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Menu Biblioteca");
            System.out.println("1. Aggiungi elemento");
            System.out.println("2. Cerca per ISBN");
            System.out.println("3. Rimuovi elemento");
            System.out.println("4. Cerca per anno");
            System.out.println("5. Cerca per autore");
            System.out.println("6. Aggiorna elemento");
            System.out.println("7. Mostra statistiche");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            try {
                int scelta = Integer.parseInt(scanner.nextLine());
                switch (scelta) {
                    case 1 -> aggiungiElemento();
                    case 2 -> cercaIsbn();
                    case 3 -> rimuoviElemento();
                    case 4 -> cercaAnno();
                    case 5 -> cercaAutore();
                    case 6 -> aggiornaElemento();
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

    private static void aggiungiElemento() {
        System.out.print("Vuoi aggiungere un [L]ibro o una [R]ivista? ");
        String tipo = scanner.nextLine().trim().toLowerCase();

        try {
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Anno di pubblicazione: ");
            int anno = Integer.parseInt(scanner.nextLine());
            System.out.print("Numero pagine: ");
            int pagine = Integer.parseInt(scanner.nextLine());

            if (tipo.equals("l")) {
                System.out.print("Autore: ");
                String autore = scanner.nextLine();
                System.out.print("Genere: ");
                String genere = scanner.nextLine();
                archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
                System.out.println("Libro aggiunto!");
            } else if (tipo.equals("r")) {
                System.out.print("Periodicità (es. Settimanale, Mensile): ");
                String periodicita = scanner.nextLine();
                archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
                System.out.println("Rivista aggiunta!");
            } else {
                System.out.println("Tipo non valido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore: inserire numeri validi per anno e pagine.");
        } catch (Exception e) {
            System.out.println("Errore durante l'aggiunta: " + e.getMessage());
        }
    }

    private static void cercaIsbn() {
        System.out.print("Inserisci ISBN: ");
        String isbn = scanner.nextLine();
        System.out.println(archivio.cercaPerIsbn(isbn));
    }

    private static void rimuoviElemento() {
        System.out.print("ISBN da rimuovere: ");
        archivio.rimuoviElemento(scanner.nextLine());
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

    private static void aggiornaElemento() {
        System.out.print("ISBN dell'elemento da aggiornare: ");
        String isbn = scanner.nextLine();

        ElementoCatalogo esistente = archivio.cercaPerIsbn(isbn);

        try {
            System.out.print("Nuovo titolo: ");
            String titolo = scanner.nextLine();
            System.out.print("Nuovo anno: ");
            int anno = Integer.parseInt(scanner.nextLine());
            System.out.print("Nuove pagine: ");
            int pagine = Integer.parseInt(scanner.nextLine());

            if (esistente instanceof Libro) {
                System.out.print("Nuovo autore: ");
                String autore = scanner.nextLine();
                System.out.print("Nuovo genere: ");
                String genere = scanner.nextLine();
                archivio.aggiornaElemento(isbn, new Libro(isbn, titolo, anno, pagine, autore, genere));
                System.out.println("Libro aggiornato.");
            } else if (esistente instanceof Rivista) {
                System.out.print("Nuova periodicità: ");
                String periodicita = scanner.nextLine();
                archivio.aggiornaElemento(isbn, new Rivista(isbn, titolo, anno, pagine, periodicita));
                System.out.println("Rivista aggiornata.");
            } else {
                System.out.println("Tipo di elemento non riconosciuto.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errore: anno o pagine non validi.");
        }
    }
}
