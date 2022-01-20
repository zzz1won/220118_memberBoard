package com.z1won.memberboard.service;

import com.z1won.memberboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardSerivceImpl implements BoardSerivce{
    private final BoardRepository br;
}
