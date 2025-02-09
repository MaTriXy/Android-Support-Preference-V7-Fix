# Android Support library - preference v7 bugfix

> **BREAKING CHANGE** in 26.1.0.3: The custom attribute names for extra preference types use `pref_` prefix in order to avoid name collision with other libraries. See the changelog for more details.

This library is meant to fix some of the problems found in the official support preference-v7 library. Also, there are [new preference types](#extra-types) available, such as `RingtonePreference`, `DatePickerPreference`, and `TimePickerPreference`.

Support Library (28.0.0):
[ ![Download](https://api.bintray.com/packages/gericop/maven/com.takisoft.fix%3Apreference-v7/images/download.svg) ](https://bintray.com/gericop/maven/com.takisoft.fix%3Apreference-v7/_latestVersion)

AndroidX (1.0.0-beta01):
[ ![Download](https://api.bintray.com/packages/takisoft/android/com.takisoft.preferencex%3Apreferencex/images/download.svg) ](https://bintray.com/takisoft/android/com.takisoft.preferencex%3Apreferencex/_latestVersion)
> Check out the other available AndroidX artifacts at https://bintray.com/takisoft/android)

### Donation

If you would like to support me, you may donate some small amount via PayPal.

[ ![Buy me a coffee](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/donate.png)](https://www.paypal.me/korossyg/0eur)

---

## How to use the library?
### 1. Add gradle dependency
First, **remove** the unnecessary lines of preference-v7 and preference-v14 from your gradle file as the bugfix contains both of them:
```gradle
implementation 'com.android.support:preference-v7:28.0.0'
implementation 'com.android.support:preference-v14:28.0.0'
```
And **add** this single line to your gradle file:
```gradle
implementation 'com.takisoft.fix:preference-v7:28.0.0.0'
```
> Notice the versioning: the first three numbers are *always* the same as the latest official library while the last number is for own updates. I try to keep it up-to-date but if, for whatever reasons, I wouldn't notice the new support library versions, just issue a ticket.

### 2. Use the appropriate class as your fragment's base
You can use either `PreferenceFragmentCompat` or `PreferenceFragmentCompatDividers`. The former is the fixed version of the original fragment while the latter is an extended one where you can set the dividers using the divider flags. The `PreferenceFragmentCompatDividers` is the recommended approach as it uses the updated Material Design guidelines to create the appropriate divider config.

#### Option 1 - `PreferenceFragmentCompat`
```java
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompat;

public class MyPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
	
	// additional setup
    }
}
```
> **Warning!** Watch out for the correct package name when importing `PreferenceFragmentCompat`, it should come from `com.takisoft.fix.support.v7.preference`.
---
#### Option 2 - `PreferenceFragmentCompatDividers`
> **Warning!** `PreferenceFragmentCompatDividers` is deprecated and will be removed from the AndroidX version of the lib. You should use `PreferenceFragmentCompat` instead.
```java
import com.takisoft.fix.support.v7.preference.PreferenceFragmentCompatDividers;

public class MyPreferenceFragment extends PreferenceFragmentCompatDividers {

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);

	// additional setup
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            return super.onCreateView(inflater, container, savedInstanceState);
        } finally {
            setDividerPreferences(DIVIDER_PADDING_CHILD | DIVIDER_CATEGORY_AFTER_LAST | DIVIDER_CATEGORY_BETWEEN);
        }
    }
}
```

### 3. Use the appropriate theme
You should set your containing `Activity`'s theme to either a variant of `@style/PreferenceFixTheme` or create your own and use it as the parent theme. `PreferenceFixTheme` is based on `Theme.AppCompat` and contains the required attribute `preferenceTheme`. The fix theme is available for all `Theme.AppCompat` variants, such as `NoActionBar`, `Light`, etc.
For example, the sample app uses `PreferenceFixTheme.Light.NoActionBar` as the parent theme:
```xml
<style name="Theme.MyTheme" parent="@style/PreferenceFixTheme.Light.NoActionBar">
    <item name="colorAccent">@color/accent</item>
    <item name="colorPrimary">@color/primary</item>
    <item name="colorPrimaryDark">@color/primary_dark</item>
    <!-- [...] -->
</style>
```

**Theme.MaterialComponents**
There's a NEW module called `preference-v7-material` that provides the new `Theme.MaterialComponents` related themes. You can add it to your project like this:
```gradle
implementation 'com.takisoft.fix:preference-v7-material:28.0.0.0'
```

> Note that you may need to use multidexing after this because it uses the support design library which is a huge codebase.

### 4. That's it!
Now you can enjoy using the support preferences API without losing all your hair.

---

## Extra types

There are additional preferences not part of the official support library, but decided to add them to some extra libraries. You can add all of them to your project using

```gradle
implementation 'com.takisoft.fix:preference-v7-extras:28.0.0.0'
```

or one or more groups:

Preference | Dependency | Preview
-|-|-
[`RingtonePreference`](https://github.com/Gericop/Android-Support-Preference-V7-Fix/wiki/Preference-types#ringtonepreference) | `compile 'com.takisoft.fix:preference-v7-ringtone:28.0.0.0'` | ![API 26](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/ringtone_api26.png)
[`DatePickerPreference`](https://github.com/Gericop/Android-Support-Preference-V7-Fix/wiki/Preference-types#datepickerpreference) | `compile 'com.takisoft.fix:preference-v7-datetimepicker:28.0.0.0'` | ![API 26](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/datepicker_api26.png)
[`TimePickerPreference`](https://github.com/Gericop/Android-Support-Preference-V7-Fix/wiki/Preference-types#timepickerpreference) | `compile 'com.takisoft.fix:preference-v7-datetimepicker:28.0.0.0'` | ![API 26](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/timepicker_api26.png)
[`ColorPickerPreference`](https://github.com/Gericop/Android-Support-Preference-V7-Fix/wiki/Preference-types#colorpickerpreference) | `compile 'com.takisoft.fix:preference-v7-colorpicker:28.0.0.0'` | ![API 26](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/colorpicker_api26_fixed.png)
[`SimpleMenuPreference`](https://github.com/Gericop/Android-Support-Preference-V7-Fix/wiki/Preference-types#simplemenupreference) | `compile 'com.takisoft.fix:preference-v7-simplemenu:28.0.0.0'` | ![API 26](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/simplemenu_api26.png)

---

## Custom solutions
### Dividers
If you use `PreferenceFragmentCompatDividers` as your base class for the preference fragment, you can use 3 new methods to customize the dividers:
- `setDivider(Drawable drawable)`: Sets a custom `Drawable` as the divider.
- `setDividerHeight(int height)`: Sets the height of the drawable; useful for XML resources.
- `setDividerPreferences(int flags)`: Sets where the dividers should appear. Check the documentation of the method for more details about the available flags.

### Hijacked `EditTextPreference`
The support implementation of `EditTextPreference` ignores many of the basic yet very important attributes as it doesn't forward them to the underlying `EditText` widget. In my opinion this is a result of some twisted thinking which would require someone to create custom dialog layouts for some simple tasks, like showing a numbers-only dialog. This is the main reason why the `EditTextPreference` gets hijacked by this lib: it replaces certain aspects of the original class in order to forward all the XML attributes set (such as `inputType`) on the `EditTextPreference` to the `EditText`, and also provides a `getEditText()` method so it can be manipulated directly.

The sample app shows an example of setting (via XML) and querying (programmatically) the input type of the `EditTextPreference`:
```xml
<EditTextPreference
    android:inputType="phone"
    android:key="edit_text_test" />
```

```java
EditTextPreference etPref = (EditTextPreference) findPreference("edit_text_test");
if (etPref != null) {
    int inputType = etPref.getEditText().getInputType();
    // do something with inputType
}
```
> **Note!** Watch out for the correct package name when importing `EditTextPreference`, it should come from `com.takisoft.fix.support.v7.preference`. If you import from the wrong package (i.e. `android.support.v7.preference`), the `getEditText()` method will not be available, however, the XML attributes will still be forwarded and processed by the `EditText`.


### PreferenceCategory's bottom margin reduce
Some people found the preference category's bottom margin too big, so now you can change it from the styles by setting the `preferenceCategory_marginBottom` value in your theme, for example:
```xml
<item name="preferenceCategory_marginBottom">0dp</item>
```

### Preference dialog's message style
The original implementation uses `?attr/textAppearanceSmall` as the message style in the popup dialog (the one you find when clicking on an `EditTextPreference` with a set `android:dialogMessage` attribute), but it seems really small and might be hard to read. In order to fix that, a new attribute has been introduced to the `PreferenceFixTheme`: `preferenceDialog_messageAppearance`. This attribute controls the appearance of the message in the dialog and is set to `@style/TextAppearance.AppCompat.Subhead` by default. If you wish to change this, you'll just have to add the following line to your theme:
```xml
<item name="preferenceDialog_messageAppearance">@style/YourTextAppearance</item>
```

---

## Version
The current stable version is **28.0.0.0**.

## Notes #
This demo / bugfix is set to work on API level 14+.

---

## Sample

_Material design - everywhere._

API 15 | API 21 | API 26
-|-|-
![API 15](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/base_api15.png) | ![API 21](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/base_api21.png) | ![API 26](https://raw.githubusercontent.com/Gericop/Android-Support-Preference-V7-Fix/master/images/base_api26.png)

---

### Changelog

**2018-09-24**

New version: 28.0.0.0 (based on v28.0.0)

- No support preferences related changes in the support library.
- Fixed issues #174 and #175

**2018-09-13**

New version: 28.0.0.0-rc02 (based on v28.0.0-rc02)

- No support preferences related changes in the support library.

**2018-09-13**

New version: 28.0.0.0-rc01 (based on v28.0.0-rc01)

- Official lib bug fixes
  - `PreferenceThemeOverlay` has been updated to the latest material theme. If no custom theme is provided, `PreferenceThemeOverlay` is used as the default theme.
  - `PreferenceThemeOverlay.v14` and `PreferenceThemeOverlay.v14.Material` themes have been deprecated in favour of `PreferenceThemeOverlay`.
  - `PreferenceGroup` visibility is now tied to its children - hiding a parent group will also prevent its children from being shown in the hierarchy. Use `Preference.isShown()` to get whether a `Preference` is actually displayed to the user in the hierarchy.
  - `Preference.onSetInitialValue(boolean, Object)` has been deprecated and replaced with `onSetInitialValue(Object)`. PreferenceDataStore now also correctly restores default values.
- added `onSetInitialValue(Object)` implementation and removed the deprecated `onSetInitialValue(boolean, Object)`

**2018-07-18**

New version: 28.0.0.0-alpha3 (based on v28.0.0-alpha3)

- No support preferences related changes in the support library.
- `PreferenceFragmentCompatDividers` is now deprecated. Use `PreferenceFragmentCompat` instead.
- Added `preference-v7-material` module which provides `Theme.MaterialComponents` related themes.
- Bug fixes #166, #169, #173

**2018-07-04**

New version: 27.1.1.2 (based on v27.1.1)

- No support preferences related changes in the support library.
- RingtonePreference bug fixes(#164, #165, #167).

**2018-05-11**

New version: 27.1.1.1 (based on v27.1.1)

- No support preferences related changes in the support library.
- Bug fixes (#153, #149, #155, #152).

**2018-04-10**

New version: 27.1.1.0 (based on v27.1.1)

- No support preferences related changes in the support library.
- Some bug fixes (see #147 for more info).

> For older changelogs, check out the [CHANGELOG](CHANGELOG.md) file.

Feel free to ask / suggest anything on this page by creating a ticket (*issues*)!

# License notes #
You can do whatever you want except where noted.
