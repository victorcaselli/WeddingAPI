package br.com.casellisoftware.weddingapi.services;

import br.com.casellisoftware.weddingapi.dto.GuestDto;
import br.com.casellisoftware.weddingapi.entities.Guest;
import br.com.casellisoftware.weddingapi.repositories.GuestRepository;
import br.com.casellisoftware.weddingapi.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.casellisoftware.weddingapi.dto.GuestDto.toDto;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    /**
     * Find all guest objects from database
     * @return List<GuestDto>
     */

    @Transactional(readOnly = true)
    public List<GuestDto> findAll(){
        return guestRepository.findAll().stream()
                .map(GuestDto::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find unique guest by id
     * @param id
     * @return GuestDto
     */

    @Transactional(readOnly = true)
    public GuestDto findById(Long id){

        // TODO - create a new exception to be thrown here

        return toDto(
                guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id. Id: "+id))
        );
    }


    /**
     * Save unique guest
     * @param guestDto
     * @return GuestDto
     */

    @Transactional
    public GuestDto save(GuestDto guestDto){
        return toDto(guestRepository.save(guestDto.toEntity()));
    }

    /**
     * Save multiple guest
     * @param guestDtoList
     * @return List<GuestDto>
     */

    @Transactional
    public List<GuestDto> saveAll(List<GuestDto> guestDtoList){
        return guestRepository.saveAll(
                        guestDtoList.stream()
                                .map(GuestDto::toEntity)
                                .collect(Collectors.toList())
                ).stream()
                .map(GuestDto::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Delete by id
     * @param id
     */

    @Transactional
    public void deleteById(Long id){
        guestRepository.deleteById(id);
        // TODO - data access exception after relationship will be created
    }

    /**
     * Delete all by id
     * @param ids
     */

    @Transactional
    public void deleteAllById(List<Long> ids){
        guestRepository.deleteAllById(ids);
        // TODO - What if user send an id that does not exist?
    }

    /**
     * Delete all
     */

    @Transactional
    public void deleteAll(){
        guestRepository.deleteAll();
    }

    /**
     * Update guest using model mapper strategy
     * @param source GuestDto that was sent by the user
     * @param destinationId id of the original object in the database
     * @return
     */
    @Transactional
    public GuestDto update(GuestDto source, Long destinationId){
        Guest destionation = guestRepository.getById(destinationId);
        ModelMapperUtil.map(source.toEntity(), destionation);
        destionation.setId(destinationId);
        return toDto(guestRepository.save(destionation));
    }

}
