package exceptions;

public class InvalidPassword extends DiaryAppException{
    public InvalidPassword(String message) {
        super(message);
    }
}
