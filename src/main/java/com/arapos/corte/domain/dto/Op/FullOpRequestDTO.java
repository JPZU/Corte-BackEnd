package com.arapos.corte.domain.dto.Op;

import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;

import java.util.List;

public class FullOpRequestDTO {

    private CreateOpDTO op;
    private List<CreateItemReferenceDTO> references;
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
