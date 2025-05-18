# AliSource [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


### About

AliSource is an Android utility library that provides:

- **BaseActivity**:
  - Simplified component visibility control.
  - Built-in transition animations.
  - Field focus management.
  - Network connectivity checks.
  - Exit confirmation dialogs.
  - Convenient methods to extract values from fields (`Integer`, `Double`, `Date`, `Long`, `String`, `Boolean`).
  - Basic field validations.

- **SnackBarActions**:
  - Predefined `Snackbar` styles for Success, Warning, Error, and Neutral messages.

- **Mask**:
  - Easy-to-apply input masks for fields.

---

### ⚠️ Bintray Deprecation Notice

This library was originally published via Bintray, which is no longer available. While we migrate to a new public repository, you can use the library locally through one of the following approaches:

---

### 🛠 Option 1 – Build and use via **Maven Local**

#### Step 1: Generate the release
```bash
./gradlew clean assembleRelease publishReleasePublicationToMavenLocal
````

#### Step 2: Locate the artifact

After publishing, the `.aar` will be available in your local Maven repository:

```
~/.m2/repository/com/github/gaboso/alisource/3.0.0/
```

#### Step 3: Configure your app to use `mavenLocal()`

Add this in your project's `build.gradle`:

```gradle
repositories {
    mavenLocal()
    google()
    mavenCentral()
}
```

Then, add the dependency:

```gradle
implementation 'com.github.gaboso:alisource:3.0.0'
```

---

### 📦 Option 2 – Use `.aar` directly via `libs/`

#### Step 1: Copy the `.aar` file

Navigate to:

```
~/.m2/repository/com/github/gaboso/alisource/3.0.0/
```

Copy the file `alisource-3.0.0.aar` to your app's `libs/` folder:

```
your-app-module/libs/alisource-3.0.0.aar
```

#### Step 2: Configure `build.gradle`

```gradle
repositories {
    flatDir {
        dirs 'libs'
    }
}

dependencies {
    implementation(name: 'alisource-3.0.0', ext: 'aar')
}
```

---

### 🧩 How to Use

#### `BaseActivity`

Extend `BaseActivity` in your Activity:

```java
import br.com.alisource.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    // ...
}
```

##### Get values from fields:

```java
String name = getTextFromField(R.id.name_field);
Long id = getLongFromField(R.id.id_field);
Double weight = getDoubleFromField(R.id.weight_field);
Integer age = getIntegerFromField(R.id.age_field);
Date birthDate = getDateFromField(R.id.date_field);
Boolean isActive = getBooleanFromRadio(R.id.active_radio);
```

##### Set input mask:

```java
setMask(R.id.phone_field, "(###) ###-####");
// or using a string resource
setMask(R.id.phone_field, R.string.phone_mask);
```

##### Change text color:

```java
setTextColor(R.id.label, R.color.yellow_dark);
```

##### Clear focus from fields:

```java
clearFocus(R.id.field_one);

// or multiple fields
clearFocus(new int[]{R.id.field_one, R.id.field_two});
```

##### Hide keyboard:

```java
hideKeyboard();
```

##### Control visibility:

```java
setVisibilityGone(R.id.view);
setVisibilityVisible(R.id.view);
setVisibilityInvisible(R.id.view);
```

##### Get selected `RadioButton` ID:

```java
int checkedId = getCheckedRadioButtonId(R.id.radio_group);
```

##### Set HTML-formatted text:

```java
setHTMLContent(R.id.text_view, "<p>Hello <b>World</b></p>");
// or using a string resource
setHTMLContent(R.id.text_view, R.string.html_content);
```

---

### 📋 License

This project is licensed under the [Apache License 2.0](https://opensource.org/licenses/Apache-2.0).
