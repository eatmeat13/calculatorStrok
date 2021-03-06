import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution {
    static char operation;
    static Scanner scanner = new Scanner(System.in);
    static String result;
    static int num;

    public static void main(String[] args) {
        System.out.println("Введите выражение!");
        String vvod = scanner.nextLine();
        operation = Operat.operative(vvod);
        solutionn(vvod);
    }

    public static void solutionn(String vvod) {
        String newvvod =vvod.replace("\"\"", "\" \"");
        String[] massiv = newvvod.split("[+-/*\"]");
        String ms0 = massiv[0];
        if (!ms0.equals("")) {
            throw new ArrayIndexOutOfBoundsException("Сначала нужно ввести выражение в скобках");
        }
        if (massiv.length == 5) {
            String ms1 = massiv[1];
            String ms4 = massiv[4];
            if ( ms1.length() > 10 || ms4.length() > 10) {
                System.out.println("Выражение должно занимать от 1 до 10 символов");
                return;
            }
            result = Calc.calculate(ms1, ms4, operation);
            if (result.length() > 40) {
                String rez = result.substring(0, 40);
                System.out.println(rez + "...");
            } else {
                System.out.println(result);
            }
        } else if (massiv.length==4){


            String ms1 = massiv[1];
            String ms3 = massiv[3];
            if (ms1.length() > 10  || ms3.length() > 10) {
                System.out.println("Выражение должно занимать от 1 до 10 символов");
                return;
            }

            num = Integer.parseInt(ms3);
            result = Calc.calculated(ms1, num, operation);
            if (result.length() > 40) {
                String rez = result.substring(0, 40);
                System.out.println(rez + "...");
            } else {
                System.out.println(result);
            }


        }
        else {
            System.out.println("Вы ввели неправильное выражение");
        }
    }


    public static class Calc {
        public static String calculate(String world1, String world2, char op) {
            switch (op) {
                case '+':
                    result = world1 + world2;
                    break;
                case '-':
                    result = world1.replace(world2, "");
                    break;
                case '*':
                case '/':
                    throw new NullPointerException("Неверный знак операции, попробуйте + или -");
                default:
                    throw new IllegalArgumentException("Неверный знак операции");
            }
            return result.trim();
        }

        public static String calculated(String world, int numic, char op) {
            if (numic <1|| numic>10){
                throw new InputMismatchException("Числа могут быть только от 1 до 10");

            }
            switch (op) {
                case '+':
                case '-':
                    throw new NullPointerException("Неверный знак операции, попробуйте * или /");
                case '*':
                    result = world.repeat(numic);
                    break;
                case '/':
                    int resB = world.length() / numic;
                    result = world.substring(0, resB);
                    if (world.length() < numic) {
                        System.out.println("Делимое меньше делителя");
                    }
                    result = world.substring(0, resB);
                    break;
                default:
                    throw new IllegalArgumentException("Не верный знак операции");
            }
            return result.trim();
        }
    }

    public static class Operat {
        public static char operative(String vvod) {
            char[] findop = new char[26];
            for (int i = 0; i < vvod.length(); i++) {
                findop[i] = vvod.charAt(i);
                if (findop[i] == '+') {
                    operation = '+';
                }
                if (findop[i] == '-') {
                    operation = '-';
                }
                if (findop[i] == '*') {
                    operation = '*';
                }
                if (findop[i] == '/') {
                    operation = '/';
                }
            }
            return operation;
        }
    }
}