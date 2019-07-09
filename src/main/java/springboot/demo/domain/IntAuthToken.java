package springboot.demo.domain;

public class IntAuthToken {

    private String intAuthToken;

    private String device;

    private String ip;

    private User user;

    public IntAuthToken() {}

    public String getIntAuthToken() {
        return intAuthToken;
    }

    public IntAuthToken setIntAuthToken(String intAuthToken) {
        this.intAuthToken = intAuthToken;
        return this;
    }

    public User getUser() {
        return user;
    }

    public IntAuthToken setUser(User user) {
        this.user = user;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public IntAuthToken setDevice(String device) {
        this.device = device;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public IntAuthToken setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
