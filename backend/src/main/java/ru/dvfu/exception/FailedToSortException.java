package ru.dvfu.exception;

import ru.dvfu.exception.http.UnprocessableEntity;

public class FailedToSortException extends UnprocessableEntity {

    private static final String MESSAGE = "Невозможно отсортировать данные по данному полю";

    public FailedToSortException() {
        super(MESSAGE);
    }

    public FailedToSortException(Throwable cause) {
        super(MESSAGE, cause);
    }

}
