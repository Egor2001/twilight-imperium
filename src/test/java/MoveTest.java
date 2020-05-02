import base.GameController;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MoveTest extends Assert {
    @Test
    public void FighterCanNotMove () {
        try {
            DoTestFromFile("test1");
            CheckOutput("test1");
        }
        catch (Exception e)
        {
            fail();
        }
    }
    @Test
    public void MoveWithShipInside () {
        try {
            DoTestFromFile("test2");
            CheckOutput("test2");
        }
        catch (Exception e)
        {
            fail();
        }
    }
    @Test
    public void TooLongMove() {
        try {
            DoTestFromFile("test3");
            CheckOutput("test3");
        }
        catch (Exception e)
        {
            fail();
        }
    }
    @Test
    public void LongMove() {
        try {
            DoTestFromFile("test4");
            CheckOutput("test4");
        }
        catch (Exception e)
        {
            fail();
        }
    }

    private void DoTestFromFile(String test_name) throws FileNotFoundException {
        try {
            File input_file = new File("test/" + test_name + "/commands.txt");

            FileWriter fstream1 = new FileWriter("test/" + test_name + "/out_text.txt");// конструктор с одним параметром - для перезаписи
            BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
            out1.write(""); // очищаем, перезаписав поверх пустую строку
            out1.close(); // закрываем


            FileOutputStream fileOutputStream = new FileOutputStream("test/" + test_name + "/out_text.txt", true);

            GameController.Delete();

            GameController gameController = GameController.getInstance( new FileInputStream(input_file), new PrintStream(fileOutputStream));
            gameController.start();

        }
        catch (Exception ex) {
            fail();
        }
    }

    private void CheckOutput(String test_name){
        try {
            Object[] correct = Files.lines(Paths.get("test/" + test_name + "/expected_output.txt"), StandardCharsets.UTF_8).toArray();
            Object[] real = Files.lines(Paths.get("test/" + test_name + "/out_text.txt"), StandardCharsets.UTF_8).toArray();

            int sz = 0;
            int other_sz = 0;

            for (Object x: correct)  {
                sz += 1;
            }
            for (Object x: real)  {
                other_sz += 1;
            }

            if (sz != other_sz) {
                fail();
                return;
            }

            for (int i = 0; i < sz; ++i)  {
                if (!((String) correct[i]).equals((String)real[i])) {
                    fail();
                }
            }
        }
        catch (Exception ignored)
        {
            fail();
        }
    }
}