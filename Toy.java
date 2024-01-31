import java.io.Serializable;

public class Toy implements Comparable<Toy>, Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int winningProbability; // Вероятность выигрыша в процентах

    public Toy(int id, String name, int winningProbability) {
        this.id = id;
        this.name = name;
        this.winningProbability = winningProbability;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWinningProbability() {
        return winningProbability;
    }

    @Override
    public int compareTo(Toy otherToy) {
        return Integer.compare(this.id, otherToy.id);
    }

    @Override
    public String toString() {
        return "Игрушка{" +
                "id=" + id +
                ", название='" + name + '\'' +
                ", вероятность выигрыша=" + winningProbability + "%" +
                '}';
    }
}
