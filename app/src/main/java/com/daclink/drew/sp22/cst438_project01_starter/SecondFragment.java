package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.drew.sp22.cst438_project01_starter.api_implementation.NewsResultsAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.api_implementation.apis.NewsSearchService;
import com.daclink.drew.sp22.cst438_project01_starter.api_implementation.models.NewsResultsResponse;
import com.daclink.drew.sp22.cst438_project01_starter.api_implementation.view_model.NewsViewModel;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentSecondBinding;

/**
 * Fragment displaying the search page
 */
public class SecondFragment extends Fragment implements View.OnClickListener {

    private FragmentSecondBinding binding;

    private User user;
    private UserDAO userDAO;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Check if logged in
        userDAO = UserDb.getInstance(getContext()).getPersonDAO();
        if (getArguments() != null) {
            int userId = getArguments().getInt(FirstFragment.USER_ID);
            user = userDAO.getUser(userId);
            if(user == null) { logout(); }
        } else { logout(); }

        binding = FragmentSecondBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.logoutBtn.setOnClickListener(view1 -> logout());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    /**
     * Used to logout the user. Clears the shared preference saving the logged in user and
     * returns to the login screen.
     */
    public void logout() {
        SharedPreferences sharedPref = requireContext().getSharedPreferences("SAVED_PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(FirstFragment.USER_ID, -1).apply();
        NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SecondFragment_to_FirstFragment);
    }

    @Override
    public void onClick(View v) {

    }
}