package br.com.casellisoftware.weddingapi.controllers;

import br.com.casellisoftware.weddingapi.dto.GuestDto;
import br.com.casellisoftware.weddingapi.services.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    @GetMapping
    public ResponseEntity<List<GuestDto>> findAll(){
        return ResponseEntity.ok().body(guestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDto> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(guestService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GuestDto> save(@RequestBody GuestDto guestDto){
        guestDto = guestService.save(guestDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(guestDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(guestDto);
    }

    @PostMapping("/save-all")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<GuestDto>> saveAll(@RequestBody List<GuestDto> guestDtoList){
        return ResponseEntity.ok().body(guestService.saveAll(guestDtoList));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam(name = "all", required = false, defaultValue = "false") Boolean deleteAll,
            @RequestParam(name = "by-id", required = false) Long id,
            @RequestParam(name = "all-by-id", required = false, defaultValue = "false") Boolean deleteAllById,
            @RequestBody(required = false) List<Long> ids
    ){
        if(deleteAll) {
            guestService.deleteAll();
        }else if(deleteAllById){
            guestService.deleteAllById(ids);
        }else if(id != null) {
            guestService.deleteById(id);
        }

        //TODO - Create a better response

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> update(@RequestBody GuestDto guestDto, @PathVariable Long id){
        return ResponseEntity.ok().body(guestService.update(guestDto,id));
    }
}
