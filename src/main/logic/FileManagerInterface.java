package main.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import main.pojo.Match;

public interface FileManagerInterface {
	public ArrayList<Match> loadMatches() throws IOException;
	
	public void writeMatches(ArrayList<Match> messages) throws FileNotFoundException, IOException;
	
}