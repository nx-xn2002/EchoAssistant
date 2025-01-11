package com.bupt.echoassistantbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.echoassistantbackend.model.domain.Question;
import com.bupt.echoassistantbackend.service.QuestionService;
import com.bupt.echoassistantbackend.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 18702
* @description 针对表【question(题目表)】的数据库操作Service实现
* @createDate 2024-10-03 21:41:05
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




