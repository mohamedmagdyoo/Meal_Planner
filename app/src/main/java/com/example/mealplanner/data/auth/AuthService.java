package com.example.mealplanner.data.auth;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.credentials.ClearCredentialStateRequest;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.ClearCredentialException;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import io.reactivex.rxjava3.core.Single;

public class AuthService {
    private FirebaseAuth auth;
    private Context context;


    public AuthService(Context context) {
        this.context = context;
        auth = FirebaseAuth.getInstance();
    }


    public Single<FirebaseUser> login(String email, String password) {
        Single<FirebaseUser> single = Single.create(emitter -> {

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Login successful
                    emitter.onSuccess(auth.getCurrentUser());
                } else {
                    // Login failed
                    emitter.onError(task.getException());
                }
            });
        });

        return single;
    }


    public Single<FirebaseUser> register(String email, String password) {
        Single<FirebaseUser> single = Single.create(emitter -> {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    emitter.onSuccess(auth.getCurrentUser());
                } else {
                    emitter.onError(task.getException());
                }
            });

        });
        return single;
    }

    public Single<FirebaseUser> loginWithGoogle(String idToken) {
        Single<FirebaseUser> single = Single.create(emitter -> {
                    AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
                    auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            emitter.onSuccess(auth.getCurrentUser());
                        } else {
                            emitter.onError(task.getException());
                        }
                    });
                }
        );
        return single;
    }


    public void signOut() {

        CredentialManager credentialManager = CredentialManager.create(context);

        auth.signOut();

        credentialManager.clearCredentialStateAsync(
                new ClearCredentialStateRequest(),
                null,
                ContextCompat.getMainExecutor(context),
                new CredentialManagerCallback<Void, ClearCredentialException>() {
                    @Override
                    public void onResult(Void result) {
                        Log.d("Logout", "Google credential cleared");
                    }

                    @Override
                    public void onError(ClearCredentialException e) {
                        Log.e("Logout", "Error clearing Google credential", e);
                    }
                }
        );
    }
}

//    public void loginWithGoogle(String idToken, FirebaserResponse response) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        auth.signInWithCredential(credential).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                response.onSuccess(auth.getCurrentUser());
//            } else {
//                response.onFailed(task.getException());
//            }
//        });
//    }


//    public Single<FirebaseUser> register(String email, String password) {
//
//        Single<FirebaseUser> single = Single.create(emitter -> {
//            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    emitter.onSuccess(auth.getCurrentUser());
//                } else {
//                    emitter.onError(task.getException());
//                }
//            });
//
//        });
//
//        return single;
//    }
