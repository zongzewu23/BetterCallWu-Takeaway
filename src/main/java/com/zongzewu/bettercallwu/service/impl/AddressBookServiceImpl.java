package com.zongzewu.bettercallwu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zongzewu.bettercallwu.entity.AddressBook;
import com.zongzewu.bettercallwu.mapper.AddressBookMapper;
import com.zongzewu.bettercallwu.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
