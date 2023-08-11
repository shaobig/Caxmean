package shaobig.caxmean.adapter.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import shaobig.caxmean.R;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    private TextView nameView;
    private TextView createdView;
    private TextView editedView;
    private TextView priorityView;

    private ImageButton editButton;
    private ImageButton deleteButton;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.card_name);
        createdView = itemView.findViewById(R.id.card_collection_created);
        editedView = itemView.findViewById(R.id.card_collection_edited);
        priorityView = itemView.findViewById(R.id.card_collection_priority);

        editButton = itemView.findViewById(R.id.card_collection_edit_button);
        deleteButton = itemView.findViewById(R.id.card_collection_delete_button);
    }

    public TextView getNameView() {
        return nameView;
    }

    public TextView getCreatedView() {
        return createdView;
    }

    public TextView getEditedView() {
        return editedView;
    }

    public TextView getPriorityView() {
        return priorityView;
    }

    public ImageButton getEditButton() {
        return editButton;
    }

    public ImageButton getDeleteButton() {
        return deleteButton;
    }

    public void setNameView(String name) {
        if (name == null) {
            throw new NullPointerException("The name attribute has no reference to an object");
        }
        getNameView().setText(name);
    }

    public void setCreatedView(String created) {
        if (created == null) {
            throw new NullPointerException("The created attribute has no reference to an object");
        }
        getCreatedView().setText(created);
    }

    public void setEditedView(String edited) {
        if (edited == null) {
            throw new NullPointerException("The edited attribute has no reference to an object");
        }
        getEditedView().setText(edited);
    }

    public void setPriorityView(Integer priority) {
        if (priority == null) {
            throw new NullPointerException("The priority attribute has no reference to an object");
        }
        getPriorityView().setText(String.valueOf(priority));
    }
}
