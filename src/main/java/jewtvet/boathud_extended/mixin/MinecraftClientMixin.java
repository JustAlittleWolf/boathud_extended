package jewtvet.boathud_extended.mixin;

import jewtvet.boathud_extended.Common;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MinecraftClient.class})
public abstract class MinecraftClientMixin {

    @Shadow public abstract int getCurrentFps();

    @Inject(
        method = {"render"},
        at = {@At("TAIL")}
    )
    private void getCurrentFPS(boolean tick, CallbackInfo ci) {
        if (tick && Common.ridingBoat) {
            Common.hudData.fps = getCurrentFps();
        }

    }
}
