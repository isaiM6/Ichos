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

import com.daclink.drew.sp22.cst438_project01_starter.databinding.WavAnalysisBinding;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import linc.com.amplituda.Amplituda;
import linc.com.amplituda.AmplitudaResult;
import linc.com.amplituda.InputAudio;
import linc.com.amplituda.exceptions.io.AmplitudaIOException;


public class WavAnalysis extends Fragment {

    private @NonNull WavAnalysisBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = WavAnalysisBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.wavAnalysisBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LineChart chart = binding.chart;

                ArrayList amplitude1 = new ArrayList();
                ArrayList amplitude2 = new ArrayList();

                //Set data for amplitude 1
                Amplituda amplituda = new Amplituda(getContext());
                amplituda.setLogConfig(Log.ERROR, true);
                amplituda.processAudio("/storage/self/primary/Android/data/com.daclink.drew.sp22.cst438_project01_starter/files/DCIM/AudioRecorder/instrument1.wav").get(result -> {
                    List<Integer> amplitudesData = result.amplitudesAsList();
                    for(int i = 0; i < amplitudesData.size(); i++) {
                        Log.d("Amplituda", String.valueOf(amplitudesData.get(i)));
                        amplitude1.add(new Entry(i, amplitudesData.get(i)));
                    }
                    /// List<Integer> amplitudesForFirstSecond = result.amplitudesForSecond(1);
                    long duration = result.getAudioDuration(AmplitudaResult.DurationUnit.SECONDS);
                    String source = result.getAudioSource();
                    InputAudio.Type sourceType = result.getInputAudioType();
                    // etc
                }, exception -> {
                    if (exception instanceof AmplitudaIOException) {
                        System.out.println("IO Exception!");
                    }
                });
                //Set data for amplitude 2
                Amplituda amplituda2 = new Amplituda(getContext());
                amplituda.setLogConfig(Log.ERROR, true);
                amplituda.processAudio("/storage/self/primary/Android/data/com.daclink.drew.sp22.cst438_project01_starter/files/DCIM/AudioRecorder/instrument2.wav").get(result -> {
                    List<Integer> amplitudesData2 = result.amplitudesAsList();
                    for(int i = 0; i < amplitudesData2.size(); i++) {
                        Log.d("Amplituda", String.valueOf(amplitudesData2.get(i)));
                        amplitude2.add(new Entry(i, amplitudesData2.get(i)));
                    }
                    /// List<Integer> amplitudesForFirstSecond = result.amplitudesForSecond(1);
                    long duration = result.getAudioDuration(AmplitudaResult.DurationUnit.SECONDS);
                    String source = result.getAudioSource();
                    InputAudio.Type sourceType = result.getInputAudioType();
                    // etc
                }, exception -> {
                    if (exception instanceof AmplitudaIOException) {
                        System.out.println("IO Exception!");
                    }
                });

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

                LineDataSet linedataset = new LineDataSet(amplitude1, "Amplitude1");
                LineDataSet linedataset2 = new LineDataSet(amplitude2, "Amplitude2");
                linedataset2.setColor(Color.RED);
                linedataset.setColor(Color.GREEN);
                linedataset.setDrawCircles(false);
                linedataset2.setDrawCircles(false);
                linedataset.setLineWidth(2f);
                linedataset2.setLineWidth(2f);
                chart.animateY(3000);
                LineData data = new LineData(linedataset, linedataset2);
//                linedataset.setColors(ColorTemplate.COLORFUL_COLORS);
                chart.setData(data);



                Log.d("wavAnalysis", "WavAnalysisBtn clicked");
            }
        });
        binding.amplitudaTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(WavAnalysis.this).navigate(R.id.action_WavAnalysis_to_Amplituda);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
