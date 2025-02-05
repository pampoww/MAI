package mai.administracaousuarios.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Roles {
    ADMIN("ADMIN"),
    FUNC("FUNC"), // Funcionario
    USER("FUNC");

    private  String role;
    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static String[] toStringArray() {
        return Arrays.stream(values()).map(Enum::toString).toArray(String[]::new);
    }

    public static String[] toStringArrayFilter(String role){
        return Arrays.stream(values()).map(Enum::toString).filter(i -> !i.equals(role)).toArray(String[]::new);
    }

    public static String[] toStringArrayFilter(List<String> roles){
        return Arrays.stream(values()).map(Enum::toString).filter(i -> !roles.contains(i)).toArray(String[]::new);
    }

}
