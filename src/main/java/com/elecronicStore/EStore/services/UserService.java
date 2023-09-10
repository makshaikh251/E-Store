package com.elecronicStore.EStore.services;

import com.elecronicStore.EStore.dtos.PageableResponse;
import com.elecronicStore.EStore.dtos.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    UserDto getUserById(String userId);

    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    UserDto getUserByEmail(String email);
    List<UserDto> searchUser(String keyword);




}

