package com.kochipek.noteapp.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.kochipek.noteapp.R;
import com.kochipek.noteapp.databinding.ActivityMainBinding;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding;
    private int tutorialStep = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCenter.start(getApplication(), "{\"afe0f8bd-eb57-4832-917f-b5891c9f5315\"}",
                Analytics.class, Crashes.class);
    }

    private void showTapTarget(int viewId, String title, String description, final int nextStep, boolean cancelable) {
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(viewId), title, description)
                        .outerCircleColor(R.color.blue)
                        .outerCircleAlpha(0.97f)
                        .targetCircleColor(R.color.grey)
                        .titleTextSize(30)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(20)
                        .textColor(R.color.white)
                        .dimColor(R.color.black)
                        .drawShadow(true)
                        .cancelable(cancelable)
                        .tintTarget(true)
                        .transparentTarget(true)
                        .targetRadius(30),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        view.dismiss(true);
                        if (nextStep > 0) {
                            tutorialStep = nextStep;
                            startShowcaseWalkthrough();
                        }
                    }
                });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void startShowcaseWalkthrough() {
        switch (tutorialStep) {
            case 1:
                showTapTarget(R.id.floatingActionButton, "Add note", "You can simply add a note by clicking here", 2, false);
                break;
            case 2:
                showTapTarget(R.id.filterButton, "Filter", "You can filter notes here", 3, false);
                break;
            case 3:
                showTapTarget(R.id.buttonSearch, "Search", "You can search notes here", 0, true);
                break;
        }
    }
}
