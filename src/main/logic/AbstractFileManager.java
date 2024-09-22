package main.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public abstract class AbstractFileManager {
    protected String path = "C://trastero//";
    protected String fileName = path + "Resultados.dat";

    public class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(FileOutputStream fileOutputStream) throws IOException {
            super(fileOutputStream);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }

    protected boolean fileIsEmpty(String file) {
        return new File(file).length() == 0;
    }
}