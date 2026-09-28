package name.tennocraft.entity;

import mod.azure.azurelib.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.animation.play_behavior.AzPlayBehavior;
import mod.azure.azurelib.animation.play_behavior.AzPlayBehaviors;

public class InfestedSkeletonDispatcher {

    private static final AzCommand IDLE_COMMAND = AzCommand.create
            ("base_controller","idle", AzPlayBehaviors.LOOP);

    private final InfestedSkeletonEntity entity;

    public InfestedSkeletonDispatcher(InfestedSkeletonEntity entity) {
        this.entity = entity;
    }

    public void idle() {
        IDLE_COMMAND.sendForEntity(entity);
    }

}
