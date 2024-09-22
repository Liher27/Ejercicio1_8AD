package main.logic;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import main.pojo.Match;

public class FileManager extends AbstractFileManager implements FileManagerInterface {

	public ArrayList<Match> loadMatches() throws IOException {
		ArrayList<Match> matches = new ArrayList<Match>();
		FileInputStream fileInputStream = new FileInputStream(new File(fileName));
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		try {
			Match match = null;
			while (fileInputStream.getChannel().position() < fileInputStream.getChannel().size()) {
				match = (Match) objectInputStream.readObject();
				matches.add(match);
			}
		} catch (EOFException e) {
			return null;
		} catch (StreamCorruptedException x) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
		objectInputStream.close();
		
		return matches;
	}

	public void writeMatches(ArrayList<Match> matches) throws FileNotFoundException, IOException {
	    File file = new File(fileName);
	    FileOutputStream fileOutputStream = new FileOutputStream(file, true); // Append mode
	    ObjectOutputStream objectOutputStream;
	    
	    if (file.length() == 0) {
	        objectOutputStream = new ObjectOutputStream(fileOutputStream);
	    } else {
	        objectOutputStream = new AppendableObjectOutputStream(fileOutputStream);
	    }
	    
	    for (Match match : matches) {
	        objectOutputStream.writeObject(match);
	    }
	    
	    objectOutputStream.close();
	    fileOutputStream.close();
	}
}