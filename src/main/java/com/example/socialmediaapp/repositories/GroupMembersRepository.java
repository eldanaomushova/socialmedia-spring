package com.example.socialmediaapp.repositories;

import com.example.socialmediaapp.entities.GroupMembers;
import org.springframework.data.repository.CrudRepository;

public interface GroupMembersRepository extends CrudRepository<GroupMembers, Long> {
}
