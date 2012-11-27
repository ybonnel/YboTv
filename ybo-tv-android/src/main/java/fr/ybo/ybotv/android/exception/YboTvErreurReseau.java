package fr.ybo.ybotv.android.exception;


public class YboTvErreurReseau  extends Exception {

    public YboTvErreurReseau() {
    }

    public YboTvErreurReseau(String detailMessage) {
        super(detailMessage);
    }

    public YboTvErreurReseau(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public YboTvErreurReseau(Throwable throwable) {
        super(throwable);
    }
}
