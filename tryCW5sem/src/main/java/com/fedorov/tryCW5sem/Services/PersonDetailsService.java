package com.fedorov.tryCW5sem.Services;

import com.fedorov.tryCW5sem.Entity.Person;
import com.fedorov.tryCW5sem.Repositories.PeopleRepository;
import com.fedorov.tryCW5sem.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }

    public Person getPerson(String username) {
        return peopleRepository.findByUsername(username).get();
    }


    public List<Person> showAll(){
        return peopleRepository.findAll();
    }
}