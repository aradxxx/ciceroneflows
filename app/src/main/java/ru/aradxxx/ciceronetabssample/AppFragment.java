package ru.aradxxx.ciceronetabssample;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ru.aradxxx.ciceronetabs.NavigationContainer;
import ru.aradxxx.ciceronetabs.TabRouter;
import ru.aradxxx.ciceronetabssample.tabfragment.Tab2Screen;
import ru.aradxxx.ciceronetabssample.tabfragment.Tab3Screen;

/**
 * Пример фрагмента экрана.
 */
public final class AppFragment extends Fragment {
    @NonNull
    private static final String BUNDLE_KEY_LABEL = "BUNDLE_KEY_LABEL";

    @NonNull
    private TabRouter router;

    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            router.exit();
        }
    };

    public static AppFragment newInstance(@NonNull String label) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_LABEL, label);
        AppFragment fragment = new AppFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        TextView label = view.findViewById(R.id.label);
        Button switchButton = view.findViewById(R.id.switch_to_2);
        Button switchAndAnotherOneButton = view.findViewById(R.id.switch_to_3);
        Button addWithoutSwitchButton = view.findViewById(R.id.add_to_3);
        Button nextButton = view.findViewById(R.id.open_next);
        Button nextGlobalButton = view.findViewById(R.id.open_next_global);
        Button replaceGlobalButton = view.findViewById(R.id.replace_global);

        if (getArguments() != null) {
            // отображает на экране с какого таба был открыт фрагмент
            // и текущий размер стека
            String text = "OPENED FROM: <b>" + getArguments().getString(BUNDLE_KEY_LABEL)
                    + "</b><br/><br/>back stack size: <b>" + backStackSize() + "</b>";
            label.setText(Html.fromHtml(text));
        }

        // кнопка переключения на 2ой таб
        switchButton.setOnClickListener(v -> router.switchTab(new Tab2Screen()));

        // кнопка переключения на 3ий таб и открытие еще одного фрагмента
        switchAndAnotherOneButton.setOnClickListener(v -> {
            router.switchTab(new Tab3Screen());
            router.navigateTo(Tab.Tab3.tag(), new AppFragmentScreen(tabTag()));
        });

        // Открытие экрана на 3ем табе, без переключения таба
        addWithoutSwitchButton.setOnClickListener(
                v -> router.navigateTo(Tab.Tab3.tag(), new AppFragmentScreen(tabTag())));

        // кнопка открытия еще одного фрагмента на текущем табе
        nextButton.setOnClickListener(v -> router.navigateTo(new AppFragmentScreen(tabTag())));

        // кнопка открытия еще одного фрагмента поверх табов
        nextGlobalButton.setOnClickListener(v -> router.navigateTo("", new AppFragmentScreen("ACTIVITY")));

        // кнопка замены глобал экрана
        replaceGlobalButton.setOnClickListener(v -> router.replaceScreen("", new AppFragmentScreen("REPLACED")));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // на боевом приложении т.о. роутер будет предоставляться во viewmodel/presenter
        if (getParentFragment() instanceof NavigationContainer) {
            router = ((NavigationContainer) getParentFragment()).router();
        } else if (requireActivity() instanceof NavigationContainer) {
            router = ((NavigationContainer) requireActivity()).router();
        }

        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    }

    @Override
    public void onDestroyView() {
        onBackPressedCallback.remove();
        super.onDestroyView();
    }

    @NonNull
    private String tabTag() {
        Fragment fragment = getParentFragment();
        if (fragment == null || fragment.getTag() == null) {
            return "";
        }
        return fragment.getTag();
    }

    @NonNull
    private String backStackSize() {
        if (getParentFragment() == null) {
            return String.valueOf(requireActivity().getSupportFragmentManager().getBackStackEntryCount());
        }
        return String.valueOf(getParentFragment().getChildFragmentManager().getBackStackEntryCount());
    }
}
