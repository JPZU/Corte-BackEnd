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
        updatedClothDTO.setMeters(metersUpdated);
        updatedClothDTO.setIsActive(isActive);
        updatedClothDTO.setCategoryId(cloth.getCategory().getCategoryId());

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
        updatedOpDTO.setDescriptions(op.getDescriptions());
        updatedOpDTO.setUserId(op.getUser().getUserId());

        opService.update(updatedOpDTO);

        return savedItem;
    }

    @Transactional
    public ItemClothResponseDTO update(CreateItemClothDTO newDTO) {
        // 1. Obtener el itemCloth anterior
        ItemClothResponseDTO oldItem = getById(newDTO.getItemClothId())
                .orElseThrow(() -> new IllegalArgumentException("ItemCloth not found with ID: " + newDTO.getItemClothId()));

        int oldClothId = oldItem.getCloth().getClothId();
        int newClothId = newDTO.getClothId();

        // 2. Si la tela cambió, actualizar ambas telas (anterior y nueva)
        if (oldClothId != newClothId) {
            // 2.1 Restaurar metros a la tela anterior
            ClothResponseDTO oldCloth = clothService.getById(oldClothId)
                    .orElseThrow(() -> new IllegalArgumentException("Old cloth not found"));

            BigDecimal restoredMeters = oldCloth.getMeters().add(oldItem.getMeters());
            boolean oldClothActive = restoredMeters.compareTo(new BigDecimal("1")) > 0;

            CreateClothDTO updatedOldCloth = new CreateClothDTO();
            updatedOldCloth.setClothId(oldCloth.getClothId());
            updatedOldCloth.setName(oldCloth.getName());
            updatedOldCloth.setMeters(restoredMeters);
            updatedOldCloth.setIsActive(oldClothActive);
            updatedOldCloth.setCategoryId(oldCloth.getCategory().getCategoryId());
            clothService.update(updatedOldCloth);

            // 2.2 Descontar metros en la nueva tela
            ClothResponseDTO newCloth = clothService.getById(newClothId)
                    .orElseThrow(() -> new IllegalArgumentException("New cloth not found"));

            BigDecimal updatedNewMeters = newCloth.getMeters().subtract(newDTO.getMeters());
            if (updatedNewMeters.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Not enough meters in new cloth");
            }

            boolean newClothActive = updatedNewMeters.compareTo(new BigDecimal("1")) > 0;

            CreateClothDTO updatedNewCloth = new CreateClothDTO();
            updatedNewCloth.setClothId(newCloth.getClothId());
            updatedNewCloth.setName(newCloth.getName());
            updatedNewCloth.setMeters(updatedNewMeters);
            updatedNewCloth.setIsActive(newClothActive);
            updatedNewCloth.setCategoryId(newCloth.getCategory().getCategoryId());
            clothService.update(updatedNewCloth);
        } else {
            // 3. Si no cambió la tela, solo ajusta la diferencia
            ClothResponseDTO cloth = clothService.getById(newClothId)
                    .orElseThrow(() -> new IllegalArgumentException("Cloth not found"));

            BigDecimal difference = newDTO.getMeters().subtract(oldItem.getMeters()); // puede ser + o -
            BigDecimal updatedMeters = cloth.getMeters().subtract(difference);

            if (updatedMeters.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Not enough meters in cloth to update");
            }

            boolean isActive = updatedMeters.compareTo(new BigDecimal("1")) > 0;

            CreateClothDTO updatedCloth = new CreateClothDTO();
            updatedCloth.setClothId(cloth.getClothId());
            updatedCloth.setName(cloth.getName());
            updatedCloth.setMeters(updatedMeters);
            updatedCloth.setIsActive(isActive);
            updatedCloth.setCategoryId(cloth.getCategory().getCategoryId());
            clothService.update(updatedCloth);
        }

        // 4. Actualizar el itemCloth
        ItemClothResponseDTO updatedItem = itemClothRepository.update(newDTO);

        // 5. Recalcular el total de metros de la op
        OpResponseDTO op = opService.getById(newDTO.getOpId())
                .orElseThrow(() -> new IllegalArgumentException("Op not found with ID: " + newDTO.getOpId()));

        List<ItemClothResponseDTO> itemCloths = getByOpId(op.getOpId());
        BigDecimal totalMeters = itemCloths.stream()
                .map(ItemClothResponseDTO::getMeters)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CreateOpDTO updatedOp = new CreateOpDTO();
        updatedOp.setOpId(op.getOpId());
        updatedOp.setTotalMeters(totalMeters);
        updatedOp.setQuantityCloths(op.getQuantityCloths());
        updatedOp.setSchemaLength(op.getSchemaLength());
        updatedOp.setDescriptions(op.getDescriptions());
        updatedOp.setUserId(op.getUser().getUserId());
        opService.update(updatedOp);

        return updatedItem;
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
