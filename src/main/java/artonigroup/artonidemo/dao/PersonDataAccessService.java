package artonigroup.artonidemo.dao;

import artonigroup.artonidemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//todo

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        final String querySql = "INSERT INTO public.person(id, name) VALUES (?,?)";
        try{
            jdbcTemplate.update(
                    querySql,
                    person.getId(),
                    person.getName());
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public int insertPerson(Person person) {
        final String querySql = "INSERT INTO public.person(name) VALUES (?)";
        try{
            jdbcTemplate.update(
                    querySql,
                    person.getName());
        }catch (Exception e){
            return 0;
        }
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String querySql = "SELECT id, name FROM public.person";
        List<Person> people = jdbcTemplate.query(querySql, ((resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new Person(id, name);
        }));
        return people;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String querySql = "SELECT id, name FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                querySql,
                new Object[]{id},
                ((resultSet, i) -> {
                    UUID personId =  UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString(("name"));
                    return new Person(personId, name);
                }));
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        String sql = "DELETE FROM public.person WHERE id = ?";
        Object[] args = new Object[] {id};

        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        // todo: implement the update
        return 0;
    }
}
