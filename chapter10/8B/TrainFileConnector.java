import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TrainFileConnector {

    public void saveTrain(String fileName, Train train) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(train);
        }
    }

    public Train loadTrain(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Train) in.readObject();
        }
    }

    public void addWagon(String fileName, Wagon wagon) throws IOException, ClassNotFoundException {
        Train train = loadTrain(fileName);
        train.addWagon(wagon);
        saveTrain(fileName, train);
    }

    public void removeWagonByNumber(String fileName, int number) throws IOException, ClassNotFoundException {
        Train train = loadTrain(fileName);
        train.removeWagonByNumber(number);
        saveTrain(fileName, train);
    }
}
