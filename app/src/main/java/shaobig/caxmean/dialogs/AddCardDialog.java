package shaobig.caxmean.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import shaobig.caxmean.R;
import shaobig.caxmean.database.entities.meta.dto.FullCard;
import shaobig.caxmean.dialogs.listeners.OnAddCardConfirmListener;

public class AddCardDialog extends DialogFragment {

    private static final String DIALOG_TILE_TEXT = "Добавление карточки";
    private static final String POSITIVE_BUTTON_TEXT = "Подтвердить";
    private static final String NEGATIVE_BUTTON_TEXT = "Отменить";

    private OnAddCardConfirmListener addCardConfirmListener;

    public static DialogFragment getAddCardDialog() {
        return new AddCardDialog();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            addCardConfirmListener = (OnAddCardConfirmListener) context;
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_add_card, null);

        EditText firstMeaningEditText = dialogView.findViewById(R.id.add_card_first_meaning_edit_text);
        EditText secondMeaningEditText = dialogView.findViewById(R.id.add_card_second_meaning_edit_text);
        EditText thirdMeaningEditText = dialogView.findViewById(R.id.add_card_third_meaning_edit_text);
        EditText fourthMeaningEditText = dialogView.findViewById(R.id.add_card_fourth_meaning_edit_text);
        EditText fifthMeaningEditText = dialogView.findViewById(R.id.add_card_fifth_meaning_edit_text);

        EditText firstTranslationEditText = dialogView.findViewById(R.id.add_card_first_translation_edit_text);
        EditText secondTranslationEditText = dialogView.findViewById(R.id.add_card_second_translation_edit_text);
        EditText thirdTranslationEditText = dialogView.findViewById(R.id.add_card_third_translation_edit_text);
        EditText fourthTranslationEditText = dialogView.findViewById(R.id.add_card_fourth_translation_edit_text);
        EditText fifthTranslationEditText = dialogView.findViewById(R.id.add_card_fifth_translation_edit_text);

        EditText nativeHintEditText = dialogView.findViewById(R.id.add_card_native_hint_edit_text);
        EditText foreignHintEditText = dialogView.findViewById(R.id.add_card_foreign_hint_edit_text);

        EditText transcriptionEditText = dialogView.findViewById(R.id.add_card_transcription_edit_text);

        return new AlertDialog.Builder(getContext())
                .setTitle(DIALOG_TILE_TEXT)
                .setView(dialogView)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, (dialog, which) -> {
                    String firstMeaning = firstMeaningEditText.getText().toString();
                    String secondMeaning = secondMeaningEditText.getText().toString();
                    String thirdMeaning = thirdMeaningEditText.getText().toString();
                    String fourthMeaning = fourthMeaningEditText.getText().toString();
                    String fifthMeaning = fifthMeaningEditText.getText().toString();

                    String firstTranslation = firstTranslationEditText.getText().toString();
                    String secondTranslation = secondTranslationEditText.getText().toString();
                    String thirdTranslation = thirdTranslationEditText.getText().toString();
                    String fourthTranslation = fourthTranslationEditText.getText().toString();
                    String fifthTranslation = fifthTranslationEditText.getText().toString();

                    String nativeHint = nativeHintEditText.getText().toString();
                    String foreignHint = foreignHintEditText.getText().toString();

                    String transcription = transcriptionEditText.getText().toString();

                    List<String> meanings = Stream.of(firstMeaning, secondMeaning, thirdMeaning, fourthMeaning, fifthMeaning)
                            .collect(Collectors.toList());
                    List<String> translations = Stream.of(firstTranslation, secondTranslation, thirdTranslation, fourthTranslation, fifthTranslation)
                            .collect(Collectors.toList());

                    FullCard card = new FullCard.Builder()
                            .setMeanings(meanings)
                            .setTranslations(translations)
                            .setNativeHint(nativeHint)
                            .setForeignHint(foreignHint)
                            .setTranscription(transcription)
                            .getCard();

                    addCardConfirmListener.onAddCardConfirm(card);
                })
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addCardConfirmListener = null;
    }
}
