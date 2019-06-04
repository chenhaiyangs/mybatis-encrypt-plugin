package com.chenhaiyang.plugin.mybatis.sensitive.test.all.testcase;

import com.chenhaiyang.plugin.mybatis.sensitive.test.all.dto.UserDTO;
import com.chenhaiyang.plugin.mybatis.sensitive.test.all.mapper.UserMapper;
import com.chenhaiyang.plugin.mybatis.sensitive.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapperTestCase {

    @Resource
    private UserMapper userMapper;

    @PostConstruct
    public void init(){
        //sql条件是预编译的
        //testInsert1();
       // testinsert2();
      testfinbyAll1();
        testfinbyAll2();
//        testfinbyId1();
  //      testfinbyConditon1();
      //testUpdateByCondition1();

    }

    //=======以下的测试用例，sql都是预编译的==============
    private void testInsert1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(1758695069);
        userDTO.setEmail("238484@qq.com");
        userDTO.setIdcard("130722199102900995");
        userDTO.setIdcardSensitive("130722199102900995");
        userDTO.setUserName("张三丰");
        userDTO.setUserNameSensitive("张三丰");


        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("username","张三丰");
        jsonMap.put("idcard","13072219991947585896");
        jsonMap.put("age",18);
        userDTO.setJsonStr(JsonUtils.parseMaptoJSONString(jsonMap));
        int result = userMapper.insert(userDTO);
        System.out.println(result);
    }
    //这种传值方式，加解密和脱敏无法生效，必须基于javaBean
    private void testinsert2() {
        int result = userMapper.insert2("马达哈","12222");
        System.out.println(result);
    }


    private void testfinbyAll1() {

        List<UserDTO>  userDTOS = userMapper.findAll();
        System.out.println(userDTOS);
    }
    //此种方式是基于resultmap查询
    private void testfinbyAll2() {
        List<UserDTO>  userDTOS = userMapper.findAll2();
        System.out.println(userDTOS);
    }

    private void testfinbyId1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(41);
        UserDTO   userDTO2 = userMapper.findOne(userDTO);
        System.out.println(userDTO2);
    }
    private void testfinbyConditon1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdcard("130722199102900995");
        List<UserDTO>   userDTO2 = userMapper.findByCondition(userDTO);
        System.out.println(userDTO2);
    }

    private void testUpdateByCondition1() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(42);

        UserDTO userDTO2 = userMapper.findOne(userDTO);
        userDTO2.setEmail("测试用email@qq.com");
        userDTO2.setIdcard("2344");
        userDTO2.setIdcardSensitive("2344");
        int result = userMapper.updateByCondition(userDTO2);
        System.out.println(result);
    }
}
