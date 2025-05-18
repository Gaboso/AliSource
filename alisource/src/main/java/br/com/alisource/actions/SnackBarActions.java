package br.com.alisource.actions;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.google.android.material.snackbar.Snackbar;

/**
 * Helper class for the use of SnackBar,
 * with some already ready like:
 * <b>success</b>, <b>warning</b> and <b>error</b>.
 */
public class SnackBarActions {

    private SnackBarActions() {
    }

    /**
     * Method for displaying a successful SnackBar
     *
     * @param view   - View which Snackbar should be displayed
     * @param textID - Id of text to be displayed
     */
    public static void showSuccess(@NonNull View view, @StringRes int textID) {
        show(view, textID, Color.GREEN, Snackbar.LENGTH_LONG);
    }

    /**
     * Method for displaying a successful SnackBar
     *
     * @param view - View which Snackbar should be displayed
     * @param text - Text to be displayed
     */
    public static void showSuccess(@NonNull View view, @NonNull String text) {
        show(view, text, Color.GREEN, Snackbar.LENGTH_LONG);
    }

    /**
     * Method for displaying a warning SnackBar
     *
     * @param view   - View which Snackbar should be displayed
     * @param textID - Id of text to be displayed
     */
    public static void showWarning(@NonNull View view, @StringRes int textID) {
        show(view, textID, Color.YELLOW, Snackbar.LENGTH_LONG);
    }

    /**
     * Method for displaying a warning SnackBar
     *
     * @param view - View which Snackbar should be displayed
     * @param text - Text to be displayed
     */
    public static void showWarning(@NonNull View view, @NonNull String text) {
        show(view, text, Color.YELLOW, Snackbar.LENGTH_LONG);
    }

    /**
     * Method for displaying a error SnackBar
     *
     * @param view   - View which Snackbar should be displayed
     * @param textID - Id of text to be displayed
     */
    public static void showError(@NonNull View view, @StringRes int textID) {
        show(view, textID, Color.RED, Snackbar.LENGTH_LONG);
    }

    /**
     * Method for displaying a error SnackBar
     *
     * @param view - View which Snackbar should be displayed
     * @param text - Text to be displayed
     */
    public static void showError(@NonNull View view, @NonNull String text) {
        show(view, text, Color.RED, Snackbar.LENGTH_LONG);
    }

    /**
     * Method for displaying a SnackBar
     *
     * @param view-   View which Snackbar should be displayed
     * @param textID- Id of text to be displayed
     */
    public static void show(@NonNull View view, @StringRes int textID, int duration) {
        Snackbar.make(view, textID, duration).show();
    }

    /**
     * Method for displaying a SnackBar
     *
     * @param view- View which Snackbar should be displayed
     * @param text- Text to be displayed
     */
    public static void show(@NonNull View view, @NonNull String text, int duration) {
        Snackbar.make(view, text, duration).show();
    }

    /**
     * Method for displaying a SnackBar
     *
     * @param view    - View which Snackbar should be displayed
     * @param textID  - Id of text to be displayed
     * @param colorID - Id of color that text will be displayed
     */
    public static void show(@NonNull View view, @StringRes int textID, int colorID, int duration) {
        show(view, view.getResources().getString(textID), colorID, duration);
    }

    /**
     * Method for displaying a SnackBar
     *
     * @param view    - View which Snackbar should be displayed
     * @param text    - Text to be displayed
     * @param colorID - Id of color that text will be displayed
     */
    public static void show(@NonNull View view, @NonNull String text, int colorID, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);

        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);

        if (textView != null) {
            textView.setTextColor(colorID);
        }

        snackbar.show();
    }

}