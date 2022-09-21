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
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentFirstBinding;

import java.util.List;
import java.util.Objects;

/**
 * Fragment displaying the login page
 */
public class FirstFragment extends Fragment {
    public static final String USER_ID = "UserId";
    private FragmentFirstBinding binding;

    EditText mUsername, mPassword;
    TextView mErrorMessage;
    UserDAO userDAO;

    SharedPreferences sharedPref;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Check if already logged in
        sharedPref = requireContext().getSharedPreferences("SAVED_PREFS", Context.MODE_PRIVATE);
        int userId = sharedPref.getInt(USER_ID, -1);
        if(userId != -1) { login(userId); }

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup for login
        mUsername = binding.username;
        mPassword = binding.password;
        mErrorMessage = binding.errorMessage;
        userDAO = UserDb.getInstance(getContext()).getPersonDAO();

        mErrorMessage.setVisibility(View.INVISIBLE);
        List<User> userList = userDAO.listUsers();
        if(userList.size() == 0) {
            userDAO.insertUser(new User("Test", "pass", ""));
        }

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Try to login
                String loginUsername = mUsername.getText().toString();
                String loginPassword = mPassword.getText().toString();
                User user = userDAO.getUser(loginUsername);

                if(user != null && user.getPassword().equals(loginPassword)) {
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(USER_ID, user.uid).apply();
                    login(user.uid);
                } else {
                    mErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.registrationBtn.setOnClickListener(view1 -> register());

    }

    /**
     * Used to navigate to the user register fragment
     */
    public void register() {
        SharedPreferences sharedPref = requireContext().getSharedPreferences("SAVED_PREFS", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(FirstFragment.USER_ID, -1).apply();
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_userRegister);
    }

    /**
     * Used to login. Navigates to the search fragment. Stores the user id
     * so that it can be accessed in the next fragment.
     * @param userId The id of the user that is logging in.
     */
    private void login(int userId) {
        // Pass user id to next fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_ID, userId);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}