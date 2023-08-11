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

import shaobig.caxmean.R;
import shaobig.caxmean.database.entities.CardCollection;
import shaobig.caxmean.dialogs.listeners.OnAddCardCollectionConfirmListener;

public class AddCardCollectionDialog extends DialogFragment {

    private static final String DIALOG_TITLE = "Создание коллекции";
    private static final String POSITIVE_BUTTON_TEXT = "Подтвердить";
    private static final String NEGATIVE_BUTTON_TEXT = "Отклонить";

    private static final String DEFAULT_CARD_COLLECTION_NAME = "Untitled";
    private static final Integer DEFAULT_CARD_COLLECTION_PRIORITY = 1;

    private OnAddCardCollectionConfirmListener addCardCollectionConfirmListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            addCardCollectionConfirmListener = (OnAddCardCollectionConfirmListener) context;
        }
        catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.dialog_add_card_collection, null);

        EditText nameView = dialogView.findViewById(R.id.add_card_collection_name_edit_text);
        EditText priorityView = dialogView.findViewById(R.id.add_card_collection_priority_edit_text);

        return new AlertDialog.Builder(getContext())
                .setTitle(DIALOG_TITLE)
                .setView(dialogView)
                .setPositiveButton(POSITIVE_BUTTON_TEXT, (dialog, which) -> {
                    String name = !nameView.getText().toString().isEmpty()
                            ? nameView.getText().toString()
                            : DEFAULT_CARD_COLLECTION_NAME;

                    Integer priority = !priorityView.getText().toString().isEmpty()
                            ? Integer.parseInt(priorityView.getText().toString())
                            : DEFAULT_CARD_COLLECTION_PRIORITY;

                    CardCollection collection = new CardCollection(name, priority);
                    addCardCollectionConfirmListener.onAddCardCollectionConfirm(collection);
                })
                .setNegativeButton(NEGATIVE_BUTTON_TEXT, (dialog, which) -> {})
                .create();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        addCardCollectionConfirmListener = null;
    }
}
