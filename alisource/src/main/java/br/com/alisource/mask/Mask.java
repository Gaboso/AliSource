package br.com.alisource.mask;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

/**
 * Helper class to use field masks.
 * <p>
 * Example mask patterns:
 * Phone number US: (###) ###-####
 * Mobiles in UK: 07### ######
 * Mobiles in BR: (##) 9####-####
 */
public class Mask {

    private static final String CLOSE_PARENTHESIS = "[)]";
    private static final String DOT = "[.]";
    private static final String HYPHEN = "[-]";
    private static final String OPEN_PARENTHESIS = "[(]";
    private static final String SLASH = "[/]";
    private static final String WHITE_SPACE = "[ ]";
    private static final char MASK_BASE_CHAR = '#';
    private static final String EMPTY_STRING = "";

    private Mask() {
    }

    /**
     * Method to insert a mask in a field
     *
     * @param maskFormat - Mask format
     * @param field      - Field that will receive mask
     * @return listener that was added in field to control mask
     */
    public static TextWatcher insert(final String maskFormat, final EditText field) {
        return new TextWatcher() {
            boolean update;
            String oldValue = EMPTY_STRING;

            public void onTextChanged(CharSequence newValueWithMask, int start, int before, int count) {
                String valueWithoutMask = Mask.getValueWithoutMask(newValueWithMask.toString());
                String valueWithMask = EMPTY_STRING;

                if (update) {
                    oldValue = valueWithoutMask;
                    update = false;
                    return;
                }

                int position = 0;

                if (valueWithoutMask.length() > oldValue.length()) {
                    for (char maskChar : maskFormat.toCharArray()) {
                        if (maskChar != MASK_BASE_CHAR) {
                            valueWithMask += Character.toString(maskChar);
                        } else {
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
     * Method to remove special characters from mask
     *
     * @param textWithMask - Text with mask
     * @return Text without mask
     */
    private static String getValueWithoutMask(String textWithMask) {
        return textWithMask.replaceAll(DOT, EMPTY_STRING)
                .replaceAll(HYPHEN, EMPTY_STRING)
                .replaceAll(SLASH, EMPTY_STRING)
                .replaceAll(OPEN_PARENTHESIS, EMPTY_STRING)
                .replaceAll(CLOSE_PARENTHESIS, EMPTY_STRING)
                .replaceAll(WHITE_SPACE, EMPTY_STRING);
    }

}