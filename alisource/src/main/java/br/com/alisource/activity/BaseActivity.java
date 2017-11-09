package br.com.alisource.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import br.com.alisource.R;
import br.com.alisource.mask.Mask;

public class BaseActivity extends AppCompatActivity {

    /**
     * Metodo para abrir uma activity utilizando intent default
     *
     * @param view              - View atual
     * @param destionationClass - Classe da proxima tela
     */
    protected void openActivity(View view, Class destionationClass) {
        Intent intent = new Intent(view.getContext(), destionationClass);
        openActivity(intent);
    }

    /**
     * Metodo para abrir uma activity utilizando intent personalizado
     *
     * @param intent - Intent personalizada
     */
    protected void openActivity(Intent intent) {
        startActivity(intent);
        enterActivityTransition();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitActivityTransition();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo para pegar o texto de um campo, passando o id dele
     *
     * @param fieldID - Id do campo, para poder pegar ele
     * @return texto que tinha dentro do campo selecionado
     */
    protected String getTextFromField(@IdRes int fieldID) {
        EditText editText = findViewById(fieldID);

        if (editText == null) {
            return null;
        }

        return editText.getText().toString().trim();
    }

    /**
     * Metodo para pegar um valor integer pelo id do campo
     *
     * @param fieldID - id do campo
     * @return O valor do campo convertido para integer
     */
    protected Long getLongFromField(@IdRes int fieldID) {
        String text = getTextFromField(fieldID);

        if (text != null) {
            return Long.parseLong(text);
        }

        return null;
    }

    /**
     * Metodo para pegar um valor double pelo id do campo
     *
     * @param fieldID - id do campo
     * @return O valor do campo convertido para double
     */
    protected Double getDoubleFromField(@IdRes int fieldID) {
        String text = getTextFromField(fieldID);

        if (text != null) {
            return Double.parseDouble(text);
        }

        return null;
    }

    /**
     * Metodo para pegar um valor integer pelo id do campo
     *
     * @param fieldID - id do campo
     * @return O valor do campo convertido para integer
     */
    protected Integer getIntegerFromField(@IdRes int fieldID) {
        String text = getTextFromField(fieldID);

        if (text != null) {
            return Integer.parseInt(text);
        }

        return null;
    }

    /**
     * Metodo para pegar a selecao ou nao selecao de um botao
     *
     * @param fieldID - Id do botao radio
     * @return true se o botao estava selecionado e false caso contrario
     */
    protected boolean getBooleanFromRadio(@IdRes int fieldID) {
        RadioButton radioButton = findViewById(fieldID);
        return radioButton != null && radioButton.isChecked();
    }

    /**
     * Metodo para inserir uma mascara no campo
     *
     * @param fieldID - Id do campo a receber a mascara
     * @param maskID  - Id da mascara escolhida para o campo
     */
    protected void setMask(@IdRes int fieldID, @StringRes int maskID) {
        setMask(fieldID, getString(maskID));
    }

    /**
     * Metodo para inserir uma mascara no campo
     *
     * @param fieldID - Id do campo a receber a mascara
     * @param mask    - Mascara escolhida para o campo
     */
    protected void setMask(@IdRes int fieldID, String mask) {
        EditText field = findViewById(fieldID);

        if (field != null) {
            field.addTextChangedListener(Mask.insert(mask, field));
        }
    }

    /**
     * Metodo para exibir dialogo de confirmacao para sair do app
     *
     * @param titleID        - Id do titulo a ser utilizada no dialog
     * @param messageID      - Id da mensagem a ser utilizada no dialog
     * @param positiveTextID - Id da mensagem para o botao positivo
     * @param negativeTextID - Id da mensagem para o botao negativo
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
     * Metodo para exibir dialogo de confirmacao para sair do app
     *
     * @param title        - Titulo a ser utilizada no dialog
     * @param message      - Mensagem a ser utilizada no dialog
     * @param positiveText - Mensagem para o botao positivo
     * @param negativeText - Mensagem para o botao negativo
     */
    protected void showExitDialog(String title, String message, String positiveText, String negativeText) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                finish();
            }
        });
        builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Metodo para set erro em algum campo
     *
     * @param fieldID        - Id do campo a ser evidenciado
     * @param errorMessageID - Id da mensagem de erro a ser exibida no campo
     */
    protected void setErrorMessage(@IdRes int fieldID, @StringRes int errorMessageID) {
        setErrorMessage(fieldID, getString(errorMessageID));
    }

    /**
     * Metodo para set erro em algum campo
     *
     * @param fieldID      - Id do campo a ser evidenciado
     * @param errorMessage - Mensagem de erro a ser exibida no campo
     */
    protected void setErrorMessage(@IdRes int fieldID, String errorMessage) {
        TextInputLayout field = findViewById(fieldID);

        if (field != null) {
            field.setErrorEnabled(true);
            field.setError(errorMessage);
        }
    }

    /**
     * Metodo para verificar se o campo não é nulo nem vazio
     *
     * @param fieldID        - Id do campo a ser validado
     * @param layoutID       - Id do layout a ser evidenciado em caso de erro
     * @param errorMessageID - Id da mensagem de erro
     * @return true se não for vazio e nem nulo, false se for nulo ou vazio
     */
    protected boolean validateFieldNotNullOrNotEmpty(@IdRes int fieldID, @IdRes int layoutID, @StringRes int errorMessageID) {
        String text = getTextFromField(fieldID);

        if (text == null || text.isEmpty()) {
            setErrorMessage(layoutID, errorMessageID);
            return false;
        }

        clearError(layoutID);
        return true;
    }

    /**
     * Metodo para tirar a marcacao de erro de um determinado campo
     *
     * @param layoutID - Id do text input layout em que o erro deve ser removido
     */
    protected void clearError(@IdRes int layoutID) {
        TextInputLayout field = findViewById(layoutID);
        field.setErrorEnabled(false);
    }

    /**
     * Metodo para pegar o tipo de um campo
     *
     * @param fieldID - Id do campo
     * @return Id do tipo do campo
     */
    protected int getFieldType(@IdRes int fieldID) {
        EditText editText = findViewById(fieldID);
        return editText.getInputType();
    }

    /**
     * Metodo que seta texto no campo
     *
     * @param fieldID   - Id do campo a ser setado
     * @param contentID - Id do conteudo textual a ser setado no campo
     */
    protected void setText(@IdRes int fieldID, @StringRes int contentID) {
        setText(fieldID, getString(contentID));
    }

    /**
     * Metodo que seta texto no campo
     *
     * @param fieldID - Id do campo a ser setado
     * @param content - Conteudo textual a ser setado no campo
     */
    protected void setText(@IdRes int fieldID, String content) {
        TextView textView = findViewById(fieldID);

        if (textView != null) {
            textView.setText(content);
        }
    }

    /**
     * Metodo que seta cor em um texto
     *
     * @param fieldID - Id do campo que contem o texto a receber a cor
     * @param colorID - Id da cor a ser setada no texto
     */
    protected void setTextColor(@IdRes int fieldID, @ColorRes int colorID) {
        TextView textView = findViewById(fieldID);

        if (textView != null) {
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), colorID));
        }
    }

    /**
     * Metodo para marcar um RadioButton
     *
     * @param radioID - id do RabioButton a ser marcado
     */
    protected void checkRadio(@IdRes int radioID) {
        RadioButton radioButton = findViewById(radioID);

        if (radioButton != null) {
            radioButton.setChecked(true);
        }
    }

    /**
     * Metodo para remover a marcacao de um RadioGroup
     *
     * @param radioGroupID - id do RabioGroup a ter as marcacoes retiradas
     */
    protected void clearRadioGroup(@IdRes int radioGroupID) {
        RadioGroup radioGroup = findViewById(radioGroupID);

        if (radioGroup != null) {
            radioGroup.clearCheck();
        }
    }

    /**
     * Metodo para pegar o id do botao selecionado no radio grupo
     *
     * @param groupID - Id do radio grupo
     * @return id do botao selecionado
     */
    protected int getCheckedRadioButtonId(@IdRes int groupID) {
        RadioGroup radioGroup = findViewById(groupID);
        return radioGroup.getCheckedRadioButtonId();
    }

    /**
     * Metodo para checar se o celeular possui conectividade com a rede
     *
     * @return retorna true para conectividade e false para a falta dela
     */
    protected boolean networkConnectivity() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /**
     * Metodo para remover o foco de varios campos
     *
     * @param fieldIDs - array com ids dos campos a terem o foco removido
     */
    protected void clearFocus(int[] fieldIDs) {
        for (int fieldID : fieldIDs) {
            clearFocus(fieldID);
        }
    }

    /**
     * Metodo para remover o foco de um campo
     *
     * @param fieldID - id do campo a ter o foco removido
     */
    protected void clearFocus(@IdRes int fieldID) {
        EditText editText = findViewById(fieldID);

        if (editText != null) {
            editText.clearFocus();
        }
    }

    /**
     * Metodo para esconder o teclado
     */
    protected void hideKeyboard() {
        View view = getCurrentFocus();

        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * Metodo para setar conteudo formatado com tags HTML
     *
     * @param fieldID     - Id do campo a receber o texto formatado
     * @param bodyContent - Body do html com as formatacoes necessarias
     */
    protected void setHTMLContent(@IdRes int fieldID, String bodyContent) {
        String htmlFormatted = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
                "<body>" + bodyContent + "</body></html>";

        TextView textView = findViewById(fieldID);

        if (textView != null) {
            textView.setText(
                    android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N ?
                            Html.fromHtml(htmlFormatted, Html.FROM_HTML_MODE_LEGACY) :
                            Html.fromHtml(htmlFormatted)
            );
        }
    }

    /**
     * Metodo para setar conteudo formatado com tags HTML
     *
     * @param fieldID       - Id do campo a receber o texto formatado
     * @param bodyContentID - Id do body do html com as formatacoes necessarias
     */
    protected void setHTMLContent(@IdRes int fieldID, @StringRes int bodyContentID) {
        setHTMLContent(fieldID, getString(bodyContentID));
    }

    /**
     * Metodo que cria transicao de entrada para uma activity
     */
    protected void enterActivityTransition() {
        overridePendingTransition(R.anim.ali_slide_from_right, R.anim.ali_slide_to_left);
    }

    /**
     * Metodo que cria transicao de saida para uma activity
     */
    protected void exitActivityTransition() {
        overridePendingTransition(R.anim.ali_slide_from_left, R.anim.ali_slide_to_right);
    }

    /**
     * Metodo para setar a visibilidade para GONE
     *
     * @param id - Id do componente a ter a visibilidade alterada
     */
    protected void setVisibilityGone(@IdRes int id) {
        TextView textView = findViewById(id);
        textView.setVisibility(View.GONE);
    }

    /**
     * Metodo para setar a visibilidade para VISIBLE
     *
     * @param id - Id do componente a ter a visibilidade alterada
     */
    protected void setVisibilityVisible(@IdRes int id) {
        TextView textView = findViewById(id);
        textView.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo para setar a visibilidade para INVISIBLE
     *
     * @param id - Id do componente a ter a visibilidade alterada
     */
    protected void setVisibilityInvisible(@IdRes int id) {
        TextView textView = findViewById(id);
        textView.setVisibility(View.INVISIBLE);
    }

}
