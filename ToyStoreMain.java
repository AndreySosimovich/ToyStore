public class ToyStoreMain implements InputReader {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        System.out.println("Добро пожаловать в Игрушечный Магазин!");

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить игрушку");
            System.out.println("2. Играть");
            System.out.println("3. Посмотреть сохраненные игрушки");
            System.out.println("4. Выйти");

            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер

            switch (choice) {
                case 1:
                    System.out.print("Введите ID игрушки: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Очищаем буфер
                    System.out.print("Введите название игрушки: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите вероятность выигрыша в процентах: ");
                    int winningProbability = scanner.nextInt();
                    toyStore.put(id, name, winningProbability);
                    break;
                case 2:
                    System.out.print("Введите количество раундов для игры: ");
                    int rounds = scanner.nextInt();
                    toyStore.playGame(rounds);
                    break;
                case 3:
                    toyStore.viewToys();
                    break;
                case 4:
                    System.out.println("Выход из Игрушечного Магазина. До свидания!");
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}
