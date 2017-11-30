import java.io.FileNotFoundException;

/**
 * Created by Lenovo on 2017-11-30.
 */
public class Main {
    public static void main(String[] args) {
        Network network = new Network(1,35,26);
        try {
            network.loadTrainData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        network.showWeights();
        for (int i = 0; i < 10000; i++) {
            network.train();

        }
        network.showWeights();
        network.test();

    }

}
