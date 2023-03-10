import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Массив общей стоимости каждого товара по отдельности
        int[] goods = new int[3];

        //Массив стоимости товаров
        int[] count = {50, 14, 80};

        //Массив информации о каждом товаре
        String[] fullProductInf = {"1. Молоко 50 руб/шт ", "2. Хлеб 14 руб/шт ", "3. Гречневая крупа 80 руб/шт "};

        //Массив отвечает за наличие или отсутствие скидки у каждого вида товара
        boolean[] sale = {false, false, true};

        //Массив проверки наличия товара в корзине
        boolean[] availability = new boolean[3];

        //Количество каждого вида товара
        int[] amnt = new int[3];
        
        while (true) {
            System.out.println("Список возможных товаров для покупки:");
            System.out.println(fullProductInf[0]);
            System.out.println(fullProductInf[1]);
            System.out.println(fullProductInf[2]);
            System.out.println("Выберите товар и количество или введите \"end\"");
            String choice = (scanner.nextLine());

            //Проверка на окончание покупки
            if (choice.equals("end")) {
                System.out.println("Ваша корзина:");

                //Переменная проверки каждого товара из списка
                int number = 0;

                //Общая сумма покупки
                int sum = 0;

                for (boolean accounting : availability) {
                    if (accounting) {
                        System.out.println(fullProductInf[number] + "итого: " + goods[number]);
                        sum += goods[number];
                        number++;
                    } else {
                        number++;
                    }
                }
                System.out.println("Общая сумма покупки: " + sum);
                break;

                //Обработка выбранного товара
            } else {
                String[] parts = choice.split(" ");
                if (parts.length != 2) {
                    System.out.println("Должно быть введено два значения!");
                    continue;
                } else {
                    try {
                        if (Integer.parseInt(parts[0]) < 1 || Integer.parseInt(parts[0]) > 3) {
                            System.out.println("Введен некорректный номер товара");
                            continue;
                        }
                        //Значение типа товара
                        int kind = Integer.parseInt(parts[0]) - 1;
                        //Значение количества товара
                        int amount = Integer.parseInt(parts[1]);

                        if (amnt[kind] + amount < 0) {
                            System.out.println("Количество товара не может быть отрицательным :(");
                            continue;
                        }
                        if (amount == 0 || amnt[kind] + amount == 0) {
                            amnt[kind] = 0;
                            goods[kind] = 0;
                            availability[kind] = false;
                            continue;

                            //Расчет общей стоимости одного вида товара в корзине
                            goods[kind] += amount * count[kind];

                            //Скидочный расчет общей стоимости одного вида товара
                            if (sale[kind]) {
                                int generalAmount = goods[kind] / count[kind];
                                if (generalAmount >= 3) {
                                    int saleCount = generalAmount / 3;
                                    goods[kind] = (generalAmount - saleCount) * count[kind];
                                }
                            }

                            //Подтверждение наличия товара
                            availability[kind] = true;
                            amnt[kind] = amount;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Введено некорректное значение");
                    }
                }
            }
        }
    }
}