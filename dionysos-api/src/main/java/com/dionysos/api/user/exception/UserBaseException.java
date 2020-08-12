package com.dionysos.api.user.exception;

import com.dionysos.api.common.exception.BaseException;
import com.dionysos.api.common.model.ErrorModel;

public class UserBaseException extends BaseException {

    protected UserBaseException(ErrorModel error) {
        super(error);
    }

}
