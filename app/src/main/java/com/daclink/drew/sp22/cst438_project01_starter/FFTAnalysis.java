package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
                File wavFile2 = new File("/storage/self/primary/Android/data/com.daclink.drew.sp22.cst438_project01_starter/files/DCIM/AudioRecorder/instrument2.wav");

                WaveDecoder decoder1 = null;
                try {
                    decoder1 = new WaveDecoder(new FileInputStream(wavFile1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                WaveDecoder decoder2 = null;
                try {
                    decoder2 = new WaveDecoder(new FileInputStream(wavFile2));
                } catch (Exception e) {
                    e.printStackTrace();
                }


                FFT fft = new FFT(1024, 44100);



//                NoOfEmp.add(new Entry(945f, 0));
//                NoOfEmp.add(new Entry(1040f, 1));
//                NoOfEmp.add(new Entry(1133f, 2));
//                NoOfEmp.add(new Entry(1240f, 3));
//                NoOfEmp.add(new Entry(1369f, 4));
//                NoOfEmp.add(new Entry(1487f, 5));
//                NoOfEmp.add(new Entry(1501f, 6));
//                NoOfEmp.add(new Entry(1645f, 7));
//                NoOfEmp.add(new Entry(1578f, 8));
//                NoOfEmp.add(new Entry(1695f, 9));

                LineDataSet linedataset = new LineDataSet(spectralFlux1, "spectrum1");
                LineDataSet linedataset2 = new LineDataSet(spectralFlux2, "spectrum2");
                linedataset2.setColor(Color.RED);
                linedataset.setDrawCircles(false);
                linedataset2.setDrawCircles(false);
                chart.animateY(5000);
                LineData data = new LineData(linedataset, linedataset2);
//                linedataset.setColors(ColorTemplate.COLORFUL_COLORS);
                chart.setData(data);



                Log.d("wavAnalysis", "WavAnalysisBtn clicked");
                System.out.println("Spectral Flux 1:" + spectralFlux1);
                System.out.println("Spectral Flux 2:" + spectralFlux2);
            }
        });
        binding.amplitudaTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FFTAnalysis.this).navigate(R.id.action_WavAnalysis_to_Amplituda);
            }
        });
    }
    public void renderData() {
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(2048f);  //Valore massimo asse X
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



}
