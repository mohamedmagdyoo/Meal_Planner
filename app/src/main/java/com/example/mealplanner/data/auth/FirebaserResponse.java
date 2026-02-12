package com.example.mealplanner.data.auth;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaserResponse {
    void onSuccess(FirebaseUser user);
    void onFailed(Exception error);
}

