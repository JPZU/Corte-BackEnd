package com.arapos.corte.domain.dto.ClothEntry;

import java.util.List;

import com.arapos.corte.domain.dto.ClothEntryItem.CreateClothEntryItemDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FullClothEntryDTO {

    @NotNull(message = "Cloth entry data is required")
    @Valid
    private CreateClothEntryDTO entry;

    @NotNull(message = "Cloth entry items list is required")
    @NotEmpty(message = "At least one cloth entry item is required")
    @Valid
    private List<CreateClothEntryItemDTO> items;

    public CreateClothEntryDTO getEntry() {
        return entry;
    }

    public void setEntry(CreateClothEntryDTO entry) {
        this.entry = entry;
    }

    public List<CreateClothEntryItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CreateClothEntryItemDTO> items) {
        this.items = items;
    }
}
