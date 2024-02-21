
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = input.nextLine();
        Main.calc(exp);

    }
}
class Main{
    public static String calc(String exp){
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};

// Проверка знака операции
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        if (actionIndex == -1) {
            throw new RuntimeException("Неверный знак вычислительной операции");
        }


// "достаем" числа из введенной строки
        String[] data = exp.split(regexActions[actionIndex]);
        if ((data.length !=2)) {
            throw new RuntimeException("Допустимы операции только с двумя числами");
        }


        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
            int a, b;
            boolean isRoman = converter.isRoman(data[0]);
            if (isRoman) {
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            } else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }


            if ((a > 10) || (a < 0) || (b > 10) || (b < 0)) {
                throw new RuntimeException("Для ввода доступный числа от 0 до 10");
            }

            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    if (b == 0)
                        throw new RuntimeException("Делить на ноль нельзя!");
                    result = a / b;
                    break;
            }
            if(isRoman){
                System.out.println(converter.intToRoman(result));

            }
            else {
                System.out.println(result);
            }
        }else{
            throw new RuntimeException("числа должны быть в одном формате");
        }


        return exp;
    }



}
