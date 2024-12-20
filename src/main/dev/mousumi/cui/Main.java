package main.dev.mousumi.cui;
import java.util.Scanner;
import main.dev.mousumi.cui.entities.Word;
import main.dev.mousumi.cui.repository.WordRepository;
import main.dev.mousumi.cui.services.WordService;
import java.util.List;

public class Main {
    private final Scanner scanner;
    private final WordRepository repository;
    private final WordService wordService;

    public Main() {
        scanner = new Scanner(System.in);
        repository = new WordRepository(); // Initialize repository
        wordService = new WordService(repository); // Initialize wordService with repository

        char option;
        while (true) {
            System.out.println("""
                    Select an option:
                    a. Add a new word
                    b. See all words
                    c. Search a word
                    d. Delete a word
                    e. Exit
                    """);
            option = scanner.nextLine().charAt(0);

            switch (option) {
                case 'a' -> {
                    System.out.println("Enter a word");
                    String name = scanner.nextLine();
                    System.out.println("Enter description");
                    String description = scanner.nextLine();
                    Word word = new Word(name, description);
                    wordService.saveWord(word);
                }
                case 'b' -> {
                    List<Word> words = wordService.findAllWords();
                    words.forEach(System.out::println);
                }
                case 'c' -> {
                    System.out.println("Enter query: ");
                    String query = scanner.nextLine();
                    List<Word> matchingWords = wordService.searchWord(query);
                    matchingWords.forEach(System.out::println);
                }
                case 'd' -> {
                    System.out.println("Enter word name: ");
                    String name = scanner.nextLine();
                    wordService.deleteWord(name);
                }
                case 'e' -> System.out.println("Exited");
                default -> System.out.println("Invalid Input");
            }
            if (option == 'e')
                break;
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}



