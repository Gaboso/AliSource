package br.com.alisource.alisource.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import br.com.alisource.alisource.R;
import br.com.alisource.alisource.mask.Mask;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Classe utilitaria para activities
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Metodo para trocar de activities
     *
     * @param context           - Contexto da tela atual
     * @param destionationClass - Classe da proxima tela
     */
    protected void goToActivity(Context context, Class destionationClass) {
        Intent intent = new Intent(context, destionationClass);
        startActivity(intent);
        enterActivityTransition();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitActivityTransition();
    }

    /**
     * Metodo para pegar o texto de um campo, passando o id dele
     *
     * @param fieldID - Id do campo, para poder pegar ele
     * @return texto que tinha dentro do campo selecionado
     */
    protected String getTextFromField(int fieldID) {
        // Campo de texto
        EditText editText = (EditText) findViewById(fieldID);

        //se o campo for null retorna null
        if (editText == null)
            return null;

        // chamando metodo para converter para string e retornando
        return editText.getText().toString().trim();
    }

    /**
     * Metodo para pgar a selecao ou nao selecao de um botao
     *
     * @param fieldID - Id do botao radio
     * @return true se o botão estava selecionado e false caso contrario
     */
    protected boolean getValueFromRadioButton(int fieldID) {
        RadioButton radioButton = (RadioButton) findViewById(fieldID);
        //retorna true se o botao de radio for valido e estiver checado
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

        if (field != null)
            field.addTextChangedListener(Mask.insert(getString(maskID), field));
    }

    /**
     * Metodo para mostrar ao usuario que tem um campo errado
     *
     * @param fieldID        - Id do campo a ser evidenciado em caso de erro
     * @param errorMessageID - Id da mensagem de erro a ser exibida no campo
     */
    private void showErrorMessage(int fieldID, int errorMessageID) {
        //Pega o campo
        EditText field = (EditText) findViewById(fieldID);

        if (field != null) {
            //Seta mensagem de erro nele
            field.setError(getString(errorMessageID));
            field.requestFocus();
        }
    }

    /**
     * Metodo que seta texto no campo e retorna texto que salvou
     *
     * @param fieldID - Id do campo a ser setado
     * @param content - Conteudo textual a ser setado no campo
     */
    protected void setText(int fieldID, String content) {
        //pega o campo
        TextView textView = (TextView) findViewById(fieldID);
        //seta o conteudo
        if (textView != null)
            textView.setText(content);
    }

    /**
     * Metodo que seta texto no campo e retorna texto que salvou
     *
     * @param fieldID   - Id do campo a ser setado
     * @param contentID - Id do conteudo textual a ser setado no campo
     */
    protected void setText(int fieldID, int contentID) {
        setText(fieldID, getString(contentID));
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
     * Metodo para retirar o botao de voltar ao pai da tela, das action bar
     */
    protected void removeButtonFromActionBar() {
        //Pegando a barra de acoes
        ActionBar actionBar = getSupportActionBar();
        //Tirando o botão de voltar para a tela anterior
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(false);
    }

    /**
     * Metodo para inserir os listeners de animacao dos campos e botoes de informacao correlacionados
     *
     * @param field      - Campo que deve receber o focus
     * @param infoButton - Botao de informacao que e relacionado ao campo que recebe o foco
     */
    protected void setFocusListenerAnimate(final View field, final View infoButton) {
        field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    infoButton.setVisibility(VISIBLE);
                    field.animate()
                            .translationX(0.0F)
                            .setDuration(500);
                    infoButton.animate()
                            .translationX(0.0F)
                            .setDuration(500)
                            .setListener(null);
                } else {
                    infoButton.animate()
                            .translationX(infoButton.getWidth())
                            .alpha(infoButton.getWidth())
                            .setDuration(500)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    infoButton.setVisibility(GONE);
                                    infoButton.animate().setListener(null);
                                }

                                @Override
                                public void onAnimationStart(Animator animation) {
                                    super.onAnimationStart(animation);
                                    field.animate().translationX(0.0F);
                                }
                            });
                }
            }
        });

        infoButton.setVisibility(GONE);
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