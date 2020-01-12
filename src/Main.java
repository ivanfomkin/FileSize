import java.io.File;
import java.util.Scanner;

public class Main {
    private static final double UNIT_CONST = 1024;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            try {
                System.out.print("Enter a path to directory to get size or EXIT: ");
                String path = scanner.nextLine();
                if (path.equalsIgnoreCase("EXIT")) break;
                File fileForCalculateSize = new File(path);
                if (fileForCalculateSize.isDirectory()) {
                    double sizeAllFiles = getSizeAllFiles(fileForCalculateSize);
                    printSizeAtEasyFormat(sizeAllFiles);
                } else
                {
                    System.out.println("This is no valid path!");
                }
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
        if (size > UNIT_CONST) {
            unit = "Kb";
            size /= UNIT_CONST;

            if (size > UNIT_CONST) {
                unit = "Mb";
                size /= UNIT_CONST;

                if (size > UNIT_CONST) {
                    unit = "Gb";
                    size /= UNIT_CONST;
                }

            }

        }


        System.out.println("All files size at this directory is " + String.format("%.2f", size) + " " + unit);
    }
}
