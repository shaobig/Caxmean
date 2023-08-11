package shaobig.caxmean.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import shaobig.caxmean.R;

public class CardCollectionViewHolder extends RecyclerView.ViewHolder {

    private TextView numberView;
    private TextView nameView;

    private ImageButton editCardButton;
    private ImageButton deleteCardButton;

    public CardCollectionViewHolder(@NonNull View itemView) {
        super(itemView);

        numberView = itemView.findViewById(R.id.card_number);
        nameView = itemView.findViewById(R.id.card_name);

        editCardButton = itemView.findViewById(R.id.card_edit_button);
        deleteCardButton = itemView.findViewById(R.id.card_delete_button);
    }

    public TextView getNumberView() {
        return numberView;
    }

    public TextView getNameView() {
        return nameView;
    }

    public ImageButton getDeleteCardButton() {
        return deleteCardButton;
    }

    public ImageButton getEditCardButton() {
        return editCardButton;
    }

    public void setNumberView(Integer number) {
        if (number == null) {
            throw new NullPointerException("The number attribute has no reference to an object");
        }
        getNumberView().setText(String.valueOf(number));
    }

    public void setNameView(String name) {
        if (name == null) {
            throw new NullPointerException("The name attribute has no reference to an object");
        }
        getNameView().setText(name);
    }
}
