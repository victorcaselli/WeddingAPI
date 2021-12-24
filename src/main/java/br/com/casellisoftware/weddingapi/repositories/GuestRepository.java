package br.com.casellisoftware.weddingapi.repositories;

import br.com.casellisoftware.weddingapi.entities.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
