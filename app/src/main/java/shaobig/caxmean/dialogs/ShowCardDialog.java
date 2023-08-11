package shaobig.caxmean.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import shaobig.caxmean.R;
import shaobig.caxmean.database.entities.meta.dto.FullCard;

public class ShowCardDialog extends DialogFragment {

    private static final String CARD_KEY = "card";

    private static final String DIALOG_TITLE = "Просмотр карты";
    private static final String POSITIVE_BUTTON_TEXT = "Закрыть";

    private static final String DEFAULT_TRANSCRIPTION_TEXT = " отсутствует";

    private FullCard card;

    public static DialogFragment createDialog(FullCard card) {
        DialogFragment fragment = new ShowCardDialog();

        Bundle args = new Bundle();
        args.putSerializable(CARD_KEY, card);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            try {
                FullCard card = (FullCard) getArguments().getSerializable(CARD_KEY);
                setCard(card);
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
        View dialogView = inflater.inflate(R.layout.dialog_show_card, null);

        setView(dialogView);

        return new AlertDialog.Builder(getActivity())
                .setTitle(DIALOG_TITLE)
                .setView(dialogView)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, (dialog, which) -> {})
                .create();
    }

    private void setView(View view) {
        TextView commonMeaningView = view.findViewById(R.id.show_card_common_meaning);
        TextView commonTranslationView = view.findViewById(R.id.show_card_common_translation);
        TextView meaningsView = view.findViewById(R.id.show_card_meanings);
        TextView translationsView = view.findViewById(R.id.show_card_translations);
        TextView nativeHintView = view.findViewById(R.id.show_card_native_hint);
        TextView foreignHintView = view.findViewById(R.id.show_card_foreign_hint);
        TextView transcriptionView = view.findViewById(R.id.show_card_transcription);

        String commonMeaning = getCard().getFirstMeaning();
        String commonTranslation = getCard().getFirstTranslation();
        String commonMeanings = getCard().getMeanings().toString();
        String commonTranslations = getCard().getTranslations().toString();
        String nativeHint = getCard().getNativeHint();
        String foreignHint = getCard().getForeignHint();

        String transcription = !getCard().getTranscription().isEmpty()
                ? "[".concat(getCard().getTranscription()).concat("]")
                : DEFAULT_TRANSCRIPTION_TEXT;

        commonMeaningView.setText(commonMeaning);
        commonTranslationView.setText(commonTranslation);
        meaningsView.setText(commonMeanings);
        translationsView.setText(commonTranslations);
        nativeHintView.setText(nativeHint);
        foreignHintView.setText(foreignHint);
        transcriptionView.setText(transcription);
    }

    public FullCard getCard() {
        return card;
    }

    private void setCard(FullCard card) {
        this.card = card;
    }
}
