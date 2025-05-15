package com.arapos.corte.domain.dto.Op;

import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class FullOpRequestDTO {

    @NotNull(message = "Operation data is required")
    @Valid
    private CreateOpDTO op;

    @NotNull(message = "Item references list is required")
    @NotEmpty(message = "At least one item reference is required")
    @Valid
    private List<CreateItemReferenceDTO> references;

    @NotNull(message = "Item cloths list is required")
    @NotEmpty(message = "At least one item cloth is required")
    @Valid
    private List<CreateItemClothDTO> cloths;

    public CreateOpDTO getOp() {
        return op;
    }

    public void setOp(CreateOpDTO op) {
        this.op = op;
    }

    public List<CreateItemReferenceDTO> getReferences() {
        return references;
    }

    public void setReferences(List<CreateItemReferenceDTO> references) {
        this.references = references;
    }

    public List<CreateItemClothDTO> getCloths() {
        return cloths;
    }

    public void setCloths(List<CreateItemClothDTO> cloths) {
        this.cloths = cloths;
    }
}
