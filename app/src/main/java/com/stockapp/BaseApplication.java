package com.stockapp;

import com.stockapp.di.app.AppComponent;
import com.stockapp.di.app.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {
    private static final String STARTUP_TRACE_NAME = "STARTUP_TRACE_NAME";
    private static final String REQUESTS_COUNTER_NAME = "REQUESTS_COUNTER_NAME";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseApp.initializeApp(getApplicationContext());
//        MyJobService.enqueueWork(getApplicationContext(), new Intent(this, MyFirebaseMessagingService.class));
//        FirebasePerformance.getInstance().setPerformanceCollectionEnabled(true);
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
//        Trace trace = FirebasePerformance.getInstance().newTrace(STARTUP_TRACE_NAME);
//        Log.d("TAG", "Starting trace");
//        trace.start();
//        trace.putAttribute("experiment", "A");
//        trace.incrementMetric(REQUESTS_COUNTER_NAME, 1);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                trace.stop();
//            }
//        }, 3000);

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder().application(this).build();
        return appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
