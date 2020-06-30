package artonigroup.artonidemo.dao;

import artonigroup.artonidemo.model.Person;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

// contract for anyone that wishes to implement this interface
// then you use DI to switch between implementation
// DAO stands for data access object, it's the mapping between DB and code..
public interface PersonDao {

    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);

}
