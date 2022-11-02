package com.daclink.drew.sp22.cst438_project01_starter;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.AmplitudaBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentSecondBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.WavAnalysisBinding;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import linc.com.amplituda.Amplituda;
import linc.com.amplituda.AmplitudaProcessingOutput;
import linc.com.amplituda.AmplitudaProgressListener;
import linc.com.amplituda.AmplitudaResult;
import linc.com.amplituda.Cache;
import linc.com.amplituda.Compress;
import linc.com.amplituda.InputAudio;
import linc.com.amplituda.ProgressOperation;
import linc.com.amplituda.callback.AmplitudaErrorListener;
import linc.com.amplituda.exceptions.AmplitudaException;
import linc.com.amplituda.exceptions.io.AmplitudaIOException;

public class Amplitude extends Fragment {
    private AmplitudaBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Amplituda amplituda = new Amplituda(getContext());
        amplituda.setLogConfig(Log.ERROR, true);
        amplituda.processAudio("/storage/self/primary/Android/data/com.daclink.drew.sp22.cst438_project01_starter/files/DCIM/AudioRecorder/1666998261700.wav").get(result -> {
            List<Integer> amplitudesData = result.amplitudesAsList();
            for(int i = 0; i < amplitudesData.size(); i++) {
                Log.d("Amplituda", String.valueOf(amplitudesData.get(i)));
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
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = AmplitudaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.TESTBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

    }



}
