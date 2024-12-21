package app;

import java.io.Serializable;

public interface Receiver extends Serializable {
    String getIpAddress();
    int getPort();
}
