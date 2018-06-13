[ ![Download](https://api.bintray.com/packages/gaboso/com.github.gaboso/alisource/images/download.svg) ](https://bintray.com/gaboso/com.github.gaboso/alisource/_latestVersion)

# AliSource


### About

An Android library that contains:

- BaseActivity:
    - Component visibility control.
    - Transition visual effects.
    - Clear field focus.
    - Check network connectivity.
    - Dialog to confirm application exit.
    - Get data from fields (Integer, Double, Date, Long, String and boolean).
    - Basic field validation.

- SnackBarActions:
    - Basic Success Snackbar.
    - Basic Warning Snackbar.
    - Basic Error Snackbar.
    - Basic Snackbar.
    
- Mask:
    - Mask helper.
    
### How to add in your own project

Add the following dependency in the file 'build.gradle':

```gradle
implementation 'com.github.gaboso:alisource:x.x.x'
```

### How to use

#### BaseActivity

Your activity should extend BaseActivity:

```java
import br.com.alisource.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    ...
}
```

Get data from field:

```java
//How to get a String
String text = getTextFromField(R.id.field_text_id);

//How to get a Long
Long id = getLongFromField(R.id.field_long_id);

//How to get a Double
Double bodyMass = getDoubleFromField(R.id.field_double_id);

// How to get an Integer
Integer age = getIntegerFromField(R.id.field_int_id);

// How to get a Date
Date date = getDateFromField(R.id.field_date_id);

// How to get a Boolean
Boolean createBackup = getBooleanFromRadio(R.id.radio_id);
```

Set mask in field:

```java
//Using string mask
setMask(R.id.phone_field, "(###) ###-####");
// OR
//Using mask id
setMask(R.id.phone_field, R.string.phone_mask);
```

Set color in text:

```java
setTextColor(R.id.component, R.color.yellow_dark);
```

Remove focus from field:

```java
clearFocus(R.id.field_1);

//OR

int[] fieldIds = {R.id.field_1, R.id.field_2, R.id.field3};
clearFocus(fieldIds);
```

Hide keyboard

```java
hideKeyboard();
```

Component visibility control

```java
// Gone
setVisibilityGone(R.id.component);

// Visible
setVisibilityVisible(R.id.component);

//Invisible
setVisibilityInvisible(R.id.component);

```

Load an advertising
```java
loadAd(R.id.ad_view_id);
```