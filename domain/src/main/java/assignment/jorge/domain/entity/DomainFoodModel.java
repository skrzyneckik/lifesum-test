package assignment.jorge.domain.entity;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public class DomainFoodModel {

    private Integer mId;
    private CharSequence mName;
    private boolean isSaved;

    public DomainFoodModel() {
        mId = 0;
        mName = "";
        isSaved = false;
    }

    @Nonnull
    public Integer getId() {
        return mId;
    }

    public void setId(final @Nonnull Integer id) {
        mId = id;
    }

    @Nonnull
    public CharSequence getName() {
        return mName;
    }

    public void setName(final @Nonnull CharSequence name) {
        mName = name;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(final boolean saved) {
        isSaved = saved;
    }

    @Override
    public boolean equals(final @CheckForNull Object o) {
        return o != null && o instanceof DomainFoodModel && ((DomainFoodModel) o).getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
