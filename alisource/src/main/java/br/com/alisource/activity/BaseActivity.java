package br.com.alisource.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import br.com.alisource.R;
import br.com.alisource.mask.Mask;

/**
 * Class helper to get or set items from View and
 * also control other actions related to View
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Method to open an activity using a default intent
     *
     * @param view        - Current View
     * @param targetClass - Target class
     */
    protected void openActivity(@NonNull View view, @NonNull Class<?> targetClass) {
        Context context = view.getContext();
        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, targetClass);
        openActivity(intent);
    }

    /**
     * Method to open an activity using a custom intent
     *
     * @param intent - Custom Intent
     */
    protected void openActivity(Intent intent) {
        startActivity(intent);
        enterActivityTransition();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to get text from a field
     *
     * @param fieldID - Field id
     * @return selected field text
     */
    protected String getTextFromField(@IdRes int fieldID) {
        EditText editText = findViewById(fieldID);

        if (editText == null) {
            return "";
        }

        CharSequence text = editText.getText();
        return text != null ? text.toString().trim() : "";
    }

    /**
     * Method to get an Long value
     *
     * @param fieldID - Field id
     * @return value of field converted to Long
     */
    protected Long getLongFromField(@IdRes int fieldID) {
        String text = getTextFromField(fieldID);

        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return Long.valueOf(text);

    }

    /**
     * Method to get an Double value
     *
     * @param fieldID - Field id
     * @return value of field converted to Double
     */
    protected Double getDoubleFromField(@IdRes int fieldID) {
        String text = getTextFromField(fieldID);

        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return Double.valueOf(text);

    }

    /**
     * Method to get an Integer value
     *
     * @param fieldID - Field id
     * @return value of field converted to Integer
     */
    protected Integer getIntegerFromField(@IdRes int fieldID) {
        String text = getTextFromField(fieldID);

        if (TextUtils.isEmpty(text)) {
            return null;
        }
        return Integer.valueOf(text);

    }

    /**
     * Method to get a Date value using a default SimpleDateFormat: dd/MM/yyyy
     *
     * @param fieldID - Field id
     * @return value of field converted to Date
     */
    protected Date getDateFromField(@IdRes int fieldID) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return getDateFromField(fieldID, formatter);
    }

    /**
     * Method to get a Date value
     *
     * @param fieldID   - Field id
     * @param formatter - formatter for the field date
     * @return value of field converted to Date
     */
    protected Date getDateFromField(@IdRes int fieldID, SimpleDateFormat formatter) {
        String text = getTextFromField(fieldID);

        if (!TextUtils.isEmpty(text)) {
            try {
                return formatter.parse(text);
            } catch (ParseException e) {
                Log.e(BaseActivity.class.getSimpleName(), "Error while parsing content from field: " + fieldID + " to date", e);
            }
        }

        return null;
    }

    /**
     * Method to get selection of a RadioButton
     *
     * @param radioID - RadioButton id
     * @return true if RadioButton is selected and false otherwise
     */
    protected Boolean getBooleanFromRadio(@IdRes int radioID) {
        RadioButton radioButton = findViewById(radioID);

        if (radioButton == null) {
            return null;
        }

        return radioButton.isChecked();
    }

    /**
     * Method to insert a mask in field
     *
     * @param fieldID - Id of field to receive mask
     * @param maskID  - Mask id
     */
    protected void setMask(@IdRes int fieldID, @StringRes int maskID) {
        setMask(fieldID, getString(maskID));
    }

    /**
     * Method to insert a mask in field
     *
     * @param fieldID - Id of field to receive mask
     * @param mask    - Mask
     */
    protected void setMask(@IdRes int fieldID, String mask) {
        EditText field = findViewById(fieldID);

        if (field != null) {
            field.addTextChangedListener(Mask.insert(mask, field));
        }
    }

    /**
     * Method to display confirmation Dialog to exit app
     *
     * @param titleID        - Dialog title id
     * @param messageID      - Dialog message id
     * @param positiveTextID - Id of positive button text
     * @param negativeTextID - Id of negative button text
     */
    protected void showExitDialog(@StringRes int titleID, @StringRes int messageID,
                                  @StringRes int positiveTextID, @StringRes int negativeTextID) {
        String title = getString(titleID);
        String message = getString(messageID);
        String positiveText = getString(positiveTextID);
        String negativeText = getString(negativeTextID);

        showExitDialog(title, message, positiveText, negativeText);
    }

    /**
     * Method to display confirmation Dialog to exit app
     *
     * @param title        - Dialog title
     * @param message      - Dialog message
     * @param positiveText - Positive button text
     * @param negativeText - Negative button text
     */
    protected void showExitDialog(String title, String message, String positiveText, String negativeText) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, (dialog, arg1) -> finish());
        builder.setNegativeButton(negativeText, (dialog, arg1) -> dialog.cancel());

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Method for setting error in a field
     *
     * @param fieldID        - Id of field to be evidenced
     * @param errorMessageID - Id of error message to be displayed in field
     */
    protected void setErrorMessage(@IdRes int fieldID, @StringRes int errorMessageID) {
        setErrorMessage(fieldID, getString(errorMessageID));
    }

    /**
     * Method for setting error in a field
     *
     * @param fieldID      - Id of field to be evidenced
     * @param errorMessage - Error message to be displayed in field
     */
    protected void setErrorMessage(@IdRes int fieldID, String errorMessage) {
        TextInputLayout field = findViewById(fieldID);

        if (field != null) {
            field.setErrorEnabled(true);
            field.setError(errorMessage);
        }
    }

    /**
     * Method to validate if field is not null or empty
     *
     * @param fieldID        - Id of field to be verified
     * @param layoutID       - Id of layout to be evidenced in case of error
     * @param errorMessageID - Error Message Id
     * @return true if not empty or null, false if null or empty
     */
    protected boolean validateFieldNotNullOrNotEmpty(@IdRes int fieldID, @IdRes int layoutID, @StringRes int errorMessageID) {
        String text = getTextFromField(fieldID);

        if (TextUtils.isEmpty(text)) {
            setErrorMessage(layoutID, errorMessageID);
            return false;
        }

        clearError(layoutID);
        return true;
    }

    /**
     * Method to remove error signal from a field
     *
     * @param layoutID - TextInputLayout Id where error should be removed
     */
    protected void clearError(@IdRes int layoutID) {
        TextInputLayout field = findViewById(layoutID);
        field.setErrorEnabled(false);
    }

    /**
     * Method to get type of a field
     *
     * @param fieldID - Field id
     * @return Field type Id
     */
    protected int getFieldType(@IdRes int fieldID) {
        EditText editText = findViewById(fieldID);
        return editText.getInputType();
    }

    /**
     * Method to set text
     *
     * @param componentID - Id of component that will receive text
     * @param contentID   - Id of textual content
     */
    protected void setText(@IdRes int componentID, @StringRes int contentID) {
        setText(componentID, getString(contentID));
    }

    /**
     * Method to set text
     *
     * @param componentID - Id of component that will receive text
     * @param content     - Textual content
     */
    protected void setText(@IdRes int componentID, String content) {
        TextView textView = findViewById(componentID);

        if (textView != null) {
            textView.setText(content);
        }
    }

    /**
     * Method to set text
     *
     * @param componentID - Id of component that will receive text
     * @param contentID   - Id of textual content
     * @param view        - View in which component that will receive textual content is present
     */
    protected void setText(@IdRes int componentID, @StringRes int contentID, View view) {
        setText(componentID, getString(contentID), view);
    }

    /**
     * Method to set text
     *
     * @param componentID - Id of component that will receive text
     * @param content     - Textual content
     * @param view        - View in which component that will receive textual content is present
     */
    protected void setText(@IdRes int componentID, String content, View view) {
        TextView textView = view.findViewById(componentID);

        if (textView != null) {
            textView.setText(content);
        }
    }

    /**
     * Method to set color in a text
     *
     * @param fieldID - Id field that contains the text which will receive the color
     * @param colorID - Color id to be set in text
     */
    protected void setTextColor(@IdRes int fieldID, @ColorRes int colorID) {
        TextView textView = findViewById(fieldID);

        if (textView != null) {
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), colorID));
        }
    }

    /**
     * Method to select a RadioButton
     *
     * @param radioID - id RadioButton to be selected
     */
    protected void checkRadio(@IdRes int radioID) {
        RadioButton radioButton = findViewById(radioID);

        if (radioButton == null) {
            return;
        }
        radioButton.setChecked(true);
    }

    /**
     * Method to remove a RadioGroup selection
     *
     * @param groupID - id of RadioGroup to have selections removed
     */
    protected void clearRadioGroup(@IdRes int groupID) {
        RadioGroup radioGroup = findViewById(groupID);

        if (radioGroup == null) {
            return;
        }
        radioGroup.clearCheck();
    }

    /**
     * Method to get id of selected RadioButton in a RadioGroup
     *
     * @param groupID - RadioGroup id
     * @return selected RadioButton id
     */
    protected int getCheckedRadioButtonId(@IdRes int groupID) {
        RadioGroup radioGroup = findViewById(groupID);
        return radioGroup.getCheckedRadioButtonId();
    }

    /**
     * Checks if the device currently has an active network connection.
     *
     * @return true if connected to a network and has internet access, false otherwise.
     */
    protected boolean networkConnectivity() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }
        Network network = manager.getActiveNetwork();
        if (network == null) {
            return false;
        }

        NetworkCapabilities capabilities = manager.getNetworkCapabilities(network);
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                        || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN));

    }

    /**
     * Method to remove focus from multiple fields
     *
     * @param fieldIDs - Array with field ids to have the focus removed
     */
    protected void clearFocus(int[] fieldIDs) {
        for (int fieldID : fieldIDs) {
            clearFocus(fieldID);
        }
    }

    /**
     * Method to remove focus from a field
     *
     * @param fieldID - Field id to have focus removed
     */
    protected void clearFocus(@IdRes int fieldID) {
        EditText editText = findViewById(fieldID);

        if (editText == null) {
            return;
        }
        editText.clearFocus();
    }

    /**
     * Method to hide keyboard
     */
    protected void hideKeyboard() {
        View view = getCurrentFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }

        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            View rootView = findViewById(android.R.id.content);
            if (rootView == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(rootView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }

    /**
     * Method for setting textual content formatted with HTML tags
     *
     * @param fieldID     - Id of component to receive formatted text
     * @param bodyContent - HTML body with formatting
     */
    protected void setHTMLContent(@IdRes int fieldID, String bodyContent) {
        String htmlFormatted = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
                "<body>" + bodyContent + "</body></html>";

        TextView textView = findViewById(fieldID);
        if (textView == null) {
            return;
        }

        Spanned text = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ?
                Html.fromHtml(htmlFormatted, Html.FROM_HTML_MODE_LEGACY) :
                Html.fromHtml(htmlFormatted);
        textView.setText(text);
    }

    /**
     * Method for setting textual content formatted with HTML tags
     *
     * @param fieldID       - Id of component to receive formatted text
     * @param bodyContentID - HTML body id with formatting
     */
    protected void setHTMLContent(@IdRes int fieldID, @StringRes int bodyContentID) {
        setHTMLContent(fieldID, getString(bodyContentID));
    }

    /**
     * Method that creates enter transition
     */
    protected void enterActivityTransition() {
        overridePendingTransition(R.anim.ali_slide_from_right, R.anim.ali_slide_to_left);
    }

    /**
     * Method that creates exit transition
     */
    protected void exitActivityTransition() {
        overridePendingTransition(R.anim.ali_slide_from_left, R.anim.ali_slide_to_right);
    }

    /**
     * Method to set field visibility to GONE
     *
     * @param id - Component Id to have visibility changed
     */
    protected void setVisibilityGone(@IdRes int id) {
        View view = findViewById(id);
        view.setVisibility(View.GONE);
    }

    /**
     * Method to set field visibility to VISIBLE
     *
     * @param id - Component Id to have visibility changed
     */
    protected void setVisibilityVisible(@IdRes int id) {
        View view = findViewById(id);
        if (view == null) {
            return;
        }
        view.setVisibility(View.VISIBLE);
    }

    /**
     * Method to set field visibility to INVISIBLE
     *
     * @param id - Component Id to have visibility changed
     */
    protected void setVisibilityInvisible(@IdRes int id) {
        View view = findViewById(id);
        if (view == null) {
            return;
        }
        view.setVisibility(View.INVISIBLE);
    }

    /**
     * Method to get an array of IDs
     *
     * @param arrayID - Array ID
     * @return array of IDs
     */
    protected int[] getIDArray(@ArrayRes int arrayID) {
        TypedArray typedArray = null;
        try {
            typedArray = getResources().obtainTypedArray(arrayID);
            int[] ids = new int[typedArray.length()];

            for (int i = 0; i < typedArray.length(); i++) {
                ids[i] = typedArray.getResourceId(i, 0);
            }

            return ids;
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

}