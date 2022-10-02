package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.WavAnalysisBinding;

import java.util.List;


public class WavAnalysis extends Fragment {
    public static final String USER_ID = "UserId";

    private @NonNull WavAnalysisBinding binding;

    SharedPreferences sharedPref;
    private UserDb db;
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

        userDAO = UserDb.getInstance(getContext()).getPersonDAO();
        List<User> userList = userDAO.listUsers();
        if(userList.size() == 0) {
            userDAO.insertUser(new User("Test", "pass", ""));
        }

        binding.wavAnalysisBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("wavAnalysis", "WavAnalysisBtn clicked");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
