package com.bupt.echoassistantbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bupt.echoassistantbackend.model.domain.QuestionTag;
import com.bupt.echoassistantbackend.service.QuestionTagService;
import com.bupt.echoassistantbackend.mapper.QuestionTagMapper;
import org.springframework.stereotype.Service;

/**
* @author 18702
* @description 针对表【question_tag(标签表)】的数据库操作Service实现
* @createDate 2024-10-03 21:41:06
*/
@Service
public class QuestionTagServiceImpl extends ServiceImpl<QuestionTagMapper, QuestionTag>
    implements QuestionTagService{

}




