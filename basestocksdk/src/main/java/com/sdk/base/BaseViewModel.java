package com.sdk.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseViewModel extends AndroidViewModel {
    Application application;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }
}
