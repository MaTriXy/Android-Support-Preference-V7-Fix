package com.takisoft.preferencefix;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;

import com.takisoft.fix.support.v7.preference.EditTextPreference;
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

        testDynamicPrefs();

        EditTextPreference etPref = (EditTextPreference) findPreference("edit_text_test");
        if (etPref != null) {
            int inputType = etPref.getEditText().getInputType();
        }

        Preference prefEmptyCheck = findPreference("pref_empty_check");

        if (prefEmptyCheck != null) {
            prefEmptyCheck.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (!(Boolean) newValue) {
                        findPreference("pref_empty_categ").setTitle(null);
                    } else {
                        findPreference("pref_empty_categ").setTitle("Now you see me");
                    }

                    return true;
                }
            });
        }
    }

    private void testDynamicPrefs() {
        final Context ctx = getPreferenceManager().getContext(); // this is the material styled context

        final PreferenceCategory dynamicCategory = (PreferenceCategory) findPreference("pref_categ");

        Preference prefAdd = findPreference("pref_add");
        if (prefAdd != null) {
            prefAdd.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                private int n = 0;

                @Override
                public boolean onPreferenceClick(Preference preference) {
                    Preference newPreference = new Preference(ctx);

                    newPreference.setTitle("New preference " + n++);
                    newPreference.setSummary(Long.toString(System.currentTimeMillis()));

                    if (dynamicCategory != null) {
                        dynamicCategory.addPreference(newPreference);
                    }
                    return true;
                }
            });
        }
    }
}
