package jewtvet.boathud_extended.mixin;

import jewtvet.boathud_extended.Common;
import jewtvet.boathud_extended.Config;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InGameHud.class})
public class InGameHudMixin {
    @Inject(method = {"renderStatusBars"}, at = {@At("HEAD")}, cancellable = true)
    private void renderStatusBars(DrawContext context, CallbackInfo ci) {
        if (Config.enabled && Common.ridingBoat && !(Common.client.currentScreen instanceof ChatScreen) && !(Common.client.options.playerListKey.isPressed())) {
            ci.cancel();
        }
    }

    @Inject(method = {"renderExperienceBar"}, at = {@At("HEAD")}, cancellable = true)
    private void renderExperienceBar(DrawContext context, int x, CallbackInfo ci) {
        if (Config.enabled && Common.ridingBoat && !(Common.client.currentScreen instanceof ChatScreen) && !(Common.client.options.playerListKey.isPressed())) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void onRenderExperienceLevel(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (Config.enabled && Common.ridingBoat && !(Common.client.currentScreen instanceof ChatScreen) && !(Common.client.options.playerListKey.isPressed())) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void onRenderHotbar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (Config.enabled && Common.ridingBoat && !(Common.client.currentScreen instanceof ChatScreen) && !(Common.client.options.playerListKey.isPressed())) {
            ci.cancel();
        }
    }
}
