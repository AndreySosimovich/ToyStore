import java.io.*;
import java.util.*;

public class ToyStore {
    private List<Toy> toys;
    private Queue<Toy> toyQueue;

    public ToyStore() {
        toys = new ArrayList<>();
        toyQueue = new PriorityQueue<>();
        loadToys();
    }

    private void loadToys() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("toys.dat"))) {
            List<Toy> loadedToys = (List<Toy>) ois.readObject();
            toys.addAll(loadedToys);
            toyQueue.addAll(loadedToys);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Сохраненных игрушек не найдено. Начинаем с чистого листа.");
        }
    }

    private void saveToys() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("toys.dat"))) {
            oos.writeObject(new ArrayList<>(toys));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put(int id, String name, int winningProbability) {
        for (Toy existingToy : toys) {
            if (existingToy.getId() == id) {
                System.out.println("Ошибка: Игрушка с таким ID уже существует.");
                return;
            }
        }

        Toy toy = new Toy(id, name, winningProbability);
        toys.add(toy);
        toyQueue.add(toy);
        saveToys();
        System.out.println("Игрушка добавлена успешно!");
    }

    public Toy getToy() {
        if (toyQueue.isEmpty()) {
            return null;
        }

        List<Toy> shuffledToys = new ArrayList<>(toyQueue);
        Collections.shuffle(shuffledToys);

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;

        int cumulativeWeight = 0;
        for (Toy toy : shuffledToys) {
            cumulativeWeight += toy.getWinningProbability();
            if (randomNumber <= cumulativeWeight) {
                toyQueue.remove(toy);
                saveToys();
                return toy;
            }
        }

        return null;
    }

    public void playGame(int rounds) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("result.txt", true), "UTF-8")))) {
            for (int i = 0; i < rounds; i++) {
                Toy winningToy = getToy();
                if (winningToy != null) {
                    String result = "Раунд " + (i + 1) + ": " + new String(winningToy.getName().getBytes("UTF-8"), "UTF-8");
                    writer.println(result);
                    System.out.println(result);
                } else {
                    String result = "Раунд " + (i + 1) + ": без выигрыша";
                    writer.println(result);
                    System.out.println(result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewToys() {
        System.out.println("\nСохраненные игрушки:");
        try {
            for (Toy toy : toys) {
                String toyInfo = "Игрушка{" +
                        "id=" + toy.getId() +
                        ", название='" + new String(toy.getName().getBytes("UTF-8"), "UTF-8") + '\'' +
                        ", вероятность выигрыша=" + toy.getWinningProbability() + "%" +
                        '}';
                System.out.println(toyInfo);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
