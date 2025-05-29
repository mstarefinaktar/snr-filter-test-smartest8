
//Reference: General Java Code for FFT-Based SNR

//Below is a simplified, non-SmarTest version of the core algorithm you implemented, using standard Java + JTransforms:



import org.jtransforms.fft.DoubleFFT_1D;
import java.util.*;

public class SimpleSnrCalculator {

    public static double computeSNR(double[] waveform) {
        int n = waveform.length;
        DoubleFFT_1D fft = new DoubleFFT_1D(n);
        double[] fftData = Arrays.copyOf(waveform, n * 2);
        fft.realForwardFull(fftData);

        double[] power = new double[n];
        for (int i = 0; i < n; i++) {
            double re = fftData[2 * i];
            double im = fftData[2 * i + 1];
            power[i] = re * re + im * im;
        }

        int peakIndex = 0;
        double maxPower = -1;
        for (int i = 1; i < n / 2; i++) {
            if (power[i] > maxPower) {
                maxPower = power[i];
                peakIndex = i;
            }
        }

        double signalPower = maxPower;
        double noisePower = 0;
        for (int i = 1; i < n / 2; i++) {
            if (Math.abs(i - peakIndex) > 2) {
                noisePower += power[i];
            }
        }

        return 10 * Math.log10(signalPower / noisePower);
    }

    public static void main(String[] args) {
        double[] waveform = new double[1024];
        Random rand = new Random();
        for (int i = 0; i < waveform.length; i++) {
            waveform[i] = Math.sin(2 * Math.PI * i / 64) + 0.05 * rand.nextGaussian();
        }

        double snr = computeSNR(waveform);
        System.out.printf("SNR: %.2f dB\n", snr);
    }
}

/*Notes

The actual implementation uses Advantest SmarTest 8 Java APIs and internal relay configurations

The Java example provided here is for educational/demo purposes only

Logging, limit checks, GUI parameters, and site-aware control are handled within SmarTest test method conventions

For more examples or a custom integration of this flow, please reach out or fork the generic Java analyzer code.*/
