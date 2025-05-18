package br.com.alisource.mask;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Helper class to apply masks to EditText fields.
 * <p>
 * Example mask patterns:
 * - US phone: (###) ###-####
 * - UK mobile: 07### ######
 * - BR mobile: (##) 9####-####
 */
public class Mask {

    private static final char MASK_BASE_CHAR = '#';
    private static final String EMPTY_STRING = "";

    private Mask() {
    }

    /**
     * Applies a given mask to an EditText field.
     *
     * @param maskFormat The format of the mask (e.g., (###) ###-####)
     * @param field      The EditText field to apply the mask to
     * @return The TextWatcher used to apply the mask (can be removed later if needed)
     */
    public static TextWatcher insert(final String maskFormat, final EditText field) {
        return new TextWatcher() {
            boolean update;
            String oldValue = EMPTY_STRING;

            @Override
            public void onTextChanged(CharSequence newValueWithMask, int start, int before, int count) {
                String valueWithoutMask = getValueWithoutMask(newValueWithMask.toString());

                if (update) {
                    oldValue = valueWithoutMask;
                    update = false;
                    return;
                }

                StringBuilder valueWithMask = new StringBuilder();
                int inputIndex = 0;
                int inputLength = valueWithoutMask.length();

                for (int i = 0; i < maskFormat.length(); i++) {
                    char maskChar = maskFormat.charAt(i);

                    if (maskChar != MASK_BASE_CHAR) {
                        valueWithMask.append(maskChar);
                    } else {
                        if (inputIndex < inputLength) {
                            valueWithMask.append(valueWithoutMask.charAt(inputIndex));
                            inputIndex++;
                        } else {
                            break; // Avoid IndexOutOfBounds
                        }
                    }
                }

                setMaskInField(valueWithMask.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No-op
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No-op
            }

            private void setMaskInField(String valueWithMask) {
                update = true;
                field.setText(valueWithMask);
                field.setSelection(valueWithMask.length());
            }
        };
    }

    /**
     * Removes special characters from a masked string.
     *
     * @param textWithMask The masked text
     * @return The raw string without mask characters
     */
    private static String getValueWithoutMask(String textWithMask) {
        return textWithMask.replaceAll("[()\\-./\\s]", EMPTY_STRING);
    }

}