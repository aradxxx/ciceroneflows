package ru.aradxxx.ciceronetabssample;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

/**
 * Пример экрана.
 */
public class AppFragmentScreen extends SupportAppScreen {
    @NonNull
    private final String label;

    public AppFragmentScreen(@NonNull String label) {
        this.label = label;
    }

    @Override
    public Fragment getFragment() {
        return AppFragment.newInstance(label);
    }
}
