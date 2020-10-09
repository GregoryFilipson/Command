import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Frog frog = new Frog();
        System.out.println("Привет! Давай поиграем?");
        System.out.println("Ниже появится список доступных команд для лягушки!");
        Thread.sleep(1000);
        System.out.println("+N - прыгни на N шагов направо\n" +
                "-N - прыгни на N шагов налево\n" +
                "<< - Undo (отмени последнюю команду)\n" +
                ">> - Redo (повтори отменённую команду)\n" +
                "!! - повтори последнюю команду\n" +
                "0 - выход");
        List<FrogCommand> commands = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int curCommand = -1;
        while (true) {
            System.out.println("Введи команду или 0 для выхода");
            String input = reader.readLine();
            if (input.equals("0")) break;
            switch (input) {
                case "<<":
                    if (curCommand < 0) {
                        System.out.println("Нечего отменять!");
                    } else {
                        commands.get(curCommand).unExecute();
                        curCommand--;
                    }
                    break;
                case ">>":
                    if (curCommand == commands.size() - 1) {
                        System.out.println("Нечего вернуть!");
                    } else {
                        curCommand++;
                        commands.get(curCommand).execute();
                    }
                    break;
                case "!!":
                    if (curCommand == -1) {
                        System.out.println("Нечего повторять!");
                    } else {
                        if (commands.get(curCommand).execute()) {
                            commands.add(commands.get(curCommand));
                            curCommand++;
                        } else {
                            System.out.println("До этого не было прыжка");
                        }
                    }
                    break;
                default:
                    if (curCommand != commands.size() - 1) {
                        for (int i = curCommand + 1; i < commands.size(); i++) {
                            commands.remove(i);
                        }
                    } else {
                        FrogCommand cmd = FrogCommands.jumpCommand(frog, Integer.parseInt(input));
                        curCommand++;
                        commands.add(cmd);
                        cmd.execute();
                    }
                    break;
            }
            System.out.println(frog);
        }
        printField(frog.getPosition());
    }

    public static void printField(int currentPosition) {
        for (int i = Frog.MIN_POSITION; i <= Frog.MAX_POSITION; i++) {
            if (i == currentPosition) {
                System.out.print('X');
            } else {
                System.out.print('-');
            }
        }
        System.out.println();
    }
}