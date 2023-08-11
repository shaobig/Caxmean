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
import shaobig.caxmean.dialogs.listeners.OnEditCardConfirmListener;

public class EditCardDialog extends DialogFragment {

    private static final String CURRENT_CARD_KEY = "current_card";

    private static final String DIALOG_TILE_TEXT = "Редактирование карточки";
    private static final String POSITIVE_BUTTON_TEXT = "Подтвердить";
    private static final String NEGATIVE_BUTTON_TEXT = "Отменить";

    private OnEditCardConfirmListener editCardConfirmListener;

    private FullCard currentCard;

    public static DialogFragment getDialog(FullCard card) {
        Bundle args = new Bundle();
        args.putSerializable(CURRENT_CARD_KEY, card);

        DialogFragment dialog = new EditCardDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            editCardConfirmListener = (OnEditCardConfirmListener) context;
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            try {
                currentCard = (FullCard) getArguments().getSerializable(CURRENT_CARD_KEY);
            }
            catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_add_card, null);

        EditText firstMeaningEditText = dialogView.findViewById(R.id.add_card_first_meaning_edit_text);
        firstMeaningEditText.setText(getCurrentCard().getFirstMeaning());

        EditText secondMeaningEditText = dialogView.findViewById(R.id.add_card_second_meaning_edit_text);
        secondMeaningEditText.setText(getCurrentCard().getSecondMeaning());

        EditText thirdMeaningEditText = dialogView.findViewById(R.id.add_card_third_meaning_edit_text);
        thirdMeaningEditText.setText(getCurrentCard().getThirdMeaning());

        EditText fourthMeaningEditText = dialogView.findViewById(R.id.add_card_fourth_meaning_edit_text);
        fourthMeaningEditText.setText(getCurrentCard().getFourthMeaning());

        EditText fifthMeaningEditText = dialogView.findViewById(R.id.add_card_fifth_meaning_edit_text);
        fifthMeaningEditText.setText(getCurrentCard().getFifthMeaning());

        EditText firstTranslationEditText = dialogView.findViewById(R.id.add_card_first_translation_edit_text);
        firstTranslationEditText.setText(getCurrentCard().getFirstTranslation());

        EditText secondTranslationEditText = dialogView.findViewById(R.id.add_card_second_translation_edit_text);
        secondTranslationEditText.setText(getCurrentCard().getSecondTranslation());

        EditText thirdTranslationEditText = dialogView.findViewById(R.id.add_card_third_translation_edit_text);
        thirdTranslationEditText.setText(getCurrentCard().getThirdTranslation());

        EditText fourthTranslationEditText = dialogView.findViewById(R.id.add_card_fourth_translation_edit_text);
        fourthTranslationEditText.setText(getCurrentCard().getFourthTranslation());

        EditText fifthTranslationEditText = dialogView.findViewById(R.id.add_card_fifth_translation_edit_text);
        fifthTranslationEditText.setText(getCurrentCard().getFifthTranslation());

        EditText nativeHintEditText = dialogView.findViewById(R.id.add_card_native_hint_edit_text);
        nativeHintEditText.setText(getCurrentCard().getNativeHint());

        EditText foreignHintEditText = dialogView.findViewById(R.id.add_card_foreign_hint_edit_text);
        foreignHintEditText.setText(getCurrentCard().getForeignHint());

        EditText transcriptionEditText = dialogView.findViewById(R.id.add_card_transcription_edit_text);
        transcriptionEditText.setText(getCurrentCard().getTranscription());

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

                    FullCard newCard = new FullCard.Builder()
                            .setMeanings(meanings)
                            .setTranslations(translations)
                            .setNativeHint(nativeHint)
                            .setForeignHint(foreignHint)
                            .setTranscription(transcription)
                            .getCard();

                    editCardConfirmListener.onEditCardConfirm(getCurrentCard(), newCard);
                })
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        editCardConfirmListener = null;
    }

    public FullCard getCurrentCard() {
        return currentCard;
    }
}
