package mai.administracaousuarios.model.interfaces;

public interface InterfaceDTOMapper<Entity, DTO> {
    void toEntity(Entity entity);
}
