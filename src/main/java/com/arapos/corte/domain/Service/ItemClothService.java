package com.arapos.corte.domain.Service;

import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.CreateItemClothDTO;
import com.arapos.corte.domain.dto.ItemCloth.ItemClothResponseDTO;
import com.arapos.corte.domain.dto.Op.OpResponseDTO;
import com.arapos.corte.domain.dto.Op.CreateOpDTO;
import com.arapos.corte.domain.repository.ItemClothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemClothService {

    @Autowired
    private ItemClothRepository itemClothRepository;

    @Autowired
    private ClothService clothService; // Asegúrate de inyectar el servicio

    @Autowired
    private OpService opService;

    /* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<ItemClothResponseDTO> getAll(){
        return itemClothRepository.getAll();
    }

    public Optional<ItemClothResponseDTO> getById(int ClothId) {
        return itemClothRepository.getById(ClothId);
    }

    @Transactional
    public ItemClothResponseDTO save(CreateItemClothDTO createItemClothDTO) {
        /* --------------------------------------------------------
                            Saved cloth meters
        --------------------------------------------------------- */
        ClothResponseDTO cloth = clothService.getById(createItemClothDTO.getClothId())
                .orElseThrow(() -> new IllegalArgumentException("Cloth not found with ID: " + createItemClothDTO.getClothId()));

        if (!cloth.getIsActive()) {
            throw new IllegalStateException("The cloth is inactive and cannot be used.");
        }

        BigDecimal actualMeters = cloth.getMeters();
        BigDecimal metersDiscount = createItemClothDTO.getMeters();
        BigDecimal metersUpdated = actualMeters.subtract(metersDiscount);

        if (metersUpdated.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Not enough meters in cloth to subtract");
        }

        boolean isActive = metersUpdated.compareTo(new BigDecimal("1")) > 0;

        CreateClothDTO updatedClothDTO = new CreateClothDTO();
        updatedClothDTO.setClothId(cloth.getClothId());
        updatedClothDTO.setName(cloth.getName());
        updatedClothDTO.setColor(cloth.getColor());
        updatedClothDTO.setMeters(metersUpdated);
        updatedClothDTO.setIsActive(isActive);
        updatedClothDTO.setCategoryId(cloth.getCategory().getCategoryId());
        updatedClothDTO.setSupplierId(cloth.getSupplier().getSupplierId());
        updatedClothDTO.setUserId(cloth.getUser().getUserId());

        clothService.update(updatedClothDTO);

        /* --------------------------------------------------------
                            Save itemCloth
        --------------------------------------------------------- */
        ItemClothResponseDTO savedItem = itemClothRepository.save(createItemClothDTO);

        /* --------------------------------------------------------
                        Update Op total meters
        --------------------------------------------------------- */
        OpResponseDTO op = opService.getById(createItemClothDTO.getOpId())
                .orElseThrow(() -> new IllegalArgumentException("Op not found with ID: " + createItemClothDTO.getOpId()));

        List<ItemClothResponseDTO> itemCloths = getByOpId(op.getOpId());

        BigDecimal totalMeters = BigDecimal.ZERO;

        for (ItemClothResponseDTO item : itemCloths) {
            totalMeters = totalMeters.add(item.getMeters());
        }


        CreateOpDTO updatedOpDTO = new CreateOpDTO();
        updatedOpDTO.setOpId(op.getOpId());
        updatedOpDTO.setTotalMeters(totalMeters);
        updatedOpDTO.setQuantityCloths(op.getQuantityCloths());
        updatedOpDTO.setSchemaLength(op.getSchemaLength());
        updatedOpDTO.setUserId(op.getUser().getUserId());

        opService.update(updatedOpDTO);

        return savedItem;
    }

    public ItemClothResponseDTO update(CreateItemClothDTO createItemClothDTO){
        return itemClothRepository.update(createItemClothDTO);
    }

    public boolean delete(int itemClothId){
        if(itemClothRepository.getById(itemClothId).isPresent()){
            itemClothRepository.delete(itemClothId);
            return true;
        }else{
            return false;
        }
    }

    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    public List<ItemClothResponseDTO> getByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return itemClothRepository.findByCreatedAtBetween(startDate, endDate);
    }

    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    public List<ItemClothResponseDTO> getByClothId(int clothId){
        return itemClothRepository.findByClothId(clothId);
    }

    public List<ItemClothResponseDTO> getByOpId(int opId){
        return itemClothRepository.findByOpId(opId);
    }
}
