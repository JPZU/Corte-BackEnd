package com.arapos.corte.domain.Service;
import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemReference.CreateItemReferenceDTO;
import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FullOpService {
    @Autowired
    private OpService opService;

    @Autowired
    private ItemReferenceService itemReferenceService;

    @Autowired
    private ItemClothService itemClothService;

    @Transactional
    public void createCompletedOperation(CreateOpDTO opDTO, List<CreateItemReferenceDTO> refsDTO, List<CreateItemClothDTO> clothsDTO) {
        // 1. Crear la Op
        OpResponseDTO savedOp = opService.save(opDTO);

        // 2. Crear cada item_referencess usando el ID de la op creada
        for (CreateItemReferenceDTO ref : refsDTO) {
            ref.setOpId(savedOp.getOpId());
            itemReferenceService.save(ref);
        }

        // 3. Crear cada item_cloth con la misma lógica
        for (CreateItemClothDTO cloth : clothsDTO) {
            cloth.setOpId(savedOp.getOpId());
            itemClothService.save(cloth); // Este ya es transaccional, ¡perfecto!
        }
    }
}
