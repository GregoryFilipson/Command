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
        List<FrogCommands> commands = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int curCommand = -1;
        while (true) {
            System.out.println("Введи команду или 0 для выхода");
            String input = reader.readLine();
            if (input.equals("0")) break;
            if (input.equals("<<")) {
                if (curCommand < 0) {
                    System.out.println("Нечего отменять!");
                } else {
                    commands.get(curCommand).unExecute();
                    curCommand--;
                }
            } else if (input.equals(">>")) {
                if (curCommand == commands.size() - 1) {
                    System.out.println("Нечего отменять!");
                } else {
                    curCommand++;
                    commands.get(curCommand).execute();
                }
            } else { //пользователь ввёл новое движение для лягушки
                if (curCommand != commands.size() - 1) {
                    //удаляем все команды которые были отменены
                } else {
                    FrogCommands cmd = new FrogCommands(frog, Integer.parseInt(input));
                    curCommand++;
                    commands.add(cmd);
                    cmd.execute();
                }
            }
            System.out.println(frog);
        }
        System.out.println(commands);
    }
}
