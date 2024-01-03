package utils;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class GameRecorder {
    FileWriter writer;
    public GameRecorder(){
    }
    public void write(String boardState) throws IOException {
        try {
          writer = new FileWriter("checkpoint.txt");

        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        writer.write(boardState);
        writer.close();
    }
    public void close() throws IOException {

    }

}