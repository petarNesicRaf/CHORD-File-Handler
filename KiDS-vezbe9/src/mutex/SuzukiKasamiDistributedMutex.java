package mutex;

import app.AppConfig;
import app.ServentInfo;
import servent.message.Message;
import servent.message.mutex_messages.AskForToken;
import servent.message.mutex_messages.TokenMessage;
import servent.message.util.MessageUtil;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class SuzukiKasamiDistributedMutex implements DistributedMutex{
    private AtomicBoolean distributedMutexInitiated = new AtomicBoolean(false);
    private AtomicLong timeStamp;
    public SuzukiKasamiDistributedMutex() {
        this.timeStamp = new AtomicLong(AppConfig.myServentInfo.getChordId());
    }

    public void updateTimeStamp(long newTimeStamp) {
        long currentTimeStamp = timeStamp.get();

        while (newTimeStamp > currentTimeStamp) {
            if (timeStamp.compareAndSet(currentTimeStamp, newTimeStamp + 1)) {
                break;
            }
            currentTimeStamp = timeStamp.get();
        }
    }
    @Override
    public void lock() {
        while (!distributedMutexInitiated.compareAndSet(false, true)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!AppConfig.myServentInfo.isTokenOwner()) {
            AskForToken askForTokenRequest = new AskForToken(AppConfig.myServentInfo,
                    null,  AppConfig.myServentInfo.getRnMap().get(AppConfig.myServentInfo) + 1);

            broadcastMessage(askForTokenRequest); // Use broadcast instead of direct neighbour access
        }

        while (true) {
            if (AppConfig.myServentInfo.isTokenOwner()) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void unlock() {
        TokenMessage token = AppConfig.myServentInfo.getTokenMessage();
        token.getLnMap().put(AppConfig.myServentInfo, AppConfig.myServentInfo.getRnMap().get(AppConfig.myServentInfo));

        if (token.getQueue().peek() == null) token.getQueue().add(AppConfig.myServentInfo);
        token.getQueue().remove();

        TokenMessage tokenMessage = new TokenMessage(AppConfig.myServentInfo,
                null, token.getLnMap(), token.getQueue());

        AppConfig.myServentInfo.getRnMap().forEach((key, value) -> {
            token.getLnMap().putIfAbsent(key, 0);
            if (value == token.getLnMap().get(key) + 1) {
                token.getQueue().add(key);
            }
        });

        if (token.getQueue().peek() != null) {
            AppConfig.timestampedErrorPrint("unlock");
            AppConfig.myServentInfo.setTokenOwner(false);
            broadcastMessage(tokenMessage); // Use broadcast instead of direct neighbour access
        }
        distributedMutexInitiated.set(false);
    }

    private void broadcastMessage(Message message) {
        // This method should implement the broadcasting mechanism to all necessary nodes
        MessageUtil.broadCastMessage(message);
    }
}
