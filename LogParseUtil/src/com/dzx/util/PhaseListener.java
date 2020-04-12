package com.dzx.util;

public interface PhaseListener {
    void start(String startInfo);

    void finish(String finishInfo);

    void error(String errorInfo);

    void progress(String progressInfo);
}
