package com.android.edraak.Fragment.Splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.edraak.Manager.LoginManager;
import com.android.edraak.R;
import com.android.edraak.databinding.FragmentSplashBinding;
import com.google.firebase.auth.FirebaseAuth;


public class SplashFragment extends Fragment {

    FragmentSplashBinding binding;
    NavController navController;
    FirebaseAuth mAuth;
    private final int SPLASH_DISPLAY_TIMER = 2000;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding = FragmentSplashBinding.bind(view);
        mAuth = FirebaseAuth.getInstance();

        checkForUser();
    }

    private void checkForUser() {
        if(mAuth.getCurrentUser() != null) {
            // -- User is logged in
            LoginManager loginManager = new LoginManager(getContext(), navController);
            new Handler().postDelayed(loginManager::Login,SPLASH_DISPLAY_TIMER);
        } else {
            // -- User is not logged in
            navigateAfterSplashDelayEnd();
        }
    }


    void navigateAfterSplashDelayEnd(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                navController.navigate(R.id.action_splashFragment_to_loginFragment);
            }
        },SPLASH_DISPLAY_TIMER);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}