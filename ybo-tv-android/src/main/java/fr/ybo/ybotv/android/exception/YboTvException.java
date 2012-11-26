package fr.ybo.ybotv.android.exception;


public class YboTvException extends RuntimeException{

    public YboTvException() {
    }

    public YboTvException(String detailMessage) {
        super(detailMessage);
    }

    public YboTvException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public YboTvException(Throwable throwable) {
        super(throwable);
    }
}
