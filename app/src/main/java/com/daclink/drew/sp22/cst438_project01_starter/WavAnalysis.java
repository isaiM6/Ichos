package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.SharedPreferences;
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


public class WavAnalysis extends Fragment {
    //public static final String USER_ID = "UserId";

    private @NonNull WavAnalysisBinding binding;

    //SharedPreferences sharedPref;
    //private UserDb db;
    UserDAO userDAO;


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

                ArrayList NoOfEmp = new ArrayList();

                NoOfEmp.add(new Entry(945f, 0));
                NoOfEmp.add(new Entry(1040f, 1));
                NoOfEmp.add(new Entry(1133f, 2));
                NoOfEmp.add(new Entry(1240f, 3));
                NoOfEmp.add(new Entry(1369f, 4));
                NoOfEmp.add(new Entry(1487f, 5));
                NoOfEmp.add(new Entry(1501f, 6));
                NoOfEmp.add(new Entry(1645f, 7));
                NoOfEmp.add(new Entry(1578f, 8));
                NoOfEmp.add(new Entry(1695f, 9));

                LineDataSet linedataset = new LineDataSet(NoOfEmp, "No Of Employee");
                chart.animateY(5000);
                LineData data = new LineData(linedataset);
                linedataset.setColors(ColorTemplate.COLORFUL_COLORS);
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
