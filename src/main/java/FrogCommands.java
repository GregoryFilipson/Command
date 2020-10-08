public class FrogCommands implements FrogCommand {
    private final Frog frog;
    private final int steps;

    public FrogCommands(Frog frog, int steps) {
        this.frog = frog;
        this.steps = steps;
    }

    public static FrogCommand jumpRightCommand(Frog frog, int steps) {
        return new FrogCommands(frog, steps);
        // возвращаете объект команды, у которого
        // если вызвать .do(), то лягушка её выполнит,
        // если вызвать .undo(), то лягушка её отменит
    }

    @Override
    public boolean execute() {
        return frog.jump(steps);
    }

    @Override
    public boolean unExecute() {
        return !frog.jump(steps);
    }

    @Override
    public String toString() {
        return "Лягушка сделала следующий прыжок: " + steps;
    }
}
