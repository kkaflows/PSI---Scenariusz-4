import java.util.Random;

/**
 * Created by Lenovo on 2017-11-30.
 */
public class Neuron {

    public double[] input;
    public double[] weights;
    public double learningRate;
    public double forgetRate;
    public int size;


    public Neuron( double learningRate, double forgetRate, int size) {
        this.input = new double[size];
        this.weights = new double[size];
        this.learningRate = learningRate;
        this.forgetRate = forgetRate;
        this.size = size;

        initializeWeights();
    }

    private void initializeWeights() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            weights[i] = random.nextDouble();
            input[i] = 0;
        }
    }

    double calculateWeightSum(){
        double tmp=0;
        for (int i = 0; i < size; i++) {
            tmp += input[i]*weights[i];
        }
        return tmp;
    }

    double calculateSigmoid(double weightSum){
        return Math.signum(weightSum);
    }
    double calculateSigmoid2(double weightSum){
        return (1 - Math.exp(-weightSum))/(1 + Math.exp(-weightSum));
    }


}
