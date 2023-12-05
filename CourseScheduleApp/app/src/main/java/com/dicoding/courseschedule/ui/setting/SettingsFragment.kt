package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import com.dicoding.courseschedule.util.NightMode

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        val darkModePreference = findPreference<ListPreference>(getString(R.string.pref_key_dark))

        darkModePreference?.setOnPreferenceChangeListener { _, newVal ->
            val themeMode = when(newVal){
                getString(R.string.pref_dark_on) -> NightMode.ON.value
                getString(R.string.pref_dark_off) -> NightMode.OFF.value
                else -> {
                    NightMode.AUTO.value
                }
            }
            updateTheme(themeMode)
            true
        }

        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val notifPreference = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))

        notifPreference?.setOnPreferenceChangeListener { _, newValue ->
            val dailyReminder = DailyReminder()
            if(newValue as Boolean){
                dailyReminder.setDailyReminder(requireContext())
            }else {
                dailyReminder.cancelAlarm(requireContext())
            }
            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}