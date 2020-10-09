public class Frog {

    public static final int MIN_POSITION = 0;
    public static final int MAX_POSITION = 10;

    protected int position;

    public Frog() {
        position = 5;
    }

    public boolean jump(int steps) {
        int startPosition = position;
        position += steps;
        if (position < MIN_POSITION || position > MAX_POSITION) {
            System.out.println("Из ведра не выпрыгнешь!");
            position = startPosition;
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Лягушка на позиции " + position;
    }
}
