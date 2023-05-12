package com.sk.scott;

import com.sk.scott.mapper.addressMapper;
import com.sk.scott.model.address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class AddressTest {
    @Autowired
    addressMapper _addressMapper;
    @Test
    public void TestInsert(){
        address address = new address();
        address.setAddress("makati");
        address.setRemark("insert1");
        _addressMapper.insert(address);
    }
}
