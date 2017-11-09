package br.com.alisource.mask;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * Classe que contem mascara que podem ser inseridas em vários lugares.
 * Exemplo: telefone, cep, cpf, cnpj
 */
public class Mask {

    private static final String CLOSE_PARENTHESIS = "[)]";
    private static final String DOT = "[.]";
    private static final String HYPHEN = "[-]";
    private static final String OPEN_PARENTHESIS = "[(]";
    private static final String SLASH = "[/]";
    private static final String WHITE_SPACE = "[ ]";
    private static final char MASK_BASE_CHAR = '#';

    private Mask() {
    }

    /**
     * Metodo para inserir a mascara e um determindao campo
     *
     * @param maskFormat - Formato da mascara
     * @param field      - Campo a ser setado
     * @return retorna o listener adicionado no campo para controlar a mascara
     */
    public static TextWatcher insert(final String maskFormat, final EditText field) {
        return new TextWatcher() {
            boolean update;
            String oldValue = "";

            public void onTextChanged(CharSequence newValueWithMask, int start, int before, int count) {
                String valueWithoutMask = Mask.getValueWithoutMask(newValueWithMask.toString());
                String valueWithMask = "";

                if (update) {
                    oldValue = valueWithoutMask;
                    update = false;
                    return;
                }

                int position = 0;

                if (valueWithoutMask.length() > oldValue.length()) {
                    for (char maskChar : maskFormat.toCharArray()) {
                        if (maskChar != MASK_BASE_CHAR)
                            valueWithMask += Character.toString(maskChar);
                        else {
                            try {
                                valueWithMask += Character.toString(valueWithoutMask.charAt(position));
                            } catch (Exception e) {
                                Log.d("Mask", e.getMessage(), e);
                                break;
                            }
                            position++;
                        }
                    }
                } else {
                    valueWithMask = newValueWithMask.toString();
                }

                setMaskInField(valueWithMask);
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

            private void setMaskInField(String valueWithMask) {
                update = true;
                field.setText(valueWithMask);
                field.setSelection(valueWithMask.length());
            }
        };
    }

    /**
     * Metodo para tirar os caracteres especiais da mascara
     *
     * @param text - Texto com a mascara
     * @return texto sem a mascara
     */
    private static String getValueWithoutMask(String text) {
        return text.replaceAll(DOT, "")
                .replaceAll(HYPHEN, "")
                .replaceAll(SLASH, "")
                .replaceAll(OPEN_PARENTHESIS, "")
                .replaceAll(CLOSE_PARENTHESIS, "")
                .replaceAll(WHITE_SPACE, "");
    }

}