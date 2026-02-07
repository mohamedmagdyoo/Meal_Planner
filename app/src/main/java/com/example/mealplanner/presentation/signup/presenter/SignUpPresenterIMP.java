package com.example.mealplanner.presentation.signup.presenter;

import com.example.mealplanner.presentation.signup.view.SignUpView;
import com.example.mealplanner.presentation.signup.view.SignupScreen;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenterIMP implements SignUpPresenter{

    private FirebaseAuth firebaseAuth;
    private SignUpView view;

    public SignUpPresenterIMP(SignupScreen signupScreen){
        firebaseAuth = FirebaseAuth.getInstance();
        view = signupScreen;
    }

    @Override
    public void greatNewAccount(String username, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task ->{
            if (task.isSuccessful()){
                view.onCreateNewAccountSuccess(firebaseAuth.getCurrentUser());
            }else{
                view.onCreateNewAccountFailed();
            }
        });
    }
}
