package br.com.alisource.alisource.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.TextView;

import br.com.alisource.alisource.R;
import br.com.alisource.alisource.mask.Mask;


/**
 * Created by Gaboso
 * on 01/11/2016
 * <p>
 * ActivityUtil
 * </p>
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Metodo para trocar de activities
     *
     * @param view              - View atual
     * @param destionationClass - Classe da proxima tela
     */
    protected void changeActivity(View view, Class destionationClass) {
        Intent intent = new Intent(view.getContext(), destionationClass);
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
    protected String getTextFromField(int fieldID) {
        EditText editText = (EditText) findViewById(fieldID);

        if (editText == null) {
            return null;
        }

        return editText.getText().toString().trim();
    }

    /**
     * Metodo para pegar um valor double pelo id do campo
     *
     * @param fieldID - id do campo
     * @return O valor do campo convertido para double
     */
    protected Double getDoubleFromField(int fieldID) {
        String text = getTextFromField(fieldID);

        if (text != null) {
            return Double.parseDouble(text);
        }

        return null;
    }

    /**
     * Metodo para pegar a selecao ou nao selecao de um botao
     *
     * @param fieldID - Id do botao radio
     * @return true se o botao estava selecionado e false caso contrario
     */
    protected boolean getBooleanFromRadio(int fieldID) {
        RadioButton radioButton = (RadioButton) findViewById(fieldID);

        return radioButton != null && radioButton.isChecked();
    }

    /**
     * Metodo para inserir mascara nos campos
     *
     * @param maskID  - Id da mascara escolhida para o campo
     * @param fieldID - Id do campo a receber a mascara
     */
    protected void insertMaskInField(int fieldID, int maskID) {
        EditText field = (EditText) findViewById(fieldID);

        if (field != null) {
            field.addTextChangedListener(Mask.insert(getString(maskID), field));
        }
    }

    /**
     * Metodo para exibir dialogo de confirmacao para sair do app
     */
    protected void showBackMessage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit_title);
        builder.setMessage(R.string.exit_question_statement);
        builder.setPositiveButton(R.string.exit_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Metodo para mostrar ao usuario que tem um campo errado
     *
     * @param fieldID        - Id do campo a ser evidenciado em caso de erro
     * @param errorMessageID - Id da mensagem de erro a ser exibida no campo
     */
    private void showErrorMessage(int fieldID, int errorMessageID) {
        TextInputLayout field = (TextInputLayout) findViewById(fieldID);

        if (field != null) {
            field.setErrorEnabled(true);
            field.setError(getString(errorMessageID));
        }
    }

    /**
     * Metodo para verificar se o campo não é nulo nem vazio
     *
     * @param idField  - id do campo a ser validado
     * @param idLayout - id do layout a ser evidenciado em caso de erro
     * @return true se não for vazio e nem nulo, false se for nulo ou vazio
     */
    protected boolean validateEditText(int idField, int idLayout) {
        String text = getTextFromField(idField);

        if (text == null || text.isEmpty()) {
            showErrorMessage(idLayout, R.string.validation_required);
            return false;
        }

        clearErrorField(idLayout);
        return true;
    }

    private void clearErrorField(int fieldID) {
        TextInputLayout field = (TextInputLayout) findViewById(fieldID);
        field.setErrorEnabled(false);
    }

    private int getInputType(int fieldID) {
        EditText editText = (EditText) findViewById(fieldID);
        return editText.getInputType();
    }

    /**
     * Metodo que seta texto no campo
     *
     * @param fieldID - Id do campo a ser setado
     * @param content - Conteudo textual a ser setado no campo
     */
    protected void setText(int fieldID, String content) {
        TextView textView = (TextView) findViewById(fieldID);

        if (textView != null) {
            textView.setText(content);
        }
    }

    /**
     * Metodo que seta texto no campo
     *
     * @param fieldID   - Id do campo a ser setado
     * @param contentID - Id do conteudo textual a ser setado no campo
     */
    protected void setText(int fieldID, int contentID) {
        setText(fieldID, getString(contentID));
    }

    /**
     * Metodo para inserir um texto formatado em uma TextView
     *
     * @param fieldID - id da TextView na qual o texto sera inserido
     * @param text    - texto que sera inserido de maneira formatada na TextView
     */
    protected void setTextHTMLFormatInTextView(int fieldID, String text) {
        String bodyContent = "<body>" + text + "</body>";
        setHTMLContentInTextView(fieldID, bodyContent);
    }

    /**
     * Metodo que seta cor em um texto
     *
     * @param fieldID - Id do campo que contem o texto a receber a cor
     * @param colorID - Id da cor a ser setada no texto
     */
    protected void setTextColor(int fieldID, int colorID) {
        TextView textView = (TextView) findViewById(fieldID);

        if (textView != null) {
            textView.setTextColor(ContextCompat.getColor(getApplicationContext(), colorID));
        }
    }

    /**
     * Metodo para marcar um RadioButton
     *
     * @param radioID - id do RabioButton a ser marcado
     */
    protected void checkRadio(int radioID) {
        RadioButton radioButton = (RadioButton) findViewById(radioID);

        if (radioButton != null) {
            radioButton.setChecked(true);
        }
    }

    /**
     * Metodo para checar se o celeular possui conectividade com a rede
     *
     * @return retorna true para conectividade e false para a falta dela
     */
    protected boolean networkConnectivity() {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
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
    protected void clearFocus(int fieldID) {
        EditText editText = (EditText) findViewById(fieldID);

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
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * Metodo para setar conteudo formatado com tags HTML
     *
     * @param fieldID     - id do campo a receber o texto formatado
     * @param bodyContent - body do html com as formatacoes necessarias
     */
    private void setHTMLContentInTextView(int fieldID, String bodyContent) {
        String htmlFormatted = "<html><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
                bodyContent + "</html>";

        TextView textView = (TextView) findViewById(fieldID);

        if (textView != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                textView.setText(Html.fromHtml(htmlFormatted, Html.FROM_HTML_MODE_LEGACY));
            else
                textView.setText(Html.fromHtml(htmlFormatted));
        }
    }

    /**
     * Metodo que cria transicao de entrada para uma activity
     */
    protected void enterActivityTransition() {
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }

    /**
     * Metodo que cria transicao de saida para uma activity
     */
    protected void exitActivityTransition() {
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

}
