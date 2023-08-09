import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String arg = in.nextLine();

        System.out.printf("Это говорит приложение из командной строки: %s", arg);
    }
}