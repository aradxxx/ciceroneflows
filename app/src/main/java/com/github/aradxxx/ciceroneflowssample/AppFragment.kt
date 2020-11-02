package com.github.aradxxx.ciceroneflowssample

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.github.aradxxx.ciceroneflow.FlowRouter
import com.github.aradxxx.ciceroneflow.NavigationContainer
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab2Screen
import com.github.aradxxx.ciceroneflowssample.tabfragment.Tab3Screen

/**
 * Пример фрагмента экрана.
 */
class AppFragment : Fragment() {
    private lateinit var router: FlowRouter
    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            router.exit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment, container, false)
        val label = view.findViewById<TextView>(R.id.label)
        val switchButton = view.findViewById<Button>(R.id.switch_to_2)
        val switchAndAnotherOneButton = view.findViewById<Button>(R.id.switch_to_3)
        val addWithoutSwitchButton = view.findViewById<Button>(R.id.add_to_3)
        val nextButton = view.findViewById<Button>(R.id.open_next)
        val nextGlobalButton = view.findViewById<Button>(R.id.open_next_global)
        val replaceGlobalButton = view.findViewById<Button>(R.id.replace_global)
        if (arguments != null) {
            // отображает на экране с какого таба был открыт фрагмент
            // и текущий размер стека
            val text = ("OPENED FROM: <b>" + arguments!!.getString(BUNDLE_KEY_LABEL)
                + "</b><br/><br/>back stack size: <b>" + backStackSize() + "</b>")
            label.text = Html.fromHtml(text)
        }

        // кнопка переключения на 2ой таб
        switchButton.setOnClickListener {
            router.switch(Tab2Screen())
        }

        // кнопка переключения на 3ий таб и открытие еще одного фрагмента
        switchAndAnotherOneButton.setOnClickListener {
            router.switch(Tab3Screen())
            router.navigateTo(Tab.Tab3.tag(), AppFragmentScreen(tabTag()))
        }

        // Открытие экрана на 3ем табе, без переключения таба
        addWithoutSwitchButton.setOnClickListener {
            router.navigateTo(Tab.Tab3.tag(), AppFragmentScreen(tabTag()))
        }

        // кнопка открытия еще одного фрагмента на текущем табе
        nextButton.setOnClickListener {
            router.navigateTo(AppFragmentScreen(tabTag()))
        }

        // кнопка открытия еще одного фрагмента поверх табов
        nextGlobalButton.setOnClickListener {
            router.navigateTo("", AppFragmentScreen("ACTIVITY"))
        }

        // кнопка замены глобал экрана
        replaceGlobalButton.setOnClickListener {
            router.replaceScreen("", AppFragmentScreen("REPLACED"))
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (parentFragment is NavigationContainer<*>) {
            router = (parentFragment as NavigationContainer<*>).router()
        } else if (requireActivity() is NavigationContainer<*>) {
            router = (requireActivity() as NavigationContainer<*>).router()
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDestroyView() {
        onBackPressedCallback.remove()
        super.onDestroyView()
    }

    private fun tabTag(): String {
        val fragment = parentFragment
        return fragment?.tag ?: ""
    }

    private fun backStackSize(): String {
        return if (parentFragment == null) {
            requireActivity().supportFragmentManager.backStackEntryCount.toString()
        } else parentFragment!!.childFragmentManager.backStackEntryCount.toString()
    }

    companion object {
        private const val BUNDLE_KEY_LABEL = "BUNDLE_KEY_LABEL"

        @JvmStatic
        fun newInstance(label: String): AppFragment {
            val bundle = Bundle()
            bundle.putString(BUNDLE_KEY_LABEL, label)
            val fragment = AppFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}