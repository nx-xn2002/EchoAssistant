package com.bupt.echoassistantbackend.common.utils;

import cn.hutool.http.HttpUtil;
import com.bupt.echoassistantbackend.common.ErrorCode;
import com.bupt.echoassistantbackend.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * mail utils
 *
 * @author nx-xn2002
 * @date 2024-10-05
 */
public class MailUtils {
    public static boolean sendMail(String mailFrom, String password, String mailTo, String subject, String content) {
        if (StringUtils.isAnyBlank(mailFrom, password, mailTo, subject, content)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String url = "http://mail.nxx.nx.cn/mail_sys/send_mail_http.json";
        StringBuilder stringBuilder = new StringBuilder(url)
                .append("?mail_from=")
                .append(mailFrom)
                .append("&password=")
                .append(password)
                .append("&mail_to=")
                .append(mailTo)
                .append("&subject=")
                .append(subject)
                .append("&content=")
                .append(content);
        String result = HttpUtil.get(stringBuilder.toString());
        return result.contains("\"status\": true");
    }
}
