package main.worker;

import main.barbeque.Barbeque;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;

public class Seller extends Worker{
    public Seller(Barbeque barbeque) {
        super(barbeque);
    }

    @Override
    public void run() {
        File file = new File("Report "+ LocalDate.now()+".txt");
        try(PrintStream ps = new PrintStream(file)){
            while (true){
                barbeque.makePortionsAndFillArchive(this, ps);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
