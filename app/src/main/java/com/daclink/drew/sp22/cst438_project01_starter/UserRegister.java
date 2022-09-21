package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentFirstBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentUserRegisterBinding;

/**
 * Fragment displaying the new user creation screen
 */
public class UserRegister extends Fragment {
    private FragmentUserRegisterBinding binding;

    private EditText mUsername;
    private EditText mPassword;
    private EditText mVerPassword;

    private UserDAO mUserDAO;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentUserRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUsername = binding.userNameRegister;
        mPassword = binding.passwordRegister;
        mVerPassword = binding.passwordVerify;
        mUserDAO = UserDb.getInstance(getContext()).getPersonDAO();

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = String.valueOf(mUsername.getText());
                String newPassword = String.valueOf(mPassword.getText());
                String newVerPassword = String.valueOf(mVerPassword.getText());

                User user = mUserDAO.getUser(uName);
                if (user == null) {
                    if (newPassword.equals(newVerPassword)) {
                        mUserDAO.insertUser(new User(uName, newPassword, ""));
                        Toast.makeText(getActivity(),"User created!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(),"Passwords should be the same!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(),"User already exists!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(UserRegister.this)
                        .navigate(R.id.action_userRegister_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}