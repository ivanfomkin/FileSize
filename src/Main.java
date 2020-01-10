import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        String path = "F:\\Games\\Steam\\clientui";
        for (;;) {
            try {
                System.out.print("Enter a path to directory to get size or EXIT: ");
                String path = scanner.nextLine();
                double sizeAllFiles = getSizeAllFiles(new File(path));
                if (path.equalsIgnoreCase("EXIT")) break;
                printSizeAtEasyFormat(sizeAllFiles);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static double getSizeAllFiles(File directory) {
        double size = 0;
        if (directory.isDirectory()) {
            File[] filesAtDirectory = directory.listFiles();
            for (File file : filesAtDirectory) {
                size += getSizeAllFiles(file);
            }
        } else {
            size += directory.length();
        }
        return size;
    }

    private static void printSizeAtEasyFormat(double size) {
        String unit = "bytes";
        if (size > 1024) {
            unit = "Kb";
            size /= 1024;

            if (size > 1024) {
                unit = "Mb";
                size /= 1024;

                if (size > 1024) {
                    unit = "Gb";
                    size /= 1024;
                }

            }

        }


        System.out.println("All files size at this directory is " + String.format("%.2f", size) + " " + unit);
    }
}
