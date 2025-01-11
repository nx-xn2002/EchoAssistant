package com.bupt.echoassistantbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.echoassistantbackend.model.domain.QuestionBank;
import com.bupt.echoassistantbackend.service.QuestionBankService;
import com.bupt.echoassistantbackend.mapper.QuestionBankMapper;
import org.springframework.stereotype.Service;

/**
* @author 18702
* @description 针对表【question_bank(题库表)】的数据库操作Service实现
* @createDate 2024-10-03 21:41:05
*/
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank>
    implements QuestionBankService{

}




