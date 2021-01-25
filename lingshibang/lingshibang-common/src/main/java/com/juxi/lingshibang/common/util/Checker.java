package com.juxi.lingshibang.common.util;


import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author yinbo.zhang
 * 参数校验器，如果有一个参数校验不通过，则后续参数不再校验
 */
public class Checker {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z\\d-_.]+@[A-Za-z\\d]+\\.[A-Za-z\\d]+$");
    private static final Pattern ACCOUNT_PATTERN = Pattern.compile("[A-Za-z0-9-_]{6,16}");
    private static final Pattern IDCARD_PATTERN = Pattern.compile("[0-9Xx]{15,18}");
    private static Pattern passwordPattern = Pattern.compile("^(?![a-zA-z]+$)(?!\\d+$)(?![~`!@#\\$%\\^&\\*\\(\\)\\-_=\\+\\[\\]\\{\\}\\|;:,\\.<>\\?/]+$)[a-zA-Z\\d~`!@#\\$%\\^&\\*\\(\\)\\-_=\\+\\[\\]\\{\\}\\|;:,\\.<>\\?/]+$");

    private boolean success = true;
    private String message;

    public Checker checkNotEmpty(String content, String description) {
        if (success) {
            checkWithNoMatch(content, description);
        }
        return this;
    }

    public Checker checkNotNull(Object content, String description) {
        if (success) {
            checkWithNoMatch(content, description);
        }
        return this;
    }

    public Checker checkNotIdcard(String idcard, String description) {
        if (success) {
            checkWithMatch(idcard, description, IDCARD_PATTERN);
        }
        return this;
    }

    public Checker checkPhone(String phone, String description) {
        if (success) {
            checkWithMatch(phone, description, PHONE_PATTERN);
        }
        return this;
    }

    public Checker checkAccount(String account, String description) {
        if (success) {
            checkWithMatch(account, description, ACCOUNT_PATTERN);
        }
        return this;
    }

    public Checker checkLoginPwd(String password, String description) {
        if (success) {
            checkWithMatch(password, description, passwordPattern);
        }
        return this;
    }

    public Checker checkLoginPwdIfHas(String password, String description) {
        if (success) {
            checkWithMatch(password, description, passwordPattern);
        }
        return this;
    }

    public Checker checkEmail(String email, String description) {
        if (success) {
            checkWithMatch(email, description, EMAIL_PATTERN);
        }
        return this;
    }

    public Checker checkRange(Integer value, int min, int max, String description) {
        if (success) {
            if (value < min || value > max) {
                message = description;
                success = false;
            }
        }
        return this;
    }

    public Checker checkRange(String value, int min, int max, String description) {
        if (success) {
            int length = value == null ? 0 : value.length();
            if (length < min || length > max) {
                message = description;
                success = false;
            }
        }
        return this;
    }

    public Checker checkMaxLength(String content, int maxLen, String description) {
        if (success && content != null) {
            if (content.length() > maxLen) {
                message = description;
                success = false;
            }
        }
        return this;
    }

    public Checker checkLength(String content, int minLen, int maxLen, String description) {
        if (success && content != null) {
            if (content.length() < minLen || content.length() > maxLen) {
                message = description;
                success = false;
            }
        }
        return this;
    }

    public Checker checkPattern(String content, String regex, String description) {
        if (success) {
            checkWithMatch(content, description, Pattern.compile(regex));
        }
        return this;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 处理不需要匹配规则
     *
     * @param content     内容
     * @param description 描述
     */
    private void checkWithNoMatch(Object content, String description) {
        if (Objects.isNull(content)) {
            message = description;
            success = false;
        }
    }

    /**
     * 处理需要匹配规则
     *
     * @param content     内容
     * @param description 描述
     */
    private void checkWithMatch(String content, String description, Pattern pattern) {
        boolean isSuccess = !Objects.isNull(content) && pattern.matcher(content).matches();
        if (!isSuccess) {
            message = description;
            success = false;
        }
    }

}
