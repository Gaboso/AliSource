package br.com.alisource.alisource.mask;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * Classe que contêm mascara que podem ser inseridas em vários lugares.
 * Exemplo: telefone, cep, cpf, cnpj
 */
public class Mask {

    private Mask() {
    }

    /**
     * Método para tirar os caracteres especiais da mascara
     *
     * @param texto texto com todos os caracteres especiais
     * @return retorna texto sem nenhum caractere especial
     */
    private static String clearMask(String texto) {
        return texto.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "").replaceAll("[ ]", "");
    }

    public static TextWatcher insert(final String mask, final EditText editText) {
        return new TextWatcher() {
            boolean update;
            String old = "";

            public void onTextChanged(CharSequence newValue, int start, int before, int count) {
                String texto = Mask.clearMask(newValue.toString());
                String mascara = "";
                if (update) {
                    old = texto;
                    update = false;
                    return;
                }
                int i = 0;

                if (texto.length() > old.length()) {
                    for (char m : mask.toCharArray()) {
                        if (m != '#')
                            mascara += Character.toString(m);
                        else {
                            try {
                                mascara += Character.toString(texto.charAt(i));
                            } catch (Exception e) {
                                Log.d("Mask", e.getMessage(), e);
                                break;
                            }
                            i++;
                        }
                    }
                } else
                    mascara = newValue.toString();


                update = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

}