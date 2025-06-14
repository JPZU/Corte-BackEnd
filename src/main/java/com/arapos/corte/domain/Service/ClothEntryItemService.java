package com.arapos.corte.domain.Service;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arapos.corte.domain.dto.ClothEntryItem.ClothEntryItemResponseDTO;
import com.arapos.corte.domain.dto.ClothEntryItem.CreateClothEntryItemDTO;
import com.arapos.corte.domain.dto.Cloth.ClothResponseDTO;
import com.arapos.corte.domain.dto.Cloth.CreateClothDTO;
import com.arapos.corte.domain.repository.ClothEntryItemRepository;
import com.arapos.corte.domain.repository.ClothRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@Service
public class ClothEntryItemService {

    @Autowired
    private ClothRepository clothRepository;

    @Autowired
    private ClothEntryItemRepository clothEntryItemRepository;

/* --------------------------------------------------------
                            BASIC CRUD
    --------------------------------------------------------- */
    public List<ClothEntryItemResponseDTO> getAll(){
        return clothEntryItemRepository.getAll();
    }

    public Optional<ClothEntryItemResponseDTO> getById(int clothEntryItemId){
        return clothEntryItemRepository.getById(clothEntryItemId);
    }

    @Transactional
    public ClothEntryItemResponseDTO save(CreateClothEntryItemDTO dto) {
        // Buscar si ya existe una tela con el mismo nombre y categoría (activa o inactiva)
        Page<ClothResponseDTO> clothsPage = clothRepository.filterCloths(
            dto.getName(), null, dto.getCategoryId(), 0, 1
        );

        Integer clothId;

        if (!clothsPage.isEmpty()) {
            // Si existe, actualizamos los metros disponibles
            ClothResponseDTO clothDTO = clothsPage.getContent().get(0);
            BigDecimal updatedMeters = clothDTO.getMeters().add(dto.getMetersAdded());

            CreateClothDTO updateDTO = new CreateClothDTO();
            updateDTO.setClothId(clothDTO.getClothId());
            updateDTO.setName(clothDTO.getName());
            updateDTO.setMeters(updatedMeters);
            updateDTO.setIsActive(true);
            updateDTO.setCategoryId(dto.getCategoryId());

            clothRepository.update(updateDTO);

            clothId = clothDTO.getClothId(); // Asignar el ID existente
        } else {
            // Si no existe, creamos una nueva tela
            CreateClothDTO createDTO = new CreateClothDTO();
            createDTO.setName(dto.getName());
            createDTO.setMeters(dto.getMetersAdded());
            createDTO.setIsActive(true);
            createDTO.setCategoryId(dto.getCategoryId());

            ClothResponseDTO newCloth = clothRepository.save(createDTO);
            clothId = newCloth.getClothId(); // Asignar el nuevo ID creado
        }

        // Asignamos el clothId encontrado o creado al DTO original
        dto.setClothId(clothId);

        // Guardamos el ítem de entrada con la tela correspondiente
        return clothEntryItemRepository.save(dto);
    }

    @Transactional
    public ClothEntryItemResponseDTO update(CreateClothEntryItemDTO dto) {
        // 1. Obtener el ítem actual
        ClothEntryItemResponseDTO existing = clothEntryItemRepository.getById(dto.getClothEntryItemId())
                .orElseThrow(() -> new RuntimeException("ClothEntryItem no encontrado"));

        int oldClothId = existing.getCloth().getClothId();
        int newClothId = dto.getClothId();

        // 2. Caso simple: solo cambia color o precio
        boolean metersChanged = dto.getMetersAdded().compareTo(existing.getMetersAdded()) != 0;
        boolean sameCloth = oldClothId == newClothId;

        if (!metersChanged && sameCloth) {
            return clothEntryItemRepository.update(dto); // no afecta las telas
        }

        // 3. Caso: cambia cantidad, pero no la tela asociada
        if (sameCloth) {
            BigDecimal delta = dto.getMetersAdded().subtract(existing.getMetersAdded());

            ClothResponseDTO cloth = clothRepository.getById(oldClothId)
                    .orElseThrow(() -> new RuntimeException("Tela no encontrada"));

            BigDecimal updatedMeters = cloth.getMeters().add(delta);
            if (updatedMeters.compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("No se pueden dejar metros negativos");
            }

            CreateClothDTO clothUpdate = new CreateClothDTO();
            clothUpdate.setClothId(cloth.getClothId());
            clothUpdate.setName(cloth.getName());
            clothUpdate.setMeters(updatedMeters);
            clothUpdate.setIsActive(true);
            clothUpdate.setCategoryId(cloth.getCategory().getCategoryId());

            clothRepository.update(clothUpdate);

            return clothEntryItemRepository.update(dto);
        }

        // 4. Caso: clothId cambió → mover metros de una tela a otra

        // 4.1 Restar metros de la tela anterior
        ClothResponseDTO oldCloth = clothRepository.getById(oldClothId)
                .orElseThrow(() -> new RuntimeException("Tela anterior no encontrada"));

        BigDecimal oldUpdatedMeters = oldCloth.getMeters().subtract(existing.getMetersAdded());
        if (oldUpdatedMeters.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Metros negativos en tela anterior");
        }

        CreateClothDTO oldUpdate = new CreateClothDTO();
        oldUpdate.setClothId(oldCloth.getClothId());
        oldUpdate.setName(oldCloth.getName());
        oldUpdate.setMeters(oldUpdatedMeters);
        oldUpdate.setIsActive(oldUpdatedMeters.compareTo(new BigDecimal("1")) > 0);
        oldUpdate.setCategoryId(oldCloth.getCategory().getCategoryId());

        clothRepository.update(oldUpdate);

        // 4.2 Verificar si la nueva tela ya existe (por name + category)
        Page<ClothResponseDTO> match = clothRepository.filterCloths(
                dto.getName(), null, dto.getCategoryId(), 0, 1);

        int finalClothId;
        if (!match.isEmpty()) {
            // Ya existe → sumarle metros
            ClothResponseDTO matched = match.getContent().get(0);
            BigDecimal newMeters = matched.getMeters().add(dto.getMetersAdded());

            CreateClothDTO newUpdate = new CreateClothDTO();
            newUpdate.setClothId(matched.getClothId());
            newUpdate.setName(matched.getName());
            newUpdate.setMeters(newMeters);
            newUpdate.setIsActive(true);
            newUpdate.setCategoryId(matched.getCategory().getCategoryId());

            clothRepository.update(newUpdate);
            finalClothId = matched.getClothId();

        } else {
            // No existe → crear nueva tela
            CreateClothDTO newCloth = new CreateClothDTO();
            newCloth.setName(dto.getName());
            newCloth.setMeters(dto.getMetersAdded());
            newCloth.setIsActive(true);
            newCloth.setCategoryId(dto.getCategoryId());

            ClothResponseDTO created = clothRepository.save(newCloth);
            finalClothId = created.getClothId();
        }

        // 4.3 Eliminar tela anterior si quedó vacía
        if (oldUpdatedMeters.compareTo(BigDecimal.ZERO) == 0) {
            clothRepository.delete(oldCloth.getClothId());
        }

        // 4.4 Actualizar clothId y el ítem final
        dto.setClothId(finalClothId);
        return clothEntryItemRepository.update(dto);
    }

    public boolean delete(int clothEntryItemId){
        if(clothEntryItemRepository.getById(clothEntryItemId).isPresent()){
            clothEntryItemRepository.delete(clothEntryItemId);
            return true;
        }else {
            return false;
        }
    }
    /* --------------------------------------------------------
                        PERSONALIZED QUERYS
    --------------------------------------------------------- */
    public Optional<ClothEntryItemResponseDTO> getByName(String name){
        return clothEntryItemRepository.getByName(name);
    }

    public List<ClothEntryItemResponseDTO> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        return clothEntryItemRepository.findByCreatedAtBetween(startDate, endDate);
    }
    /* --------------------------------------------------------
                        RELATIONSHIP METHODS
    --------------------------------------------------------- */
    public List<ClothEntryItemResponseDTO> findByCategoryId(int categoryId){
        return clothEntryItemRepository.findByCategoryId(categoryId);
    }

    public List<ClothEntryItemResponseDTO> findByClothEntryId(int clothEntryId){
        return clothEntryItemRepository.findByClothEntryId(clothEntryId);
    }

    public List<ClothEntryItemResponseDTO> findByClothId(int clothId){
        return clothEntryItemRepository.findByClothId(clothId);
    }
}
