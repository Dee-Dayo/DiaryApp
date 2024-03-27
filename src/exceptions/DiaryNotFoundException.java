package exceptions;

import exceptions.DiaryAppException;

public class DiaryNotFoundException extends DiaryAppException {
    public DiaryNotFoundException(String message) {
        super(message);
    }
}
