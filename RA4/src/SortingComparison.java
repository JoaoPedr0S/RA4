import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SortingComparison {

    // Bubble Sort
    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    // Quick Sort
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Método para ler arquivos CSV e retornar os dados como um array de inteiros
    public static int[] readCSV(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath))
                .map(line -> line.replaceAll("[^0-9]", "")) // Remove qualquer caractere que não seja número
                .filter(line -> !line.isEmpty()) // Filtra linhas vazias resultantes da remoção
                .mapToInt(Integer::parseInt)
                .toArray();
    }


    public static void main(String[] args) throws IOException {
        // Define o diretório base onde os arquivos estão localizados
        String baseDir = "C:\\Users\\user\\IdeaProjects\\RA4\\src\\"; // Ajuste conforme seu diretório real

        // Lista de nomes de arquivos para os conjuntos de dados
        String[] fileNames = {
                "aleatorio_100.csv", "aleatorio_1000.csv", "aleatorio_10000.csv",
                "crescente_100.csv", "crescente_1000.csv", "crescente_10000.csv",
                "decrescente_100.csv", "decrescente_1000.csv", "decrescente_10000.csv"
        };

        for (String fileName : fileNames) {
            // Constrói o caminho completo do arquivo usando Paths.get para evitar problemas
            Path filePath = Paths.get(baseDir, fileName);
            int[] data = readCSV(filePath.toString());

            // Bubble Sort
            int[] bubbleData = Arrays.copyOf(data, data.length);
            long startTime = System.nanoTime();
            bubbleSort(bubbleData);
            long endTime = System.nanoTime();
            System.out.println("Bubble Sort (" + filePath + "): " + (endTime - startTime) + " ns");

            // Insertion Sort
            int[] insertionData = Arrays.copyOf(data, data.length);
            startTime = System.nanoTime();
            insertionSort(insertionData);
            endTime = System.nanoTime();
            System.out.println("Insertion Sort (" + filePath + "): " + (endTime - startTime) + " ns");

            // Quick Sort
            int[] quickData = Arrays.copyOf(data, data.length);
            startTime = System.nanoTime();
            quickSort(quickData, 0, quickData.length - 1);
            endTime = System.nanoTime();
            System.out.println("Quick Sort (" + filePath + "): " + (endTime - startTime) + " ns");

            System.out.println();
        }
    }

    // ... (Demais métodos: bubbleSort, insertionSort, quickSort, readCSV)
}
