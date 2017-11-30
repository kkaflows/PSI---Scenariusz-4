import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Lenovo on 2017-11-30.
 */
public class Network {

    int neuronCount;
    Neuron[] neurons;
    int inputSize;
    int inputCount;
    double[][] input;
    Scanner scanner;

    public Network(int neuronCount, int inputSize, int inputCount) {
        this.neuronCount = neuronCount;
        this.inputSize = inputSize;
        this.inputCount = inputCount;
        this.neurons = new Neuron[neuronCount];
        for (int i = 0; i < neuronCount; i++) {
            neurons[i] = new Neuron(0.1,0.1,35);
        }
        this.input = new double[inputCount][inputSize];
        try {
            loadTrainData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadTrainData() throws FileNotFoundException {
        try {
             scanner = new Scanner(new File("learnOnlyLetterBinary2.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < inputCount; i++) {
            for (int j = 0; j < inputSize; j++) {
                input[i][j] = scanner.nextDouble();
            }
        }
    }
    public void showInput(){
        for (int i = 0; i < inputCount; i++) {
            for (int j = 0; j < inputSize; j++) {
                System.out.println(input[i][j]);
            }
        }
    }
    public void showWeights(){
        for (int i = 0; i < neuronCount; i++) {
            for (int j = 0; j < inputSize; j++) {
                System.out.println("Neuron "+i+" Weight "+j+ " = "+neurons[i].weights[j]);
            }
        }
    }

    public void train(){

        for (int letterNumber = 0; letterNumber < inputCount; letterNumber++) {


            for (int i = 0; i < neuronCount; i++) {
                for (int j = 0; j < inputSize; j++) {
                    neurons[i].input[j] = input[letterNumber][j];
                }
            }
            double[] weightSum = new double[neuronCount];
            for (int i = 0; i < neuronCount; i++) {
                weightSum[i] = neurons[i].calculateWeightSum();
//                System.out.println(weightSum[i]);
            }

            for (int i = 0; i < neuronCount; i++) {
                for (int j = 0; j < inputSize; j++) {

//                    double deltaW = neurons[i].learningRate * neurons[i].input[j] * neurons[i].calculateSigmoid(weightSum[i]);
//                    neurons[i].weights[j] = neurons[i].weights[j] * (1 - neurons[i].forgetRate) + deltaW;
                    neurons[i].weights[j] = neurons[i].weights[j]*neurons[i].forgetRate + neurons[i].learningRate*neurons[i].calculateSigmoid(weightSum[i])*(neurons[i].input[j] - neurons[i].weights[j]);

                }
            }
        }
    }

    public void test(){
        for (int letterNumber = 0; letterNumber <inputCount ; letterNumber++) {


            double[] result = new double[neuronCount];

            for (int i = 0; i < neuronCount; i++) {
                for (int j = 0; j < inputSize; j++) {
                    neurons[i].input[j] = input[letterNumber][j];
                }
            }
            for (int i = 0; i < neuronCount; i++) {
                double weightSum = neurons[i].calculateWeightSum();
                result[i] = neurons[i].calculateSigmoid2(weightSum);
            }
//            System.out.println("Letter = " + (char) (letterNumber + 65));
//            for (int i = 0; i < neuronCount; i++) {
//                System.out.println("Neuron "+i+ " = "+result[i]);
//            }
            System.out.println((char)(letterNumber + 65) + " "+result[0]);




        }
    }

}
