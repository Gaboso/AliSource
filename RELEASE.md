# Changelog
All notable changes to this project will be documented in this file.

## [3.0.0] - 2025-05-18

### Changed
- Android API version from 29 to 35.
- Updated buildToolsVersion from '29.0.2' to '35.0.1'
- Gradle (com.android.tools.build:gradle) version from '3.4.2' to '8.10.0';
- Compile options from Java '1.8' to '17'
- Changed repositories from 'jcenter' and 'maven' to 'google' and 'mavenCentral'
- Changed 'junit:junit:4.13-beta-3' to 'junit:junit:4.13.2'.
- Changed 'androidx.appcompat:appcompat:1.0.2' to 'androidx.appcompat:appcompat:1.7.0'.
- Changed 'com.google.android.material:material:1.0.0' to 'com.google.android.material:material:1.12.0'.
- Changed 'androidx.browser:browser:1.0.0' to 'androidx.browser:browser:1.8.0'.
- Prevent NPE on BaseActivity class
- Prevent IndexOutOfBound exception on Mask class
### Added
- Add support to 'maven-publish' plugin
### Removed
- Bintray configs and dependencies
- Removed dependency 'play-services-ads'

## [2.0.0] - 2019-08-15

### Changed
- Android API version from 28 to 29.
- Updated buildToolsVersion from '28.0.3' to '29.0.2' 
- Gradle (com.android.tools.build:gradle) version from '3.4.1' to '3.4.2';
- Updated 'play-services-ads' dependencies, from '17.2.1' to '18.1.1'.
- Changed 'com.android.support:support-v4:28.0.0' to 'androidx.legacy:legacy-support-v4:1.0.0'.
- Changed 'com.android.support:appcompat-v7:28.0.0' to 'androidx.appcompat:appcompat:1.0.2'.
- Changed 'com.android.support:design:28.0.0' to 'com.google.android.material:material:1.0.0'.
- Changed 'com.android.support:customtabs:28.0.0' to 'androidx.browser:browser:1.0.0'.
### Added
- Add AndroidX support.
- Enabled Jetfier.

## [1.4.1] - 2019-05-24

### Changed
- Gradle (com.android.tools.build:gradle) version from '3.3.0' to '3.4.1';
- Updated 'play-services-ads' dependencies, from '17.1.2' to '17.2.1'.
- Updated name of some parameters for better understanding of the code.

## [1.3.1] - 2019-01-23

### Changed
- Gradle (com.android.tools.build:gradle) version from '3.2.0-beta02' to '3.3.0';
- Updating android dependencies of project, from '28.0.0-alpha3' to '28.0.0'.
- Gradle Wrapper version from '4.8' to '5.1'.

## [1.2.2] - 2018-07-14

### Changed
- Fixed access to getCurrentLocale method;

## [1.2.1] - 2018-07-05

### Changed
- Gradle (com.android.tools.build:gradle) version from '3.1.3' to '3.2.0-beta02';
- Updating android dependencies of project, from '27.1.1' to '28.0.0-alpha3'.
- Gradle Wrapper version from '4.4' to '4.8'.
- Java version from '1.7' to '1.8'.
- TextUtils to validate empty text.

### Added
- LocaleUtils class.

## [1.1.3] - 2018-06-25

### Added
- Method to get load test ads in device or emulator.

## [1.1.2] - 2018-06-22

### Changed
- Using TextUtils to validate empty or null String.
- Methods setVisibility suport to View components (not only TextView).
- Gradle (com.android.tools.build:gradle) version from '3.1.2' to '3.1.3'.

## [1.1.1] - 2018-06-09

### Changed
- Updating 'com.android.support' dependencies of project, from '27.1.0' to '27.1.1'.
- Updating 'play-services-ads' dependencies, from '12.0.0' to '15.0.1'.
- Gradle (com.android.tools.build:gradle) version from '3.1.0' to '3.1.2'.
### Added
- Method to get an array of IDs.

## [1.1.0] - 2018-03-27

### Changed
- Updating android dependencies of project, from '27.0.2' to '27.1.0'.
- Gradle (com.android.tools.build:gradle) version from '3.0.1' to '3.1.0'.
- Gradle Wrapper version from '4.1' to '4.4'.
### Added
- Method to set text in a field that is in a different view.
- Method to get a date value from field.

## [1.0.3] - 2018-02-26

### Added
- Loader for advertisements.

## [1.0.2] - 2018-02-21

### Added
- Style for email field.

## [1.0.1] - 2018-02-19

### Changed
- Moved 'br.com.alisource' package classes to 'com.github.gaboso' package.

## [0.1.5] - 2018-02-03

### Changed
- Updating android dependencies of project, from '27.0.1' to '27.0.2'.

## [0.1.4] - 2017-11-29

### Added
- Project changelog.
- Project license.
### Changed
- Updating android dependencies of project, from '27.0.0' to '27.0.1'.
- Replaced Javadocs who were in Portuguese to English.
- Gradle (com.android.tools.build:gradle) version from '3.0.0' to '3.0.1'.

## [0.1.3] - 2017-11-09

### Added
- SnackBarActions.
### Changed
- Refactoring and code cleanup in Mask.
- Gradle (com.android.tools.build:gradle) version from '2.3.3' to '3.0.0'.

## [0.1.2] - 2017-11-09

### Added
- Permission 'ACCESS_NETWORK_STATE'.
- Adding annotations to validate the parameters of BaseActivity methods.
### Changed
- Android API version from 26 to 27.
- Updating android dependencies of project, from '26.0.0-alpha1' to '27.0.0'.
### Removed
- Unnecessary casts in BaseActivity.

## [0.1.1] - 2017-09-14

### Added
- Overload in showExitDialog method to accept strings as parameter.
- Overload in setErrorMessage method to accept string as parameter.
- Method getCheckedRadioButtonId to get id of selected radioButton.
- Javadoc for visibility control methods.
### Changed
- RadioButtuon style was renamed from 'radio_button' to 'ali_radio_button'.
- RadioButtuon style was renamed from 'button' to 'ali_button'.
- Parent of styles 'button.piece' and 'button.whole'.
### Removed
- Some files from the .idea folder.

## [0.1.0] - 2017-09-11

### Added
- BaseActivity.
- Mask.
- Animations in and out of Activity.
- Methods to control the visibility of components.
- Basic dimensions for use in View.
- Basic style for RadioButton.
- Basic style for Button.
- Basic style for input fields.
- Basic style for TextInputLayout.

