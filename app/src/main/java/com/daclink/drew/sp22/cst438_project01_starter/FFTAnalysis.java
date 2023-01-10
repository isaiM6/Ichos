package com.daclink.drew.sp22.cst438_project01_starter;

import static java.lang.Float.MAX_VALUE;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.provider.MediaStore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.FftAnalyzingBinding;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import linc.com.amplituda.Amplituda;
import linc.com.amplituda.AmplitudaResult;
import linc.com.amplituda.InputAudio;
import linc.com.amplituda.exceptions.io.AmplitudaIOException;

public class FFTAnalysis extends Fragment{

    private @NonNull
    FftAnalyzingBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("FFT Analysis");
        binding = FftAnalyzingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fftAnalysisBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LineChart chart = binding.fftchart;

                File wavFile1 = new File("/storage/self/primary/Android/data/com.daclink.drew.sp22.cst438_project01_starter/files/DCIM/AudioRecorder/instrument1.wav");
                //File wavFile1 = new File("/storage/self/primary/Android/media/judith.wav");
                File wavFile2 = new File("/storage/self/primary/Android/data/com.daclink.drew.sp22.cst438_project01_starter/files/DCIM/AudioRecorder/instrument2.wav");
                String root = Environment.getRootDirectory().getAbsolutePath();
                System.out.println("TESTING ROOT DIRECTORY_________" + root);
                //File to = new File(Environment.getExternalStorage().getAbsolutePath()+"/folder2/file.ext");
                //from.renameTo(to);

                WaveDecoder decoder = null;
                try {
                    decoder = new WaveDecoder(new FileInputStream(wavFile1));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                WaveDecoder decoder2 = null;
                try {
                    decoder2 = new WaveDecoder(new FileInputStream(wavFile2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                FFT fft = new FFT(8192, 44100);
                FFT fft2 = new FFT(8192, 44100);


                float[] samples = new float[8192];
                float[] samples2 = new float[8192];
                ArrayList spectralFlux = new ArrayList();
                ArrayList spectralFlux2 = new ArrayList();




                int j = 0;

                while (decoder.readSamples(samples) > 0) {
                    fft.forward(samples);
                }
                while (decoder2.readSamples(samples2) > 0) {
                    fft2.forward(samples2);
                }

                float[] amplitudes = fft.getSpectrum();
                float[] amplitudes2 = fft2.getSpectrum();
                float[] frequencies = new float[amplitudes.length];
                float[] frequencies2 = new float[amplitudes2.length];



                float max_magnitude = amplitudes[0];
                float min_magnitude = amplitudes[0];
                float max_magnitude2 = amplitudes2[0];
                float min_magnitude2 = amplitudes2[0];

                for (int i = 1; i < frequencies.length-1; i++){
                    if (amplitudes[i] > max_magnitude){
                        max_magnitude = amplitudes[i];
                    }
                }

                for (int i = 0; i < frequencies.length; i++){
                    if (amplitudes[i] < min_magnitude){
                        min_magnitude = amplitudes[i];
                    }
                }

                for (int i = 1; i < frequencies2.length-1; i++){
                    if (amplitudes2[i] > max_magnitude2){
                        max_magnitude2 = amplitudes2[i];
                    }
                }

                for (int i = 0; i < frequencies2.length; i++){
                    if (amplitudes2[i] < min_magnitude2){
                        min_magnitude2 = amplitudes2[i];
                    }
                }

                float freq = 0;
                float freq2 = 0;

                for(int i = 0; i < frequencies.length; i++){
                    amplitudes[i] = amplitudes[i]/(max_magnitude-min_magnitude);
                    freq = i * (44100/8192);
                    frequencies[i] = freq;
                }

                for(int i = 0; i < frequencies2.length; i++){
                    amplitudes2[i] = amplitudes2[i]/(max_magnitude2-min_magnitude2);
                    freq2 = i * (44100/8192);
                    frequencies2[i] = freq2;
                }

                for (int n = 0; n < frequencies.length; n++){
                    spectralFlux.add(new Entry(frequencies[n], amplitudes[n]));
                }

                for (int n = 0; n < frequencies2.length; n++){
                    spectralFlux2.add(new Entry(frequencies2[n], amplitudes2[n]));
                }






                LineDataSet linedataset = new LineDataSet(spectralFlux, "Spectrum 1");
                LineDataSet linedataset2 = new LineDataSet(spectralFlux2, "Spectrum 2");
                linedataset.setColor(Color.BLACK);
                linedataset.setLineWidth(1);
                linedataset2.setColor(Color.RED);
                linedataset2.setLineWidth(1);
                linedataset.setDrawCircles(false);
                linedataset2.setDrawCircles(false);
                chart.animateY(5000);
                LineData data = new LineData(linedataset,linedataset2);
//              linedataset.setColors(ColorTemplate.COLORFUL_COLORS);

                chart.setData(data);
                chart.setVisibleXRangeMaximum(800);

            }
        });

    }

    /*
    public void renderData() {
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(8192f);  //Valore massimo asse X
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawLimitLinesBehindData(true);

        LimitLine ll1 = new LimitLine(215f, "Maximum Limit");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        LimitLine ll2 = new LimitLine(70f, "Minimum Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);

        mChart.getAxisRight().setEnabled(false);
        setData(true);

    }
*/


}
