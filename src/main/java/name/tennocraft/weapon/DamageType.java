package name.tennocraft.weapon;

public enum DamageType {
    SLASH(StatusType.BLEED),
    IMPACT(StatusType.KNOCKBACK),
    PUNCTURE(StatusType.WEAKENED);
    // elemental types (Heat/Cold/Electric/Toxin) slot in here later, same pattern

    public final StatusType status;

    DamageType(StatusType status) {
        this.status = status;
    }
}