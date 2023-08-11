package shaobig.caxmean.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import shaobig.caxmean.R;
import shaobig.caxmean.database.entities.CardCollection;
import shaobig.caxmean.dialogs.listeners.OnEditCardCollectionConfirmListener;

public class EditCardCollectionDialog extends DialogFragment {

    private static final String DIALOG_TITLE = "Редактирование коллекции";

    private static final String POSITIVE_BUTTON_TEXT = "Подтвердить";
    private static final String NEGATIVE_BUTTON_TEXT = "Отменить";

    private static final String CARD_COLLECTION_KEY = "CARD_COLLECTION_KEY";

    private OnEditCardCollectionConfirmListener editCardCollectionConfirmListener;

    private CardCollection cardCollection;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            editCardCollectionConfirmListener = (OnEditCardCollectionConfirmListener) context;
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static DialogFragment getEditCardCollectionDialog(CardCollection collection) {
        Bundle args = new Bundle();

        args.putSerializable(CARD_COLLECTION_KEY, collection);

        DialogFragment dialog = new EditCardCollectionDialog();
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.cardCollection = (CardCollection) getArguments().getSerializable(CARD_COLLECTION_KEY);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_edit_card_collection, null);

        EditText nameEditText = view.findViewById(R.id.edit_card_collection_name_edit_text);
        EditText priorityEditText = view.findViewById(R.id.edit_card_collection_priority_edit_text);

        nameEditText.setText(getCardCollection().getName());
        priorityEditText.setText(String.valueOf(getCardCollection().getPriority()));

        return new AlertDialog.Builder(getContext())
                .setTitle(DIALOG_TITLE)
                .setView(view)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, (dialog, which) -> {
                    String name = nameEditText.getText().toString();
                    int priority = Integer.parseInt(priorityEditText.getText().toString());

                    getCardCollection().setName(name);
                    getCardCollection().setPriority(priority);

                    getEditCardCollectionConfirmListener().onEditCardCollectionConfirm(getCardCollection());
                })
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, (dialog, which) -> {})
                .show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        editCardCollectionConfirmListener = null;
    }

    public OnEditCardCollectionConfirmListener getEditCardCollectionConfirmListener() {
        return editCardCollectionConfirmListener;
    }

    public CardCollection getCardCollection() {
        return cardCollection;
    }
}
