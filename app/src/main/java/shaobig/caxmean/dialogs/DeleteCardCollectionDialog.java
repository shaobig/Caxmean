package shaobig.caxmean.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import shaobig.caxmean.R;
import shaobig.caxmean.database.entities.CardCollection;
import shaobig.caxmean.dialogs.listeners.OnDeleteCardCollectionConfirmListener;

public class DeleteCardCollectionDialog extends DialogFragment {

    private static final String CARD_COLLECTION_KEY = "CARD_COLLECTION_KEY";

    private static final String POSITIVE_BUTTON_TEXT = "Подтвердить";
    private static final String NEGATIVE_BUTTON_TEXT = "Отмена";

    private static final String ATTENTION_TEXT =
            "Удаление коллекции карточек является весьма опасным шагом." +
            " Восстановление может быть затруднительным. Вы действительно уверены в совершении данного действа?" +
                    " Для продолжения, введите пожалуйста название коллекции: ";

    private CardCollection collection;

    private OnDeleteCardCollectionConfirmListener deleteCardCollectionConfirmListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            deleteCardCollectionConfirmListener = (OnDeleteCardCollectionConfirmListener) context;
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static DialogFragment getDeleteCardCollectionDialog(CardCollection collection) {
        Bundle args = new Bundle();
        args.putSerializable(CARD_COLLECTION_KEY, collection);

        DialogFragment dialog = new DeleteCardCollectionDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            collection = (CardCollection) getArguments().getSerializable(CARD_COLLECTION_KEY);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_delete_card_collection, null);

        TextView attentionTextView = view.findViewById(R.id.delete_card_collection_attention_label);
        attentionTextView.setText(
                ATTENTION_TEXT.concat("\n").concat("\"").concat(getCollection().getName()).concat("\"")
        );

        EditText nameEditText = view.findViewById(R.id.delete_card_collection_name_edit_text);

        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, (dialog, which) -> {
                    String name = nameEditText.getText().toString();
                    getDeleteCardCollectionConfirmListener().onDeleteCardCollectionConfirm(getCollection(), name);
                })
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, (dialog, which) -> {})
                .show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        deleteCardCollectionConfirmListener = null;
    }

    public CardCollection getCollection() {
        return collection;
    }

    public OnDeleteCardCollectionConfirmListener getDeleteCardCollectionConfirmListener() {
        return deleteCardCollectionConfirmListener;
    }
}
