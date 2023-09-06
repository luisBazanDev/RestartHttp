package pe.bazan.luis.plugins.restarthttp.domain;

public class VersionResponse {
    String version;
    String key;

    public VersionResponse(String version, String key) {
        this.version = version;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return String.format(
                "{\"version\": \"%s\",\"key\": \"%s\"}",
                version,
                key
        );
    }
}
