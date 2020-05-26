package com.example.b10709054_hw2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    ListPreference preference;
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.pref_general);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preference = (ListPreference) findPreference(getString(R.string.pref_color_key));
        pref.registerOnSharedPreferenceChangeListener(this);
        preference.setSummary(preference.getEntry());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Figure out which preference was changed
        preference.setSummary(preference.getEntry());
    }
}
