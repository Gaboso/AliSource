package br.com.alisource.actions;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

public class SnackBarActions {

    private SnackBarActions() {
    }

    /**
     * Metodo para exibir uma SnackBar de sucesso
     *
     * @param view   - View qual deve ser exibida a SnackBar
     * @param textID - Id do texto a ser exibido
     */
    public static void showSuccess(View view, @StringRes int textID) {
        show(view, textID, Color.GREEN, Snackbar.LENGTH_LONG);
    }

    /**
     * Metodo para exibir uma SnackBar de sucesso
     *
     * @param view - View qual deve ser exibida a SnackBar
     * @param text - Texto a ser exibido
     */
    public static void showSuccess(View view, @NonNull String text) {
        show(view, text, Color.GREEN, Snackbar.LENGTH_LONG);
    }

    /**
     * Metodo para exibir uma SnackBar de warning
     *
     * @param view   - View qual deve ser exibida a SnackBar
     * @param textID - Id do texto a ser exibido
     */
    public static void showWarning(View view, @StringRes int textID) {
        show(view, textID, Color.YELLOW, Snackbar.LENGTH_LONG);
    }

    /**
     * Metodo para exibir uma SnackBar de warning
     *
     * @param view - View qual deve ser exibida a SnackBar
     * @param text - Texto a ser exibido
     */
    public static void showWarning(View view, @NonNull String text) {
        show(view, text, Color.YELLOW, Snackbar.LENGTH_LONG);
    }

    /**
     * Metodo para exibir uma SnackBar de erro
     *
     * @param view   - View qual deve ser exibida a SnackBar
     * @param textID - Id do texto a ser exibido
     */
    public static void showError(View view, @StringRes int textID) {
        show(view, textID, Color.RED, Snackbar.LENGTH_LONG);
    }

    /**
     * Metodo para exibir uma SnackBar de erro
     *
     * @param view - View qual deve ser exibida a SnackBar
     * @param text - Texto a ser exibido
     */
    public static void showError(View view, @NonNull String text) {
        show(view, text, Color.RED, Snackbar.LENGTH_LONG);
    }

    /**
     * Metodo para exibir uma SnackBar
     *
     * @param view-   View qual deve ser exibida a SnackBar
     * @param textID- Id do texto a ser exibido
     */
    public static void show(View view, @StringRes int textID, int duration) {
        Snackbar.make(view, textID, duration).show();
    }

    /**
     * Metodo para exibir uma SnackBar
     *
     * @param view- View qual deve ser exibida a SnackBar
     * @param text- Texto a ser exibido
     */
    public static void show(View view, @NonNull String text, int duration) {
        Snackbar.make(view, text, duration).show();
    }

    /**
     * Metodo para exibir uma SnackBar
     *
     * @param view    - View qual deve ser exibida a SnackBar
     * @param textID  - Id do texto a ser exibido
     * @param colorID - Id da cor que o texto sera exibido
     */
    public static void show(View view, @StringRes int textID, int colorID, int duration) {
        show(view, view.getResources().getString(textID), colorID, duration);
    }

    /**
     * Metodo para exibir uma SnackBar
     *
     * @param view    - View qual deve ser exibida a SnackBar
     * @param text    - Texto a ser exibido
     * @param colorID - Id da cor que o texto sera exibido
     */
    public static void show(View view, @NonNull String text, int colorID, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);

        View snackbarView = snackbar.getView();
        TextView textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(colorID);

        snackbar.show();
    }

}