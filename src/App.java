import java.util.Objects;

public class App {

    private final int id;
    private String name;

    private String version;

    private int size;

    public App(int id, String name, String version, int size) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Apps{" +
                "  id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof App app)) return false;
        return id == app.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
