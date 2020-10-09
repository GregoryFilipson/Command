public class FrogCommands {
    public static FrogCommand jumpCommand(Frog frog, int steps) {
        return new FrogCommand() {

            @Override
            public boolean execute() {
                return frog.jump(steps);
            }

            @Override
            public boolean unExecute() {
                return frog.jump(-steps);
            }
        };
    }
}
