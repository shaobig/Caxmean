package shaobig.caxmean.dialogs.listeners;

import shaobig.caxmean.database.entities.meta.dto.FullCard;

public interface OnEditCardConfirmListener {
    void onEditCardConfirm(FullCard oldCard, FullCard newCard);
}
