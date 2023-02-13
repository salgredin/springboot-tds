package edu.spring.td2.repositories

import org.springframework.data.repository.CrudRepository
import edu.spring.td2.entities.Organisation
import org.springframework.stereotype.Repository

@Repository
interface OrganizationRepository: CrudRepository<Organisation, Int> {
}